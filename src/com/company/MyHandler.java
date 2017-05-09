package com.company;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sr9000 on 5/4/17.
 */

public class MyHandler implements HttpHandler {
    private MysqlCon con = new MysqlCon();
    @Override
    public void handle(HttpExchange t) throws IOException {
        StringBuilder response = new StringBuilder(org.apache.commons.io.IOUtils.toString(t.getRequestBody()));
        HashMap<String,String> args = new HashMap<>();
        if (t.getRequestMethod().equalsIgnoreCase("post")) {
            String[] pairs = response.toString().split("&");
            for (String pair : pairs) {
                String[] grp = pair.split("=");
                args.put(grp[0].toLowerCase(), grp[1]);
            }
            con.pushPost(
                      args.get("firstname")
                    , args.get("lastname")
                    , URLDecoder.decode(args.get("post"), "UTF-8"));
        }

        response = new StringBuilder("Chat is active");
        response = new StringBuilder("<html><body><p>"
                + response.toString()
                + "</p><form method=`post`>First name:<br><input type=`text` name=`firstname`><br>Last name:<br><input type=`text` name=`lastname`><br>Your answer?<input type=`text` name=`post` width=`150`><br><input type=`submit`></form>".replace('`', '"'));

        List<Post> myList = con.getPosts();
        for (Post p:
             myList) {
            response.append("<p><b>").append(p.first).append(" ").append(p.second).append("</b>: ").append(p.text).append("</p>");
        }

        response.append("</body></html>");

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
