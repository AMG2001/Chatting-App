package DTO.Request;

import java.io.Serializable;

public class RequestResponseDTO implements Serializable {
    private int requestID;
    private String requestStatus;

    @Override
    public String toString() {
        return "RequestResponseDTO{" +
                "requestID=" + requestID +
                ", requestStatus='" + requestStatus + '\'' +
                ", senderPhone='" + senderPhone + '\'' +
                ", recieverPhone='" + recieverPhone + '\'' +
                '}';
    }

    private String senderPhone;
    private String recieverPhone;

    public RequestResponseDTO() {

    }

    public RequestResponseDTO(int requestID, String requestStatus, String senderPhone, String recieverPhone) {
        this.requestID = requestID;
        this.requestStatus = requestStatus;
        this.senderPhone = senderPhone;
        this.recieverPhone = recieverPhone;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getRecieverPhone() {
        return recieverPhone;
    }

    public void setRecieverPhone(String recieverPhone) {
        this.recieverPhone = recieverPhone;
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
}
