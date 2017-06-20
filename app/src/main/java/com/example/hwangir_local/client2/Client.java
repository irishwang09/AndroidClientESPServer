package com.example.hwangir_local.client2;

import android.os.AsyncTask;
import android.widget.TextView;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by hwangir-local on 6/19/2017.
 */

public class Client extends AsyncTask<Void, Void, Void> {
    String dstAddress;
    String message;
    int dstPort;
    TextView textView;

    Client(String address, int port, TextView tv)
    {
        dstAddress = address;
        dstPort = port;
        textView = tv;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Socket socket = null;
        try {
            socket = new Socket(dstAddress, dstPort);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.print("Hello from client!");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                message += byteArrayOutputStream.toString("UTF-8");
            }
            printStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            message = "EXCEPTION THROWN: could not open new socket";
        }

        finally {
            if (socket != null) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    message = "EXCEPTION THROWN: could not close socket";
                }
            }
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        String ogMessage = textView.getText().toString();
        textView.setText(ogMessage + " | " + message);
        super.onPostExecute(result);
    }
}


