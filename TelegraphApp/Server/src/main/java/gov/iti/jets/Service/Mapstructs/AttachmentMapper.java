package gov.iti.jets.Service.Mapstructs;

import DTO.AttachmentDTO;
import gov.iti.jets.Domain.Attachment;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @Mapping(source = "attachmentId", target = "attachmentId")
    @Mapping(source = "attachmentType", target = "attachmentType")
    @Mapping(source = "attachmentName", target = "attachmentName")
    @Mapping(source = "conversationId", target = "conversationId")
    Attachment attachmentDTOToAttachment(AttachmentDTO attachmentDTO);
}
