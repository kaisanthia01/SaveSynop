package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class CheckImportData {

    CheckConnectDatabase dbCon = new CheckConnectDatabase();

    void addToArray(String path, int countRecord) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Connection con = dbCon.connect();
        Statement stat = con.createStatement();
        String sql = ("SELECT * FROM WEATHER_REPORT_MYANMAR WHERE ROWNUM = 1 ORDER BY REPORT_ID DESC");
        ResultSet res = stat.executeQuery(sql);
        String c1 = null;
        while (res.next()) {
            c1 = res.getString("REPORT_ID");
        }
        //System.out.println(c1);
        //System.out.println(path);
        //System.out.println(countRecord);

        if (c1 == null) {
            c1 = "0";
            //System.out.println(c1);
        } else {
        }

        FileReader read = new FileReader(path);
        BufferedReader buffer = new BufferedReader(read);
        boolean eof = false;
        String fileData = "";
        int numRow = 0;
        int report_id = Integer.parseInt(c1);
        report_id++;
        String[][] dataArray = new String[countRecord][43];
        while (!eof) {
            fileData = buffer.readLine();
            if (fileData == null) {
                eof = true;
            } else {
                dataArray[numRow][0] = Integer.toString(report_id);
                StringTokenizer str1 = new StringTokenizer(fileData, " ");
                for (int j = 1; j < 43; j++) {
                    dataArray[numRow][j] = str1.nextToken();
                }
                //System.out.println(fileData);
            }
            numRow++;
            report_id++;
        }
        buffer.close();
        read.close();
        for (int i = 0; i < countRecord; i++) {
            for (int j = 0; j < 43; j++) {
                //System.out.print(dataArray[i][j] + " ");
            }
        }

        for (int i = 0; i < countRecord; i++) {
            String Day = dataArray[i][1].substring(0, 2);
            String Month = dataArray[i][1].substring(3, 6);
            if (Month.equals("JAN")) {
                Month = "01";
            } else if (Month.equals("FEB")) {
                Month = "02";
            } else if (Month.equals("MAR")) {
                Month = "03";
            } else if (Month.equals("APR")) {
                Month = "04";
            } else if (Month.equals("MAY")) {
                Month = "05";
            } else if (Month.equals("JUN")) {
                Month = "06";
            } else if (Month.equals("JUL")) {
                Month = "07";
            } else if (Month.equals("AUG")) {
                Month = "08";
            } else if (Month.equals("SEP")) {
                Month = "09";
            } else if (Month.equals("OCT")) {
                Month = "10";
            } else if (Month.equals("NOV")) {
                Month = "11";
            } else {
                Month = "12";
            }
            String Year = dataArray[i][1].substring(7, 11);
            dataArray[i][1] = Day + "-" + Month + "-" + Year;

            try {
                stat.execute("INSERT INTO WEATHER_REPORT_MYANMAR VALUES('" + dataArray[i][0]
                        + "',to_date('" + dataArray[i][1] + "','dd/mm/yyyy'),'" + dataArray[i][2] + "','" + dataArray[i][3]
                        + "','" + dataArray[i][4] + "','" + dataArray[i][5] + "','" + dataArray[i][6] + "','" + dataArray[i][7]
                        + "','" + dataArray[i][8] + "','" + dataArray[i][9] + "','" + dataArray[i][10] + "','" + dataArray[i][11]
                        + "','" + dataArray[i][12] + "','" + dataArray[i][13] + "','" + dataArray[i][14] + "','" + dataArray[i][15]
                        + "','" + dataArray[i][16] + "','" + dataArray[i][17] + "','" + dataArray[i][18] + "','" + dataArray[i][21]
                        + "','" + dataArray[i][22] + "','" + dataArray[i][23] + "','" + dataArray[i][24] + "','" + dataArray[i][25]
                        + "','" + dataArray[i][26] + "','" + dataArray[i][27] + "','" + dataArray[i][28] + "','" + dataArray[i][29]
                        + "','" + dataArray[i][30] + "','" + dataArray[i][31] + "','" + dataArray[i][32] + "','" + dataArray[i][33]
                        + "','" + dataArray[i][34] + "','" + dataArray[i][35] + "','" + dataArray[i][36] + "','" + dataArray[i][37]
                        + "','" + dataArray[i][38] + "','" + dataArray[i][39] + "','" + dataArray[i][40] + "','" + dataArray[i][41]
                        + "','" + dataArray[i][42] + "')");

                System.out.println("INSERT INTO WEATHER_REPORT_MYANMAR VALUES('" + dataArray[i][0]
                        + "',to_date('" + dataArray[i][1] + "','dd/mm/yyyy'),'" + dataArray[i][2] + "','" + dataArray[i][3]
                        + "','" + dataArray[i][4] + "','" + dataArray[i][5] + "','" + dataArray[i][6] + "','" + dataArray[i][7]
                        + "','" + dataArray[i][8] + "','" + dataArray[i][9] + "','" + dataArray[i][10] + "','" + dataArray[i][11]
                        + "','" + dataArray[i][12] + "','" + dataArray[i][13] + "','" + dataArray[i][14] + "','" + dataArray[i][15]
                        + "','" + dataArray[i][16] + "','" + dataArray[i][17] + "','" + dataArray[i][18] + "','" + dataArray[i][21]
                        + "','" + dataArray[i][22] + "','" + dataArray[i][23] + "','" + dataArray[i][24] + "','" + dataArray[i][25]
                        + "','" + dataArray[i][26] + "','" + dataArray[i][27] + "','" + dataArray[i][28] + "','" + dataArray[i][29]
                        + "','" + dataArray[i][30] + "','" + dataArray[i][31] + "','" + dataArray[i][32] + "','" + dataArray[i][33]
                        + "','" + dataArray[i][34] + "','" + dataArray[i][35] + "','" + dataArray[i][36] + "','" + dataArray[i][37]
                        + "','" + dataArray[i][38] + "','" + dataArray[i][39] + "','" + dataArray[i][40] + "','" + dataArray[i][41]
                        + "','" + dataArray[i][42] + "')");

            } catch (SQLException e) {
                System.out.println(e);
                continue;
            }
        }
        con.close();
        stat.close();
        System.out.println("import data Success");
    }
}
