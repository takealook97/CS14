import java.sql.*;
import java.util.Random;

public class Data {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "takealook";
        String password = "0205";
        Connection con = null;
        PreparedStatement pst = null;
        String SQL = "INSERT INTO user_log VALUES (?, ?, ?)";

//        ResultSet rs = null;
        String[] words = {"force", "communicate", "develop", "earth", "protect", "signal", "animal", "rainstorm", "stone", "rabbits",
                "matter", "hunt", "channel", "goofy", "red", "smoke", "poor", "equal", "calculating", "impress", "collect", "bear", "fail",
                "gray", "wrench", "diligent", "bikes", "sleepy", "amusement", "theory", "slimy", "unruly", "accidental", "jar", "rub", "quince",
                "muddle", "fade", "hurried", "limit", "foamy", "silly", "influence", "periodic", "feigned", "sugar", "level", "fog", "flowers",
                "unkempt", "happy", "uncovered", "friends", "pathetic", "futuristic", "irritate", "shade", "well", "veil", "cure", "breathe",
                "unknown", "regular", "bite", "excite", "share", "tart", "bang", "poised", "actually", "screeching", "healthy", "spring", "egg",
                "ink", "sloppy", "river", "wanting", "scissors", "famous", "flavor", "puncture", "calm", "toes", "curious", "spiffy", "slim",
                "team", "silk", "ask", "caring", "spiders", "rely", "pancake", "expansion", "dangerous", "taste", "shop", "room", "historical"};
        int count = 1;
        Random rd = new Random();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            con.setAutoCommit(false);
            pst = con.prepareStatement(SQL);

            while (count <= 1000000) {
                String nickname;
                int money;
                String lastVisit;

                StringBuilder randomString = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    randomString.append((char) (rd.nextInt(26) + 97));//랜덤 문자열 3자리
                }
                StringBuilder randomNumber = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    randomNumber.append(rd.nextInt(10));//랜덤 숫자 4자리
                }
                nickname = words[rd.nextInt(100)] + randomString + randomNumber;

                money = rd.nextInt(100000) + 1;

                String[] dateTime = new String[6];//YYYY-MM-DD HH:MM:SS
                dateTime[0] = Integer.toString(2023);
                dateTime[1] = String.format("%02d", rd.nextInt(2) + 1);
                if (dateTime[1].equals("01")) {//1월일 경우
                    dateTime[2] = Integer.toString(rd.nextInt(11) + 21);//21~31일
                } else if (dateTime[1].equals("02")) {//2월일 경우
                    dateTime[2] = String.format("%02d", rd.nextInt(21) + 1);//1~21일
                }
                dateTime[3] = String.format("%02d", rd.nextInt(24));
                dateTime[4] = String.format("%02d", rd.nextInt(60));
                dateTime[5] = String.format("%02d", rd.nextInt(60));
                lastVisit = dateTime[0] + "-" + dateTime[1] + "-" + dateTime[2] + " " + dateTime[3] + ":" + dateTime[4] + ":" + dateTime[5];


//            pst.setString(1, nickname), (2, String.valueOf(money)), (3, lastVisit);
                pst.setString(1, nickname);
                pst.setString(2, String.valueOf(money));
                pst.setString(3, lastVisit);
                pst.addBatch();
                pst.clearParameters();
                if (count % 10000 == 0) {
                    pst.executeBatch();
                    pst.clearBatch();
                    con.commit();
                }

                System.out.println(nickname);
                System.out.println(money);
                System.out.println(lastVisit);
                System.out.println("status : " + count + "\n");

                count++;
            }

            pst.executeBatch();
            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                if (pst != null) try {
                    pst.close();
                    pst = null;
                } catch (SQLException ex) {
                }
                if (con != null) try {
                    con.close();
                    con = null;
                } catch (SQLException ex) {
                }
            }
//        rs.close();
            pst.close();
            con.close();
        }
    }
}
