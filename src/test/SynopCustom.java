package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Objects.isNull;

public class SynopCustom {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String[][] SumRain = SumRain();
        System.out.println("SumRain : " + SumRain);
    }

    public static String[][] SumRain() throws ClassNotFoundException, SQLException {
        System.out.println("SumRain");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String SumRain[][] = new String[7][10];
        String[] Synop = {"48327", "48378", "48354", "48407", "48431", "48400", "48426"};
        String sql = null, year = null;
        for (int s = 0; s < 1; s++) {
            for (int m = 9; m < 20; m++) {
                if (m < 10) {
                    year = "0" + m;
                } else {
                    year = Integer.toString(m);
                }
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM WEATHER_REPORT \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = WEATHER_REPORT.AMONTH_PAST24H_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '" + Synop[s] + "' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/04/20" + year + "', 'DD/MM/YYYY') AND TO_DATE('30/04/20" + year + "', 'DD/MM/YYYY'))";

                System.out.println(sql);
                try {
                    ResultSet res = stat.executeQuery(sql);
                    String c1 = null;
                    double dSUM = 0.0;
                    while ((res.next())) {
                        c1 = res.getString("SUMRAIN");
                        if (isNull(c1) || c1.equals("T") || c1.equals("t") || c1.equals("///") || c1.equals("////")) {
                            dSUM = dSUM + 0;

                        } else {
                            dSUM = dSUM + (Double.parseDouble(c1));

                        }
                    }

                    SumRain[s][1] = String.format("%.1f", dSUM);

                } catch (SQLException e) {
                }
            }
        }
        stat.close();
        connect.close();
        return SumRain;
    }
}
