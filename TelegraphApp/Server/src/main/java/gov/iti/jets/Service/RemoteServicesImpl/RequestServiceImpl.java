package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.NotificationDTO;
import DTO.RequestDTO;
import RemoteInterfaces.RemoteRequestService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.Notification;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.RequestCallbackHandler;
import gov.iti.jets.Service.Mapstructs.RequestMapper;
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
    public void sendRequest(RequestDTO request) throws RemoteException {
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

            contactRequestDao.add(contactRequest);

            System.out.println("Request has been added to database");

            notification.setBody("Request has been sent");

            notificationHandler.sendNotificationtoClient(notification, senderRemoteInt);

            notification.setBody(request.getSenderPhone() + "send you friend request");

            notificationHandler.sendNotificationtoClient(notification, receiverRemoteInt);

            requestHandler.sendRequest(request,receiverRemoteInt);

        }
    }

    @Override
    public void updateRequest(RequestDTO request) throws RemoteException {
        //TODO moataz
        /* Update the request to be accepted or rejected. IF accepted then use DAO to
            Create a conversation- Add relationship between user (Alter User table)
            If Rejected update Request Table
            Sent Notifications to sender that request was accepted or rejected.
        */

    }

    @Override
    public ArrayList<RequestDTO> getAllRequest(String phone) throws RemoteException {

        ContactRequestDao requestDao = new ContactRequestDaoImpl();
        List<ContactRequest> requests = requestDao.getRequestsByReceiver(phone);
        List<RequestDTO> requestDTOs = new ArrayList<>();
        for (ContactRequest request : requests) {
            RequestDTO requestDto = RequestMapper.INSTANCE.contactRequestToRequestDto(request);
            requestDTOs.add(requestDto);
        }
        return (ArrayList<RequestDTO>) requestDTOs;

    }
}
