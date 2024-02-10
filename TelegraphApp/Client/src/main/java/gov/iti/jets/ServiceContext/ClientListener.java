package gov.iti.jets.ServiceContext;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientListener {

    private MulticastSocket socket;
    private boolean isListening;

    // Variable to store the received IP address
    private String receivedIpAddress;

    public ClientListener() {
        try {
            // Use the same multicast address and port as the server
            InetAddress multicastAddress = InetAddress.getByName("239.255.255.255");
            int multicastPort = 8888;

            socket = new MulticastSocket(multicastPort);
            socket.joinGroup(multicastAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        if (!isListening) {
            isListening = true;
            new Thread(this::listenLoop).start();
        }
    }

    public void stopListening() {
        if (isListening) {
            isListening = false;
            socket.close();
        }
    }

    // Method to get the received IP address
    public String getReceivedIpAddress() {
        return receivedIpAddress;
    }

    private void listenLoop() {
        try {
            byte[] buffer = new byte[1024];

            while (isListening) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Extract the received IP address
                String ipAddress = packet.getAddress().getHostAddress();

                // Save the IP address in the variable
                receivedIpAddress = ipAddress;

                // Process the received data
                System.out.println("Received IP address: " + receivedIpAddress);

                if (!(receivedIpAddress.isEmpty())) {
                    System.out.println("Desired IP address received. Stopping listening.");
                    stopListening();
                }

                // Sleep for a specified interval before the next check
                Thread.sleep(1000);  // Sleep for 1 second (adjust as needed)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isListening() {
        return isListening;
    }
}
