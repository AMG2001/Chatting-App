package gov.iti.jets.Controllers.services;

import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.InputStream;

public class SoundsService {
    public static Player player;

    public static void playNotificationSound() {
        Thread thread = new Thread(() -> {
            try {
                InputStream is = SoundsService.class.getClassLoader().getResourceAsStream("assets/sound/notification_sound.mp3");
                player = new Player(is);
                player.play();
            } catch (Exception e) {
                System.out.println("Problem while playing notification Sound file !!");
                System.out.println(e);
            }
        });
        thread.start();
    }
}
