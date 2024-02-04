package DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RequestDTO implements Serializable {
    private int requestId;
    private LocalDateTime sendDate;
    private String receiverPhone;
    private String requestStatus;
    private LocalDateTime responseDate;
    private String senderPhone;


    public RequestDTO(int requestId, LocalDateTime sendDate, String receiverPhone, String requestStatus, LocalDateTime responseDate, String senderPhone) {
        this.requestId = requestId;
        this.sendDate = sendDate;
        this.receiverPhone = receiverPhone;
        this.requestStatus = requestStatus;
        this.responseDate = responseDate;
        this.senderPhone = senderPhone;
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
}
