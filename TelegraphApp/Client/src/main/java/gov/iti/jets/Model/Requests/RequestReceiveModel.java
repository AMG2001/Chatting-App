package gov.iti.jets.Model.Requests;

import DTO.Request.RequestRecieveDTO;

import java.time.LocalDateTime;


public class RequestReceiveModel {
    private int requestId;
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String senderPhone;
    private String senderName;

    public RequestReceiveModel(RequestRecieveDTO dto) {
        this.requestId = dto.getRequestId();
        this.sendDate = dto.getSendDate();
        this.receiverPhone = dto.getReceiverPhone();
        this.senderPhone = dto.getSenderPhone();
        this.senderName = dto.getSenderName();
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

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}


