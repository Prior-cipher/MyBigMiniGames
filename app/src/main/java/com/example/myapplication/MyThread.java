package com.example.myapplication;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;

import okhttp3.WebSocketListener;
public class MyThread extends  Thread
{
    WebSocket ws;
    public MyThread()
    {

    }
    public void run()
    {
        Log.d("", "Mой поток запущен...");
        Request request ;

        OkHttpClient client = new OkHttpClient();
        request = new Request.Builder().url("ws://192.168.1.46:7960").build();

        ws = client.newWebSocket(request, new EchoWebSocketListener());
    }

}
