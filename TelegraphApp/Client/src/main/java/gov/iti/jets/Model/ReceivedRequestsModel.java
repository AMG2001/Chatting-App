package gov.iti.jets.Model;

import DTO.Request.RequestRecieveDTO;

import java.time.LocalDateTime;

public class ReceivedRequestsModel {
    private int requestId;
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String requestStatus;
    private LocalDateTime responseDate;
    private String senderPhone;

    public ReceivedRequestsModel(RequestRecieveDTO dto) {
        this.requestId = dto.getRequestId();
        this.sendDate = dto.getSendDate();
        this.receiverPhone = dto.getReceiverPhone();
        //this.requestStatus = dto.getRequestStatus();
        //this.responseDate = dto.getResponseDate();
        this.senderPhone = dto.getSenderPhone();
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }
// Getters and Setters for each field
    // ...
}

