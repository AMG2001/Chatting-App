package gov.iti.jets.Service.Mappers;

import DTO.AttachmentDTO;
import gov.iti.jets.Domain.Attachment;

public class AttachmentMapper {

    public static Attachment attachmentDTOToAttachment(AttachmentDTO attachmentDTO){
        Attachment attachment = new Attachment();

        attachment.setAttachmentId(attachmentDTO.getAttachmentId());
        attachment.setAttachmentType(attachmentDTO.getAttachmentType());
        attachment.setAttachmentName(attachmentDTO.getAttachmentName());
        attachment.setConversationId(attachmentDTO.getConversationId());

        return attachment;
    }

    public static AttachmentDTO attachmentToAttachmentDTO(Attachment attachment){
        AttachmentDTO attachmentDTO = new AttachmentDTO();

        attachmentDTO.setAttachmentId(attachment.getAttachmentId());
        attachmentDTO.setAttachmentName(attachment.getAttachmentName());
        attachmentDTO.setConversationId(attachment.getConversationId());
        attachmentDTO.setAttachmentType(attachment.getAttachmentType());

        return attachmentDTO;
    }
}
