package DTO;

import java.io.Serial;
import java.io.Serializable;

public class LogoutDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private String name;
    private String userPhone;

    public LogoutDTO(String name, String userPhone) {
        this.name = name;
        this.userPhone = userPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
