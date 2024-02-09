package DTO.Request;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RequestRecieveDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -987654321L;
    private int requestId;
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String senderPhone;
    private String senderName;

    public RequestRecieveDTO(int requestId, LocalDateTime sendDate, String receiverPhone, String senderPhone, String senderName) {
        this.requestId = requestId;
        this.sendDate = sendDate;
        this.receiverPhone = receiverPhone;
        this.senderPhone = senderPhone;
        this.senderName = senderName;
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
