package gov.iti.jets.Service.Mapstructs;

import DTO.Request.RequestSendDTO;
import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.enums.RequestStatus;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "requestId",target="requestId")
    @Mapping(source = "sendDate",target="sendDate")
    @Mapping(source = "receiverPhone",target="receiverPhone")
    @Mapping(source = "requestStatus",target="requestStatus")
    @Mapping(source = "responseDate",target="responseDate")
    @Mapping(source = "senderPhone",target="senderPhone")
    RequestSendDTO contactRequestToRequestDto(ContactRequest contactRequest);

    @Mapping(source = "requestId",target="requestId")
    @Mapping(source = "sendDate",target="sendDate")
    @Mapping(source = "receiverPhone",target="receiverPhone")
    @Mapping(source = "requestStatus",target="requestStatus")
    @Mapping(source = "responseDate",target="responseDate")
    @Mapping(source = "senderPhone",target="senderPhone")
    ContactRequest requestDtoToContactRequest(RequestSendDTO requestSendDto);

    // Add custom mapping for converting RequestStatus to String and vice versa
    default String mapRequestStatus(RequestStatus requestStatus) {
        return (requestStatus != null) ? requestStatus.name() : null;
    }

    default RequestStatus mapRequestStatus(String requestStatus) {
        return (requestStatus !=null)?RequestStatus.valueOf(requestStatus) : null;
    }
}
