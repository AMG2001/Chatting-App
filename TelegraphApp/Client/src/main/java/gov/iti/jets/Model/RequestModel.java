package gov.iti.jets.Model;

import DTO.RequestDTO;

import java.time.LocalDateTime;

public class RequestModel {
    private int requestId;
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String requestStatus;
    private LocalDateTime responseDate;
    private String senderPhone;

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

    public RequestModel(RequestDTO requestDTO) {
        this.requestId = requestDTO.getRequestId();
        this.sendDate = requestDTO.getSendDate();
        this.receiverPhone = requestDTO.getReceiverPhone();
        this.requestStatus = requestDTO.getRequestStatus();
        this.responseDate = requestDTO.getResponseDate();
        this.senderPhone = requestDTO.getSenderPhone();
    }

    // Getters and Setters remain the same
    // ...
}
