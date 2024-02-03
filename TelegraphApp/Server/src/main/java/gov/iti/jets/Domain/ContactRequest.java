package gov.iti.jets.Domain;

import gov.iti.jets.Domain.enums.RequestStatus;

import java.util.Date;

public class ContactRequest {
    private int requestId;
    private Date sendDate;
    private String receiverPhone;
    private RequestStatus requestStatus;
    private Date responseDate;
    private String senderPhone;

    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
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
    @Override
    public String toString() {
        return "ContactRequest{" +
                "requestId=" + requestId +
                ", senderPhone='" + senderPhone + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", status='" + requestStatus + '\'' +
                ", responseDate='" + responseDate + '\'' +
                ", sendDate='" + sendDate + '\'' +
                '}';
    }
}

