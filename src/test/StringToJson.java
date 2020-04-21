package test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class StringToJson {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        /*String[] AvgTemp = AvgTemp();
        System.out.println(Arrays.toString(AvgTemp));
        
        String[] AvgDewpoint = AvgDewpoint();
        System.out.println(Arrays.toString(AvgDewpoint));*/
        
        String[] AvgSlp = AvgSlp();
        System.out.println(Arrays.toString(AvgSlp));
        /*String Station = null;
            FileWriter file = new FileWriter("D:\\NetbeansProject\\TestScript\\test.json");
            file.write(Station);*/
    }

    public static String[] AvgTemp() throws ClassNotFoundException, SQLException {
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] AvgTemp = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
        
        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM WEATHER_REPORT \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = WEATHER_REPORT.AIR_TEMP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM WEATHER_REPORT \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = WEATHER_REPORT.AIR_TEMP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM WEATHER_REPORT \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = WEATHER_REPORT.AIR_TEMP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                while ((res != null) && (res.next())) {
                    c1 = res.getString("AVGTEMP");
                }

                double dAVG = Double.parseDouble(c1);
                AvgTemp[(i - 1)] = String.format("%.1f", dAVG);
                System.out.println("AvgTempMonth : " + Month + " | " + AvgTemp[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgTemp;
    }
    
    public static String[] AvgDewpoint() throws ClassNotFoundException, SQLException {
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] AvgDewpoint = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM WEATHER_REPORT \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = WEATHER_REPORT.DEWPOINT_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM WEATHER_REPORT \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = WEATHER_REPORT.DEWPOINT_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM WEATHER_REPORT \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = WEATHER_REPORT.DEWPOINT_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                while ((res != null) && (res.next())) {
                    c1 = res.getString("AVGDEWPOINT");
                }

                double dAVG = Double.parseDouble(c1);
                AvgDewpoint[(i - 1)] = String.format("%.1f", dAVG);
                System.out.println("AvgDewpointMonth : " + Month + " | " + AvgDewpoint[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgDewpoint;
    }
    
    public static String[] AvgSlp() throws ClassNotFoundException, SQLException {
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] AvgSlp = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                while ((res != null) && (res.next())) {
                    c1 = res.getString("AVGSLP");
                }

                double dAVG = Double.parseDouble(c1);
                AvgSlp[(i - 1)] = String.format("%.1f", dAVG);
                System.out.println("AvgSlpMonth : " + Month + " | " + AvgSlp[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgSlp;
    }
    
    public static String[] SumRain2015() throws ClassNotFoundException, SQLException {
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] SumRain2015 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
        String[] SumRain2016 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
        String[] SumRain2017 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
        String[] SumRain2018 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
        String[] SumRain2019 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM WEATHER_REPORT \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = WEATHER_REPORT.SLP_ID \n"
                        + "WHERE WEATHER_REPORT.STATION_ID = '48456' AND \n"
                        + "(WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "WEATHER_REPORT.REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                while ((res != null) && (res.next())) {
                    c1 = res.getString("AVGSLP");
                }

                double dAVG = Double.parseDouble(c1);
                AvgSlp[(i - 1)] = String.format("%.1f", dAVG);
                System.out.println("AvgSlpMonth : " + Month + " | " + AvgSlp[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgSlp;
    }
}
