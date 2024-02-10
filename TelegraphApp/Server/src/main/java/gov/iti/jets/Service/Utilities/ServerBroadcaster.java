package gov.iti.jets.Service.Utilities;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerBroadcaster {
    private DatagramSocket socket;
    private boolean isBroadcasting;

    public ServerBroadcaster() {
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startBroadcasting() {
        if (!isBroadcasting) {
            isBroadcasting = true;
            // Schedule the broadcasting task with a fixed rate
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(this::broadcast, 0, 12, TimeUnit.SECONDS);
        }
    }

    public void stopBroadcasting() {
        if (isBroadcasting) {
            isBroadcasting = false;
            socket.close();
        }
    }

    private void broadcast() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String localIP = localhost.getHostAddress();
            System.out.println(localIP);
            byte[] buffer = localIP.getBytes();
            // Use the same multicast address and port as the client listener
            InetAddress multicastAddress = InetAddress.getByName("239.255.255.255");
            int multicastPort = 8888;
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastAddress, multicastPort);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
