package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.AttachmentDTO;
import DTO.NotificationDTO;
import RemoteInterfaces.RemoteAttachmentService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Attachment;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Persistence.dao.AttachmentDao;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.doaImpl.AttachmentDoaImpl;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Service.CallbackHandlers.AttachmentCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.MessageCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.Mapstructs.AttachmentMapper;
import gov.iti.jets.Service.Utilities.FileSystemUtil;
import gov.iti.jets.Service.Utilities.FileType;
import gov.iti.jets.Service.Utilities.OnlineUserManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class AttachmentServiceImpl extends UnicastRemoteObject implements RemoteAttachmentService {
    public AttachmentServiceImpl() throws RemoteException {
    }

    @Override
    public void sendAttachment(AttachmentDTO attachmentDTO) throws RemoteException {

        AttachmentDao attachmentDao = new AttachmentDoaImpl();
        ConversationDao conversationDao = new ConversationDaoImpl();

        String attachmentPath = FileSystemUtil.storeByteArrayAsFile
                (attachmentDTO.getAttachment(), String.valueOf(attachmentDTO.getAttachmentId()), FileType.ATTACHMENT);

        Attachment attachmentDomain = AttachmentMapper.INSTANCE.attachmentDTOToAttachment(attachmentDTO);
        attachmentDomain.setAttachmentLocation(attachmentPath);
        attachmentDao.add(attachmentDomain); //TODO yousef

        List<String> conversationParticipants =
                conversationDao.getConversationParticipants(attachmentDTO.getConversationId());
        final List<RemoteCallbackInterface> friendsCallBacks =
                OnlineUserManager.getFriendsFromOnlineList(conversationParticipants);

        AttachmentCallbackHandler attachmentCallbackHandler = new AttachmentCallbackHandler();
        NotificationCallbackHandler notificationHandler = new NotificationCallbackHandler();

        NotificationDTO notification = new NotificationDTO(
                "1", NotificationType.MESSAGE.toString()
                , LocalDateTime.now(),"You received an attachment");

       //(CALLBACK)
        attachmentCallbackHandler.sendMessages(attachmentDTO,friendsCallBacks);
        notificationHandler.sendNotification(notification,friendsCallBacks);

    }

    @Override
    public List<AttachmentDTO> getAllAttachmentsForConversation(int conversationId) throws RemoteException {
        //TODO Moataz
        //This will not fetch the attachment data from the filesystem.
        return null;
    }

    @Override
    public byte[] getAttachmentData(String attachmentId) throws RemoteException {
        //TODO moataz
        // THis returns the bytes from the file system or the attachment.
        // The file path is retrieved from the DB
        return new byte[0];
    }
}
