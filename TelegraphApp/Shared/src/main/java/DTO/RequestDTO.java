package DTO;

import java.io.Serializable;
import java.util.Date;

public class RequestDTO implements Serializable {
    private String requestId;
    private Date sendDate;
    private String receiverPhone;
    private String requestStatus;
    private Date responseDate;
    private String senderPhone;


    public RequestDTO(String requestId, Date sendDate, String receiverPhone, String requestStatus, Date responseDate, String senderPhone) {
        this.requestId = requestId;
        this.sendDate = sendDate;
        this.receiverPhone = receiverPhone;
        this.requestStatus = requestStatus;
        this.responseDate = responseDate;
        this.senderPhone = senderPhone;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
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

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }
}
