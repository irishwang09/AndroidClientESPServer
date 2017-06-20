package com.example.hwangir_local.client2;

import android.content.Context;
import android.net.wifi.*;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button pokeButton;
    TextView printText;
    WifiManager wifiManager;
    NetworkManager networkManager;
    int clickCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokeButton = (Button) findViewById(R.id.pokeButton);
        printText = (TextView) findViewById(R.id.printText);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        networkManager = new NetworkManager(wifiManager);
        networkManager.newNetworkConfig("server3", "12345678");
        networkManager.newNetworkConfig("server4", "12345678");

        pokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                /*if (clickCount == 0) {
                    networkManager.connectTo("server3");
                    clickCount++;
                }
                else {
                    networkManager.connectTo("server4");
                    clickCount = 0;
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WifiInfo info = wifiManager.getConnectionInfo ();
                        String ssid  = info.getSSID();
                        String ogText = printText.getText().toString();
                        printText.setText(ogText + " | " + ssid);
                        Client client = new Client("192.168.4.1", 8080, printText);
                        client.execute();
                    }
                }, 2000);*/

                /*Client client = new Client("192.168.4.1", 8080, printText);
                client.execute();
                for (int i = 0; i < 10; i++)
                {
                    switchNetworkandSend();
                }*/
                switchNetworkandSend();
            }
        });
    }
    private void switchNetworkandSend()
    {
        if (clickCount == 0) {
            networkManager.connectTo("server3");
            clickCount++;
        }
        else {
            networkManager.connectTo("server4");
            clickCount = 0;
        }
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                WifiInfo info = wifiManager.getConnectionInfo ();
                String ssid  = info.getSSID();
                String ogText = printText.getText().toString();
                printText.setText(ogText + " | " + ssid);
                Client client = new Client("192.168.4.1", 8080, printText);
                client.execute();
            }
        }, 2000);
    }

}
