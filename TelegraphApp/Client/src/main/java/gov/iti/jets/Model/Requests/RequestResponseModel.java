package gov.iti.jets.Model.Requests;

import DTO.Request.RequestResponseDTO;

public class RequestResponseModel {
    private int requestID;
    private String requestStatus;
    private String senderPhone;
    private String receiverPhone;

    public RequestResponseModel(RequestResponseDTO dto) {
        this.requestID = dto.getRequestID();
        this.requestStatus = dto.getRequestStatus();
        this.senderPhone = dto.getSenderPhone();
        this.receiverPhone = dto.getRecieverPhone();
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
