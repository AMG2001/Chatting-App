package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.User.ContactDTO;
import DTO.ConversationDTO;
import DTO.NotificationDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.Request.RequestSendDTO;
import RemoteInterfaces.RemoteRequestService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Domain.enums.RequestStatus;
import gov.iti.jets.Persistence.dao.ContactDao;
import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.ContactDaoImpl;
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

        //ContactRequest contactRequest = RequestMapper.INSTANCE.requestDtoToContactRequest(request);
        ContactRequest contactRequest=new ContactRequest();
        contactRequest.setSendDate(request.getSendDate());
        contactRequest.setReceiverPhone(request.getReceiverPhone());
        contactRequest.setSenderPhone(request.getSenderPhone());

        ContactRequestDao contactRequestDao = new ContactRequestDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();

        RemoteCallbackInterface senderRemoteInt = OnlineUserManager.getOnlineUser(request.getSenderPhone());

        RemoteCallbackInterface receiverRemoteInt = OnlineUserManager.getOnlineUser(request.getReceiverPhone());

        NotificationCallbackHandler notificationHandler = new NotificationCallbackHandler();

        RequestCallbackHandler requestHandler = new RequestCallbackHandler();

        NotificationDTO notification = new NotificationDTO("1", NotificationType.SYSTEM.toString()
                , LocalDateTime.now(), "User Phone not found");

        if (user.getById(request.getReceiverPhone()) == null) {

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

        } else if (contactRequestDao.checkIfRequestExist(contactRequest) != false) {

            notification.setBody("Request has been sent before");

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

        } else if (contactDao.checkIfAlreadyContacts(request.getReceiverPhone(), request.getSenderPhone()) == true) {
            notification.setBody(request.getReceiverPhone() + " is already in your contact list");

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);
        } else {

            int requestId = contactRequestDao.addRequest(contactRequest);

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
    public void updateRequest(RequestResponseDTO requestDTO) throws RemoteException {

        //update DB
        ContactRequestDao contactRequestDao = new ContactRequestDaoImpl();
        
        ContactRequest updatedRequest = new ContactRequest();

        updatedRequest.setRequestId(requestDTO.getRequestID());
        updatedRequest.setRequestStatus(RequestStatus.valueOf(requestDTO.getRequestStatus()));
        updatedRequest.setResponseDate(LocalDateTime.now());
        updatedRequest.setReceiverPhone(requestDTO.getRecieverPhone());
        updatedRequest.setSenderPhone(requestDTO.getSenderPhone());


        contactRequestDao.update(updatedRequest);

        //get sender and receiver callbacks
        RemoteCallbackInterface senderCallBack = OnlineUserManager.getOnlineUser(requestDTO.getSenderPhone());
        RemoteCallbackInterface receiverCallBack = OnlineUserManager.getOnlineUser(requestDTO.getRecieverPhone());

        if(requestDTO.getRequestStatus().equals("ACCEPTED")){

            //get individual conversation between sender and receiver from DB
            ConversationDao conversationDao = new ConversationDaoImpl();
            int conversationId = conversationDao.getIndividualConversationId(requestDTO.getSenderPhone(),requestDTO.getRecieverPhone());
            Conversation conversationDomain = conversationDao.getById(conversationId);

            // map conversation domain to conversation dto and set messages and attachments to empty lists
            ConversationDTO conversationDTO = ConversationMapper.INSTANCE.conversationToConversationDTO(conversationDomain);
            conversationDTO.setMessages(new ArrayList<>());
            conversationDTO.setAttachments(new ArrayList<>());

            // get sender and receiver domain
            UserDao userDao = new UserDoaImpl();
            User senderDomain = userDao.getById(requestDTO.getSenderPhone());
            User receiverDomain = userDao.getById(requestDTO.getRecieverPhone());

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
    public ArrayList<RequestRecieveDTO> getAllRequest(String phone) throws RemoteException {

        //Fetch All requests
        ContactRequestDao requestDao = new ContactRequestDaoImpl();
        List<ContactRequest> requests = requestDao.getRequestsByReceiver(phone);
        List<RequestRecieveDTO> recievedRequests = new ArrayList<>();
        //Fetch all sender Names
        UserDao userDao = new UserDoaImpl();
        String senderPhone;
        String reciverPhone;
        String senderName;
        LocalDateTime sendTime;
        int requestId;
        //Create SentRequest Object and pass to array
        for (ContactRequest request : requests) {
            senderPhone = request.getSenderPhone();
            reciverPhone = request.getReceiverPhone();
            senderName = userDao.getById(senderPhone).getName();
            sendTime = request.getSendDate();
            requestId = request.getRequestId();

            RequestRecieveDTO requestSendDto = new RequestRecieveDTO
                    (requestId,sendTime,reciverPhone,senderPhone,senderName);

            recievedRequests.add(requestSendDto);
        }

        return (ArrayList<RequestRecieveDTO>) recievedRequests;

    }
}
