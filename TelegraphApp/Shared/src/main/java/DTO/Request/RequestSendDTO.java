package DTO.Request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RequestSendDTO implements Serializable {
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String senderPhone;

    public RequestSendDTO(LocalDateTime sendDate, String receiverPhone, String senderPhone) {
        this.sendDate = sendDate;
        this.receiverPhone = receiverPhone;
        this.senderPhone = senderPhone;
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
