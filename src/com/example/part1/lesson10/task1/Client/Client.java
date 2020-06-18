package com.example.part1.lesson10.task1.Client;

import java.net.DatagramSocket;


public class Client {

    public static void main(String args[]) throws Exception {
        String host = "localhost";

        DatagramSocket socket = new DatagramSocket();
        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket, host);
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start(); st.start();
    }
}
