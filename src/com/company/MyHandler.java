package com.company;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sr9000 on 5/4/17.
 */
public class MyHandler implements HttpHandler {
    List<String> myList = new ArrayList<String>();
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = org.apache.commons.io.IOUtils.toString(t.getRequestBody());
        if (t.getRequestMethod().equalsIgnoreCase("get")) {
            response = "This is the response";
        }

        response = "<html><body><p>"
                + response
                + "</p><form method=`post`>First name:<br><input type=`text` name=`firstname`><br>Last name:<br><input type=`text` name=`lastname`><br><input type=`submit`></form></body></html>".replace('`', '"');

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
