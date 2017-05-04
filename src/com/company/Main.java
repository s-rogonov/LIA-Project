package com.company;

import com.sun.net.httpserver.HttpServer;
import com.company.MyHandler;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.start();
    }
}
