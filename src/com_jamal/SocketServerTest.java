package com_jamal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {

    public static void main(String[] args) {
        int port = 8017;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Socket started");
            System.out.println("Ready to accept connections from client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected");
            System.out.println();

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(socketInputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);
            String line = null;
            while (true) {
                line = dataInputStream.readUTF();
                System.out.println("[Client]: " + line);
                dataOutputStream.writeUTF("I say " + line);
                dataOutputStream.flush();
                System.out.println("Ready for next message");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
