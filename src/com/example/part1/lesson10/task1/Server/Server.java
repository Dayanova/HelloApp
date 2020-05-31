package com.example.part1.lesson10.task1.Server;

import com.example.part1.lesson04.Cat;

import java.io.IOException;
import java.net.*;
import java.util.*;


public class Server extends Thread {

    private final static int BUFFER = 1024;
    public final int port = 8080;
    private DatagramSocket datagramSocket;

    private Map<String, InetAddress> existingClients;

    public Server() throws SocketException {
        setName("Server");
        datagramSocket = new DatagramSocket(port);
        existingClients = new HashMap();
    }

    public String ModifyString(String word, Integer limit) {
        String value = " ";
        String[] words = word.split("#", limit);
        for (String wor : words) {
            value = wor;
        }
        return value;

    }


    private void downService() {
        if (!datagramSocket.isClosed()) {
            datagramSocket.close();
        }
    }

    public void run() {
        byte[] buf = new byte[BUFFER];
        String nik = null;
        String content = null;
        try {
            while (!isInterrupted()) {
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                content = new String(buf, buf.length);
                nik = ModifyString(content, 2);

                content = content.replaceFirst('#' + nik + '#', "");

               String PrivateNik = ModifyString(content, 2);

                content = content.replaceFirst('#' + PrivateNik + '#', "");

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();


                if (!existingClients.containsKey(nik)) {
                    existingClients.put(nik, clientAddress);
                }

                if (packet.equals("quit")) {
                    this.downService();
                }

                System.out.println(nik);
                System.out.println(content);
                byte[] data = (nik + " : " + content).getBytes();

               if (PrivateNik.isEmpty()) {


                    for (Map.Entry<String, InetAddress> entry : this.existingClients.entrySet()) {
                        packet = new DatagramPacket(data, data.length, entry.getValue(), port);
                        datagramSocket.send(packet);
                    }
                } else {
                  packet = new DatagramPacket(data, data.length, existingClients.get(PrivateNik), port);
                    datagramSocket.send(packet);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}