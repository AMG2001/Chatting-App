package gov.iti.jets.ServiceContext;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientListener {

    private MulticastSocket socket;
    private boolean isListening;

    // Variable to store the received IP address
    public String receivedIpAddress;

    private ExecutorService executorService;


    public ClientListener() {
        try {
            socket = new MulticastSocket(8888);
            socket.joinGroup(InetAddress.getByName("239.255.255.255"));
            isListening = true;
            receivedIpAddress = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        if (!isListening) {
            isListening = true;
            Executors.newSingleThreadExecutor().submit(this::listenLoop);
        }
    }

    public void stopListening() {
        if (isListening) {
            isListening = false;
            socket.close();
        }
    }

    private void listenLoop() {
        try {
            byte[] buffer = new byte[1024];
            while (isListening) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String ipAddress = packet.getAddress().getHostAddress();
                receivedIpAddress = ipAddress;
                System.out.println("Received IP address: " + receivedIpAddress);
                if (!receivedIpAddress.isEmpty()) {
                    System.out.println("Desired IP address received.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}