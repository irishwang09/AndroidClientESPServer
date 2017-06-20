package com.example.hwangir_local.client2;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import java.util.*;

/**
 * Created by hwangir-local on 6/20/2017.
 */

public class NetworkManager
{
    WifiManager wifiManager;
    public NetworkManager(WifiManager wm)
    {
        wifiManager = wm;
    }

    public void newNetworkConfig(String ssid, String pass)
    {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + ssid + "\"";
        conf.preSharedKey = "\"" + pass + "\"";
        wifiManager.addNetwork(conf);
    }

    public void connectTo(String ssid)
    {
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + ssid + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();
                break;
            }
        }
    }
}
