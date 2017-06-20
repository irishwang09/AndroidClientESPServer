package com.example.hwangir_local.client2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by hwangir-local on 6/19/2017.
 */

public class ClientSender extends Thread
{
    private Socket clientSocket;
    private PrintStream printStream;

    public ClientSender (Socket socket, PrintStream ps) {
        clientSocket = socket;
        printStream = ps;
    }

    @Override
    public void run() {
        String clientMessage = "Hello from Client!";
        printStream.print(clientMessage);
    }
}
