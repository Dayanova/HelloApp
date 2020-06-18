package com.example.part1.lesson10.task1.Server;


import java.net.SocketException;

public class ServerStarter {
    public static void main(String[] args) throws SocketException {
        Server udpServer = new Server();
        udpServer.start();
    }
}
