package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

public class SynopticSave {

    public static void main(String[] args)
            throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        for (int y = 2015; y < 2020; y++) {
            int yThai = y + 543;
            for (int m = 0; m < 12; m++) {
                for (int d = 1; d < 32; d++) {
                    for (int h = 0; h < 8; h++) {
                        // Set Day
                        String Day = null;
                        if (d < 10) {
                            Day = "0" + d;
                        } else {
                            Day = Integer.toString(d);
                        }

                        // Set Month
                        String Month = null;
                        if (m == 0) {
                            Month = "JAN";
                        } else if (m == 1) {
                            Month = "FEB";
                        } else if (m == 2) {
                            Month = "MAR";
                        } else if (m == 3) {
                            Month = "APR";
                        } else if (m == 4) {
                            Month = "MAY";
                        } else if (m == 5) {
                            Month = "JUN";
                        } else if (m == 6) {
                            Month = "JUL";
                        } else if (m == 7) {
                            Month = "AUG";
                        } else if (m == 8) {
                            Month = "SEP";
                        } else if (m == 9) {
                            Month = "OCT";
                        } else if (m == 10) {
                            Month = "NOV";
                        } else {
                            Month = "DEC";
                        }

                        // Set Year
                        String Year = Integer.toString(y);
                        Year = (Year.substring(2, 4));

                        // Set Hour
                        String[] time = {"00", "03", "06", "09", "12", "15", "18", "21"};

                        //1.(TH)THAILAND
                        //String country_code = "48";
                        //String codeCountryName = "TH";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VTBB&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VTBB");
                        //System.out.println(strURL);
                        //2.(MM)MYANMAR
                        //String country_code = "48";
                        //String codeCountryName = "MM";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VBRR&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VBRR");
                        //3.(LA)LAOS
                        //String country_code = "48";
                        //String codeCountryName = "LA";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VLIV&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VLIV");
                        //4.(VN)VIETNAM
                        //String country_code = "48";
                        //String codeCountryName = "VN";
                        //String strURL= ("http://www.gts.tmd.go.th/examtest/all.php?ld=VTBB&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VNNN");
                        //5.(MY)MALAYSIA
                        //String country_code = "4896";
                        //String codeCountryName = "MY";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=WMKK&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=WMKK");
                        //6.(SG)SINGAPORE
                        String country_code = "48";
                        String codeCountryName = "SG";
                        String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=WSSS&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=WSSS");

                        URL synop = new URL(strURL);
                        CheckConnectURL checkStatus = new CheckConnectURL();
                        boolean status = checkStatus.check(strURL);
                        if (status == true) {
                            System.out.println("URL Connection Success->" + strURL);
                            BufferedReader br = new BufferedReader(new InputStreamReader(synop.openStream()));
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append(System.getProperty("line.separator"));
                            }
                            br.close();

                            String nohtml = sb.toString().replaceAll("\\<.*?>", "");
                            Year = Integer.toString(y);

                            try (FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + Day
                                    + Month + Year + time[h] + "_" + codeCountryName + ".txt")) {
                                fw.write(nohtml);
                                fw.close();
                            } catch (Exception e) {
                                // System.out.println(e);
                            }
                            CheckSynoptic.checkSynoptic(Day, Month, Year, time[h], codeCountryName, country_code);

                        } else {
                            System.out.println("URL Connection Failed->" + strURL);
                        }
                    }
                }
            }
        }
    }
}
