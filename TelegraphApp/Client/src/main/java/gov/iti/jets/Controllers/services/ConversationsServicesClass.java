package gov.iti.jets.Controllers.services;

import gov.iti.jets.Controllers.Shared.CustomEnums;
import javafx.scene.paint.Color;

public class ConversationsServicesClass {
    public static Color setConversationsCircleColor(String status) {
        Color color = null;
        if (status.equals(CustomEnums.UserStatus_ONLINE)) {
            color = Color.GREEN;
        } else if (status.equals(CustomEnums.UserStatus_OFFLINE)) {
            color = Color.GRAY;
        } else if (status.equals(CustomEnums.UserStatus_AWAY)) {
            color = Color.ORANGE;
        } else if (status.equals(CustomEnums.UserStatus_BUSY)) {
            color = Color.RED;
        }
        return color;
    }

}
