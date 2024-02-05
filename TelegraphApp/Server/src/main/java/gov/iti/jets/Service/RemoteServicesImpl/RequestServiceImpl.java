package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.ContactDTO;
import DTO.ConversationDTO;
import DTO.NotificationDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestSendDTO;
import RemoteInterfaces.RemoteRequestService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Domain.enums.RequestStatus;
import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.RequestCallbackHandler;
import gov.iti.jets.Service.Mapstructs.ContactMapper;
import gov.iti.jets.Service.Mapstructs.ConversationMapper;
import gov.iti.jets.Service.Mapstructs.RequestMapper;
import gov.iti.jets.Service.Utilities.FileSystemUtil;
import gov.iti.jets.Service.Utilities.OnlineUserManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl extends UnicastRemoteObject implements RemoteRequestService {

    public RequestServiceImpl() throws RemoteException {

    }

    @Override
    public void sendRequest(RequestSendDTO request) throws RemoteException {
        //TODO yousef HANDLE NULLS & Exceptionss
        UserDao user = new UserDoaImpl();

        ContactRequest contactRequest = RequestMapper.INSTANCE.requestDtoToContactRequest(request);

        ContactRequestDao contactRequestDao = new ContactRequestDaoImpl();

        RemoteCallbackInterface senderRemoteInt = OnlineUserManager.getOnlineUser(request.getSenderPhone());

        RemoteCallbackInterface receiverRemoteInt = OnlineUserManager.getOnlineUser(request.getReceiverPhone());

        NotificationCallbackHandler notificationHandler = new NotificationCallbackHandler();

        RequestCallbackHandler requestHandler = new RequestCallbackHandler();

        NotificationDTO notification = new NotificationDTO("1", NotificationType.SYSTEM.toString()
                , LocalDateTime.now(), "User Phone not found");

        if (user.getById(request.getReceiverPhone()) == null) {

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

        } else if (contactRequestDao.checkIfRequestExist(contactRequest)!=false) {

            notification.setBody("Request has been sent before");

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

        } else {

            int requestId = contactRequestDao.add(contactRequest);

            System.out.println("Request has been added to database");

            notification.setBody("Request has been sent");

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

            notification.setBody(request.getSenderPhone() + "send you friend request");

            notificationHandler.sendNotificationtoClient(notification, receiverRemoteInt);

            //Set up a received Request with the name of the sender
            User sender = user.getById(request.getSenderPhone());

            String senderName = sender.getName();
            String recieverPhone = request.getReceiverPhone();
            String senderPhone = request.getSenderPhone();
            LocalDateTime sendDate = request.getSendDate();
            RequestRecieveDTO recieverRequest = new RequestRecieveDTO
                    (requestId,sendDate,recieverPhone,senderPhone,senderName);

            requestHandler.sendRequest(recieverRequest,receiverRemoteInt);

        }
    }

    @Override
    public void updateRequest(RequestDTO requestDTO) throws RemoteException {

        //update DB
        ContactRequestDao contactRequestDao = new ContactRequestDaoImpl();
        ContactRequest requestModel = RequestMapper.INSTANCE.requestDtoToContactRequest(requestDTO);
        contactRequestDao.update(requestModel);

        //get sender and receiver callbacks
        RemoteCallbackInterface senderCallBack = OnlineUserManager.getOnlineUser(requestDTO.getSenderPhone());
        RemoteCallbackInterface receiverCallBack = OnlineUserManager.getOnlineUser(requestDTO.getReceiverPhone());

        if(requestDTO.getRequestStatus()=="ACCEPTED"){

            //get individual conversation between sender and receiver from DB
            ConversationDao conversationDao = new ConversationDaoImpl();
            int conversationId = conversationDao.getIndividualConversationId(requestDTO.getSenderPhone(),requestDTO.getReceiverPhone());
            Conversation conversationDomain = conversationDao.getById(conversationId);

            // map conversation domain to conversation dto and set messages and attachments to empty lists
            ConversationDTO conversationDTO = ConversationMapper.INSTANCE.conversationToConversationDTO(conversationDomain);
            conversationDTO.setMessages(new ArrayList<>());
            conversationDTO.setAttachments(new ArrayList<>());

            // get sender and receiver domain
            UserDao userDao = new UserDoaImpl();
            User senderDomain = userDao.getById(requestDTO.getSenderPhone());
            User receiverDomain = userDao.getById(requestDTO.getReceiverPhone());

            // map sender and receiver domain to contact dto
            ContactDTO senderDTO = ContactMapper.INSTANCE.userToContactDTO(senderDomain);
            ContactDTO receiverDTO = ContactMapper.INSTANCE.userToContactDTO(receiverDomain);

            // set individual conversation dto to both sender and receiver dto
            senderDTO.setConversation(conversationDTO);
            receiverDTO.setConversation(conversationDTO);

            // set images for sender and receiver dto
            byte[] senderImage = FileSystemUtil.getBytesFromFile(senderDomain.getPicture());
            senderDTO.setProfilepic(senderImage);
            byte[] receiverImage = FileSystemUtil.getBytesFromFile(receiverDomain.getPicture());
            receiverDTO.setProfilepic(receiverImage);

            // add both of them as contact to each other(CALLBACK)
            senderCallBack.addContact(receiverDTO);
            receiverCallBack.addContact(senderDTO);

        }

        //update list of received requests of receiver(CALLBACK)
        RequestCallbackHandler requestHandler = new RequestCallbackHandler();
        requestHandler.updateRequest(requestDTO,receiverCallBack);
    }

    @Override
    public ArrayList<RequestSendDTO> getAllRequest(String phone) throws RemoteException {

        ContactRequestDao requestDao = new ContactRequestDaoImpl();
        List<ContactRequest> requests = requestDao.getRequestsByReceiver(phone);
        List<RequestSendDTO> requestSendDTOS = new ArrayList<>();
        for (ContactRequest request : requests) {
            RequestSendDTO requestSendDto = RequestMapper.INSTANCE.contactRequestToRequestDto(request);
            requestSendDTOS.add(requestSendDto);
        }
        return (ArrayList<SentRequestDTO>) sentRequestDTOS;

    }
}
