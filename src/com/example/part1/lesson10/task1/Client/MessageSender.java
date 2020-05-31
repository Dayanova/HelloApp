package com.example.part1.lesson10.task1.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessageSender implements Runnable {
    public final static int PORT = 8080;
    private static BufferedReader inputUser;
    private DatagramSocket sock;
    private String hostname;
    private String nickname; // имя клиента

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname ="#" + inputUser.readLine() + "#";
        } catch (IOException ignored) {
        }

    }

    MessageSender(DatagramSocket s, String h) {
        sock = s;
        hostname = h;
        inputUser = new BufferedReader(new InputStreamReader(System.in));
        this.pressNickname();
    }
    private void sendMessage(String s) throws Exception {
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }
    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage(nickname);
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                sendMessage(in.readLine());
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}
