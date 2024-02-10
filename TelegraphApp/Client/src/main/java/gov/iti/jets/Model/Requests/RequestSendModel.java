package gov.iti.jets.Model.Requests;

import DTO.Request.RequestSendDTO;

import java.time.LocalDateTime;

public class RequestSendModel {
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String senderPhone;

    public RequestSendModel(RequestSendDTO dto) {
        this.sendDate = dto.getSendDate();
        this.receiverPhone = dto.getReceiverPhone();
        this.senderPhone = dto.getSenderPhone();
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
}
