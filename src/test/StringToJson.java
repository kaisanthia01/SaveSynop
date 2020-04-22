package test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Objects.isNull;

public class StringToJson {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String JsonData = Station();
        System.out.println(JsonData);
        FileWriter file = new FileWriter("D:\\DataSynoptic\\JSON\\JsonDataTHAI.json");
        file.write(JsonData);
        file.close();
    }

    public static String Station() throws ClassNotFoundException, SQLException {
        System.out.println("GetStation");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();

        String codeCountryName = "TH";
        String sql = "SELECT STATION_NAME, ICAO, SYNOP, LATTITUDE, LONGITUDE, ELEV, COUNTRY_CODE FROM SYNOP_SITES\n"
                + "WHERE COUNTRY_CODE = '" + codeCountryName + "'";

        String setTableCountryName = null;
        if (codeCountryName.equals("TH")) {
            setTableCountryName = "WEATHER_REPORT";

        } else if (codeCountryName.equals("MM")) {
            setTableCountryName = "WEATHER_REPORT_MYANMAR";

        } else if (codeCountryName.equals("LA")) {
            setTableCountryName = "" + setTableCountryName + "";

        } else if (codeCountryName.equals("VN")) {
            setTableCountryName = "WEATHER_REPORT_VIETNAM";

        } else if (codeCountryName.equals("MY")) {
            setTableCountryName = "WEATHER_REPORT_MALAYSIA";

        } else if (codeCountryName.equals("SG")) {
            setTableCountryName = "WEATHER_REPORT_SINGAPORE";

        } else {

        }

        String json = "";
        json = json + "[";
        //System.out.println("[");
        try {
            //System.out.println(sql);
            //System.out.println();
            ResultSet res = stat.executeQuery(sql);
            String c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, c6 = null, c7 = null;
            int i = 0;
            while ((res.next())) {
                c1 = res.getString("STATION_NAME");
                c2 = res.getString("ICAO");
                c3 = res.getString("SYNOP");
                c4 = res.getString("LATTITUDE");
                c5 = res.getString("LONGITUDE");
                c6 = res.getString("ELEV");
                c7 = res.getString("COUNTRY_CODE");

                System.out.print("No: " + (i + 1) + " | ");
                System.out.print("SYNOP: " + c3 + " | ");
                System.out.print("STATION_NAME: " + c1 + " | ");
                System.out.print("ICAO: " + c2 + " | ");
                System.out.print("LATTITUDE: " + c4 + " | ");
                System.out.print("LONGITUDE: " + c5 + " | ");
                System.out.print("ELEV: " + c6 + " | ");
                System.out.println("COUNTRY_CODE: " + c7);

                String[] AvgTemp = AvgTemp(c3, setTableCountryName);
                String[] AvgDewpoint = AvgDewpoint(c3, setTableCountryName);
                String[] AvgSlp = AvgSlp(c3, setTableCountryName);
                String[] SumRain2015 = SumRain2015(c3, setTableCountryName);
                String[] SumRain2016 = SumRain2016(c3, setTableCountryName);
                String[] SumRain2017 = SumRain2017(c3, setTableCountryName);
                String[] SumRain2018 = SumRain2018(c3, setTableCountryName);
                String[] SumRain2019 = SumRain2019(c3, setTableCountryName);

                json = json + "{";
                json = json + "\"station_name\": \"" + c1 + "\",";
                json = json + "\"icao\": \"" + c2 + "\",";
                json = json + "\"synop\": \"" + c3 + "\",";
                json = json + "\"lattitude\": \"" + c4 + "\",";
                json = json + "\"longitude\": \"" + c5 + "\",";
                json = json + "\"elev\": \"" + c6 + "\",";
                json = json + "\"country_code\": \"" + c7 + "\",";
                json = json + "\"AvgTemp\": {";
                json = json + "\"Jan\": \"" + AvgTemp[0] + "\",";
                json = json + "\"Feb\": \"" + AvgTemp[1] + "\",";
                json = json + "\"Mar\": \"" + AvgTemp[2] + "\",";
                json = json + "\"Apr\": \"" + AvgTemp[3] + "\",";
                json = json + "\"May\": \"" + AvgTemp[4] + "\",";
                json = json + "\"Jun\": \"" + AvgTemp[5] + "\",";
                json = json + "\"Jul\": \"" + AvgTemp[6] + "\",";
                json = json + "\"Aug\": \"" + AvgTemp[7] + "\",";
                json = json + "\"Sep\": \"" + AvgTemp[8] + "\",";
                json = json + "\"Oct\": \"" + AvgTemp[9] + "\",";
                json = json + "\"Nov\": \"" + AvgTemp[10] + "\",";
                json = json + "\"Dec\": \"" + AvgTemp[11] + "\"";
                json = json + "},";
                json = json + "\"AvgDewpoint\": {";
                json = json + "\"Jan\": \"" + AvgDewpoint[0] + "\",";
                json = json + "\"Feb\": \"" + AvgDewpoint[1] + "\",";
                json = json + "\"Mar\": \"" + AvgDewpoint[2] + "\",";
                json = json + "\"Apr\": \"" + AvgDewpoint[3] + "\",";
                json = json + "\"May\": \"" + AvgDewpoint[4] + "\",";
                json = json + "\"Jun\": \"" + AvgDewpoint[5] + "\",";
                json = json + "\"Jul\": \"" + AvgDewpoint[6] + "\",";
                json = json + "\"Aug\": \"" + AvgDewpoint[7] + "\",";
                json = json + "\"Sep\": \"" + AvgDewpoint[8] + "\",";
                json = json + "\"Oct\": \"" + AvgDewpoint[9] + "\",";
                json = json + "\"Nov\": \"" + AvgDewpoint[10] + "\",";
                json = json + "\"Dec\": \"" + AvgDewpoint[11] + "\"";
                json = json + "},";
                json = json + "\"AvgSlp\": {";
                json = json + "\"Jan\": \"" + AvgSlp[0] + "\",";
                json = json + "\"Feb\": \"" + AvgSlp[1] + "\",";
                json = json + "\"Mar\": \"" + AvgSlp[2] + "\",";
                json = json + "\"Apr\": \"" + AvgSlp[3] + "\",";
                json = json + "\"May\": \"" + AvgSlp[4] + "\",";
                json = json + "\"Jun\": \"" + AvgSlp[5] + "\",";
                json = json + "\"Jul\": \"" + AvgSlp[6] + "\",";
                json = json + "\"Aug\": \"" + AvgSlp[7] + "\",";
                json = json + "\"Sep\": \"" + AvgSlp[8] + "\",";
                json = json + "\"Oct\": \"" + AvgSlp[9] + "\",";
                json = json + "\"Nov\": \"" + AvgSlp[10] + "\",";
                json = json + "\"Dec\": \"" + AvgSlp[11] + "\"";
                json = json + "},";
                json = json + "\"SumRain2015\": {";
                json = json + "\"Jan\": \"" + SumRain2015[0] + "\",";
                json = json + "\"Feb\": \"" + SumRain2015[1] + "\",";
                json = json + "\"Mar\": \"" + SumRain2015[2] + "\",";
                json = json + "\"Apr\": \"" + SumRain2015[3] + "\",";
                json = json + "\"May\": \"" + SumRain2015[4] + "\",";
                json = json + "\"Jun\": \"" + SumRain2015[5] + "\",";
                json = json + "\"Jul\": \"" + SumRain2015[6] + "\",";
                json = json + "\"Aug\": \"" + SumRain2015[7] + "\",";
                json = json + "\"Sep\": \"" + SumRain2015[8] + "\",";
                json = json + "\"Oct\": \"" + SumRain2015[9] + "\",";
                json = json + "\"Nov\": \"" + SumRain2015[10] + "\",";
                json = json + "\"Dec\": \"" + SumRain2015[11] + "\"";
                json = json + "},";
                json = json + "\"SumRain2016\": {";
                json = json + "\"Jan\": \"" + SumRain2016[0] + "\",";
                json = json + "\"Feb\": \"" + SumRain2016[1] + "\",";
                json = json + "\"Mar\": \"" + SumRain2016[2] + "\",";
                json = json + "\"Apr\": \"" + SumRain2016[3] + "\",";
                json = json + "\"May\": \"" + SumRain2016[4] + "\",";
                json = json + "\"Jun\": \"" + SumRain2016[5] + "\",";
                json = json + "\"Jul\": \"" + SumRain2016[6] + "\",";
                json = json + "\"Aug\": \"" + SumRain2016[7] + "\",";
                json = json + "\"Sep\": \"" + SumRain2016[8] + "\",";
                json = json + "\"Oct\": \"" + SumRain2016[9] + "\",";
                json = json + "\"Nov\": \"" + SumRain2016[10] + "\",";
                json = json + "\"Dec\": \"" + SumRain2016[11] + "\"";
                json = json + "},";
                json = json + "\"SumRain2017\": {";
                json = json + "\"Jan\": \"" + SumRain2017[0] + "\",";
                json = json + "\"Feb\": \"" + SumRain2017[1] + "\",";
                json = json + "\"Mar\": \"" + SumRain2017[2] + "\",";
                json = json + "\"Apr\": \"" + SumRain2017[3] + "\",";
                json = json + "\"May\": \"" + SumRain2017[4] + "\",";
                json = json + "\"Jun\": \"" + SumRain2017[5] + "\",";
                json = json + "\"Jul\": \"" + SumRain2017[6] + "\",";
                json = json + "\"Aug\": \"" + SumRain2017[7] + "\",";
                json = json + "\"Sep\": \"" + SumRain2017[8] + "\",";
                json = json + "\"Oct\": \"" + SumRain2017[9] + "\",";
                json = json + "\"Nov\": \"" + SumRain2017[10] + "\",";
                json = json + "\"Dec\": \"" + SumRain2017[11] + "\"";
                json = json + "},";
                json = json + "\"SumRain2018\": {";
                json = json + "\"Jan\": \"" + SumRain2018[0] + "\",";
                json = json + "\"Feb\": \"" + SumRain2018[1] + "\",";
                json = json + "\"Mar\": \"" + SumRain2018[2] + "\",";
                json = json + "\"Apr\": \"" + SumRain2018[3] + "\",";
                json = json + "\"May\": \"" + SumRain2018[4] + "\",";
                json = json + "\"Jun\": \"" + SumRain2018[5] + "\",";
                json = json + "\"Jul\": \"" + SumRain2018[6] + "\",";
                json = json + "\"Aug\": \"" + SumRain2018[7] + "\",";
                json = json + "\"Sep\": \"" + SumRain2018[8] + "\",";
                json = json + "\"Oct\": \"" + SumRain2018[9] + "\",";
                json = json + "\"Nov\": \"" + SumRain2018[10] + "\",";
                json = json + "\"Dec\": \"" + SumRain2018[11] + "\"";
                json = json + "},";
                json = json + "\"SumRain2019\": {";
                json = json + "\"Jan\": \"" + SumRain2019[0] + "\",";
                json = json + "\"Feb\": \"" + SumRain2019[1] + "\",";
                json = json + "\"Mar\": \"" + SumRain2019[2] + "\",";
                json = json + "\"Apr\": \"" + SumRain2019[3] + "\",";
                json = json + "\"May\": \"" + SumRain2019[4] + "\",";
                json = json + "\"Jun\": \"" + SumRain2019[5] + "\",";
                json = json + "\"Jul\": \"" + SumRain2019[6] + "\",";
                json = json + "\"Aug\": \"" + SumRain2019[7] + "\",";
                json = json + "\"Sep\": \"" + SumRain2019[8] + "\",";
                json = json + "\"Oct\": \"" + SumRain2019[9] + "\",";
                json = json + "\"Nov\": \"" + SumRain2019[10] + "\",";
                json = json + "\"Dec\": \"" + SumRain2019[11] + "\"";
                json = json + "}";
                json = json + "},";
                i++;
            }

        } catch (SQLException e) {
        }
        stat.close();
        connect.close();
        json = json + "]";
        //System.out.println("]");
        return json;
    }

    public static String[] AvgTemp(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("AvgTemp");
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
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = " + setTableCountryName + ".AIR_TEMP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = " + setTableCountryName + ".AIR_TEMP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(AIR_TEMPERATURE.AIR_TEMP_VALUE) AS AVGTEMP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AIR_TEMPERATURE ON AIR_TEMPERATURE.AIR_TEMP_ID = " + setTableCountryName + ".AIR_TEMP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                double dAVG = 0.0;
                while ((res.next())) {
                    c1 = res.getString("AVGTEMP");
                    if (isNull(c1) || c1.equals("///") || c1.equals("////")) {
                        dAVG = dAVG + 0;

                    } else {
                        dAVG = dAVG + (Double.parseDouble(c1));

                    }
                }

                AvgTemp[(i - 1)] = String.format("%.1f", dAVG);
                //System.out.println("AvgTempMonth : " + Month + " | " + AvgTemp[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgTemp;
    }

    public static String[] AvgDewpoint(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("AvgDewpoint");
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
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM " + setTableCountryName + " \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = " + setTableCountryName + ".DEWPOINT_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM " + setTableCountryName + " \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = " + setTableCountryName + ".DEWPOINT_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(DEWPOINT.DEWPOINT_VALUE) AS AVGDEWPOINT FROM " + setTableCountryName + " \n"
                        + "INNER JOIN DEWPOINT ON DEWPOINT.DEWPOINT_ID = " + setTableCountryName + ".DEWPOINT_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                double dAVG = 0.0;
                while ((res.next())) {
                    c1 = res.getString("AVGDEWPOINT");
                    if (isNull(c1) || c1.equals("///") || c1.equals("////")) {
                        dAVG = dAVG + 0;

                    } else {
                        dAVG = dAVG + (Double.parseDouble(c1));

                    }
                }

                AvgDewpoint[(i - 1)] = String.format("%.1f", dAVG);
                //System.out.println("AvgDewpointMonth : " + Month + " | " + AvgDewpoint[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgDewpoint;
    }

    public static String[] AvgSlp(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("AvgSlp");
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
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = " + setTableCountryName + ".SLP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('29/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = " + setTableCountryName + ".SLP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AVG(SLP.SLP_VALUE) AS AVGSLP FROM " + setTableCountryName + " \n"
                        + "INNER JOIN SLP ON SLP.SLP_ID = " + setTableCountryName + ".SLP_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY') OR \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY') OR  \n"
                        + "" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
                ResultSet res = stat.executeQuery(sql);
                String c1 = null;
                double dAVG = 0.0;
                while ((res.next())) {
                    c1 = res.getString("AVGSLP");
                    if (isNull(c1) || c1.equals("///") || c1.equals("////")) {
                        dAVG = dAVG + 0;

                    } else {
                        dAVG = dAVG + (Double.parseDouble(c1));

                    }
                }

                AvgSlp[(i - 1)] = String.format("%.1f", dAVG);
                //System.out.println("AvgSlpMonth : " + Month + " | " + AvgSlp[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return AvgSlp;
    }

    public static String[] SumRain2015(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("SumRain2015");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] SumRain2015 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2015', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2015', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2015', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2015', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
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

                SumRain2015[(i - 1)] = String.format("%.1f", dSUM);
                //System.out.println("SumRain2015Month : " + Month + " | " + SumRain2015[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return SumRain2015;
    }

    public static String[] SumRain2016(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("SumRain2016");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] SumRain2016 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2016', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2016', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2016', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2016', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
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

                SumRain2016[(i - 1)] = String.format("%.1f", dSUM);
                //System.out.println("SumRain2016Month : " + Month + " | " + SumRain2016[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return SumRain2016;
    }

    public static String[] SumRain2017(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("SumRain2017");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] SumRain2017 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2017', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2017', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2017', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2017', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
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

                SumRain2017[(i - 1)] = String.format("%.1f", dSUM);
                //System.out.println("SumRain2017Month : " + Month + " | " + SumRain2017[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return SumRain2017;
    }

    public static String[] SumRain2018(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("SumRain2018");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
        String[] SumRain2018 = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};

        for (int i = 1; i < 13; i++) {
            String Month;
            String sql = null;
            if (i < 10) {
                Month = "0" + i;
            } else {
                Month = Integer.toString(i);
            }

            if (Month.equals("02")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2018', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2018', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2018', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2018', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
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

                SumRain2018[(i - 1)] = String.format("%.1f", dSUM);
                //System.out.println("SumRain2018Month : " + Month + " | " + SumRain2018[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return SumRain2018;
    }

    public static String[] SumRain2019(String Synop, String setTableCountryName) throws ClassNotFoundException, SQLException {
        System.out.println("SumRain2019");
        Connection connect = new CheckConnectDatabase().connect();
        Statement stat = connect.createStatement();
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
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('28/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('30/" + Month + "/2019', 'DD/MM/YYYY'))";

            } else {
                sql = "SELECT AMONTH_PAST24H.AMONTH_PAST24H_VALUE AS SUMRAIN FROM " + setTableCountryName + " \n"
                        + "INNER JOIN AMONTH_PAST24H ON AMONTH_PAST24H.AMONTH_PAST24H_ID = " + setTableCountryName + ".AMONTH_PAST24H_ID \n"
                        + "WHERE " + setTableCountryName + ".STATION_ID = '" + Synop + "' AND \n"
                        + "(" + setTableCountryName + ".REPORT_TIME BETWEEN TO_DATE('01/" + Month + "/2019', 'DD/MM/YYYY') AND TO_DATE('31/" + Month + "/2019', 'DD/MM/YYYY'))";

            }
            try {
                //System.out.println(sql);
                //System.out.println();
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

                SumRain2019[(i - 1)] = String.format("%.1f", dSUM);
                //System.out.println("SumRain2019Month : " + Month + " | " + SumRain2019[(i - 1)]);

            } catch (SQLException e) {
            }
        }
        stat.close();
        connect.close();
        return SumRain2019;
    }
}
