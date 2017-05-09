package com.company; /**
 * Created by sr9000 on 5/9/17.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCon {
    private Connection con;
    public MysqlCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://mysqldb:3306/chat", "app", "insertsecrethere");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Post> getPosts()
    {
        List<Post> posts = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from posts order by id desc limit 10");
            while (rs.next()) {
                Post post = new Post();
                post.first = rs.getString(1);
                post.second = rs.getString(2);
                post.text = rs.getString(3);
                posts.add(post);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return posts;
    }

    public void pushPost(String first, String last, String msg)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("insert into posts (firts, last, msg) values (?, ?, ?)");
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, msg);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
