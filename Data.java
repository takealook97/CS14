import java.sql.*;
import java.util.Random;

public class Data {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "takealook";
        String password = "0205";
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");


        String[] words = new String[]{"force", "communicate", "develop", "earth", "protect", "signal", "animal", "rainstorm", "stone", "rabbits",
                "matter", "hunt", "channel", "goofy", "red", "smoke", "poor", "equal", "calculating", "impress", "collect", "bear", "fail",
                "gray", "wrench", "diligent", "bikes", "sleepy", "amusement", "theory", "slimy", "unruly", "accidental", "jar", "rub", "quince",
                "muddle", "fade", "hurried", "limit", "foamy", "silly", "influence", "periodic", "feigned", "sugar", "level", "fog", "flowers",
                "unkempt", "happy", "uncovered", "friends", "pathetic", "futuristic", "irritate", "shade", "well", "veil", "cure", "breathe",
                "unknown", "regular", "bite", "excite", "share", "tart", "bang", "poised", "actually", "screeching", "healthy", "spring", "egg",
                "ink", "sloppy", "river", "wanting", "scissors", "famous", "flavor", "puncture", "calm", "toes", "curious", "spiffy", "slim",
                "team", "silk", "ask", "caring", "spiders", "rely", "pancake", "expansion", "dangerous", "taste", "shop", "room", "historical"};
        int count = 0;
        Random rd = new Random();
        while (count < 100) {
            StringBuilder randomString = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                randomString.append((char) (rd.nextInt(26) + 97));//랜덤 문자열 3자리
            }
            StringBuilder randomNumber = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                randomNumber.append(rd.nextInt(10));//랜덤 숫자 4자리
            }

            String nickname = words[rd.nextInt(100)] + randomString + randomNumber;
            int money = rd.nextInt(100000) + 1;
            int[] last_visit;

            System.out.println(randomNumber);


            count++;
        }

        rs.close();
        st.close();
        con.close();
    }
}
