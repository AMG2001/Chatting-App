package gov.iti.jets.Service.Mappers;

import DTO.Request.RequestSendDTO;
import gov.iti.jets.Domain.ContactRequest;

public class RequestMapper {

    public static ContactRequest requestSendDtoToContactRequest(RequestSendDTO requestSendDto){

        ContactRequest contactRequest=new ContactRequest();
        contactRequest.setSendDate(requestSendDto.getSendDate());
        contactRequest.setReceiverPhone(requestSendDto.getReceiverPhone());
        contactRequest.setSenderPhone(requestSendDto.getSenderPhone());

        return contactRequest;
    }
}
