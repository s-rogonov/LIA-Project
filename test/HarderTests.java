import org.junit.Test;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

/**
 * Created by sr9000 on 5/9/17.
 */
public class HarderTests {
    @Test
    public void urldecoding() throws Exception {
        String url = "%23%24%25%5E%24V%24%26UNB%5E%26%25";
        String orig = "#$%^$V$&UNB^&%";
        assertEquals("URL decoding", orig.hashCode(), URLDecoder.decode(url, "UTF-8").hashCode());
    }

    @Test
    public void sitebuilder() throws Exception {
        StringBuilder response = new StringBuilder("Chat is active");
        response = new StringBuilder("<html><body><p>"
                + response.toString()
                + "</p><form method=`post`>First name:<br><input type=`text` name=`firstname`><br>Last name:<br><input type=`text` name=`lastname`><br>Your answer?<input type=`text` name=`post` width=`150`><br><input type=`submit`></form>".replace('`', '"'));

        assertEquals("URL decoding", 2140442023, response.toString().hashCode());
    }

    @Test
    public void db() throws Exception {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chat", "app", "test");
            con.close();
        } catch (Exception e) {
            assertEquals("DB connecting", 1, -1);
        }
        assertEquals("DB connecting", 1, 1);
    }

    @Test
    public void pushdb() throws Exception {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chat", "app", "test");
            PreparedStatement stmt = con.prepareStatement("insert into posts (firts, last, msg) values (?, ?, ?)");
            stmt.setString(1, "fist");
            stmt.setString(2, "last");
            stmt.setString(3, "message, hi!");
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (Exception e) {
            assertEquals("DB request executing", 1, -1);
        }
        assertEquals("DB request executing", 1, 1);
    }
}
