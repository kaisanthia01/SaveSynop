package testsave;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

/**
 *
 * @author DAOC-WEATHER-KRIT
 */
public class TestSave {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        // TODO code application logic here
        for (int y = 2016; y < 2017; y++) {
            int yThai = y + 543;
            for (int m = 9; m < 10; m++) {
                for (int d = 31; d < 32; d++) {
                    for (int h = 2; h < 3; h++) {
                        //Set Day
                        String Day = null;
                        if (d < 10) {
                            Day = "0" + d;
                        } else {
                            Day = Integer.toString(d);
                        }

                        //Set Month
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

                        //Set Year
                        String Year = Integer.toString(y);
                        Year = (Year.substring(2, 4));

                        //Set Hour
                        String[] time = {"00", "03", "06", "09", "12", "15", "18", "21"};

                        //1.(TH)THAILAND
                        //String country_code = "48";
                        //String codeCountryName = "TH";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VTBB&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VTBB");
                        //2.(MM)MYANMAR
                        String country_code = "48";
                        String codeCountryName = "MM";
                        String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VBRR&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VBRR");

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
                        //String country_code = "48";
                        //String codeCountryName = "SG";
                        //String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=WSSS&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=WSSS");
                        //7.(ID)INDONESIA
                        //String country_code = "9697";
                        //String codeCountryName = "ID";
                        //String strURL = "http://www.gts.tmd.go.th/examtest/all.php?ld=WIIX&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=WIIX";
                        URL synop = new URL(strURL);

                        //URL synop = new URL("http://www.gts.tmd.go.th/examtest/all.php?ld=WIIX&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=WIIX");
                        //////System.out.println(synop);
                        ////System.out.println("-----");
                        testCheck checkStatus = new testCheck();
                        boolean status = checkStatus.check(strURL);
                        if (status == true) {
                            System.out.println("URL Connection Success->" + strURL);
                            BufferedReader br = new BufferedReader(new InputStreamReader(synop.openStream()));
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                //sb.append(line);
                                // or
                                sb.append(line).append(System.getProperty("line.separator"));
                            }
                            br.close();

                            String nohtml = sb.toString().replaceAll("\\<.*?>", "");
                            ////System.out.println(nohtml);
                            Year = Integer.toString(y);

                            try (FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + Day + Month + Year + time[h] + "_" + codeCountryName + ".txt")) {
                                fw.write(nohtml);
                                fw.close();
                            } catch (Exception e) {
                                //System.out.println(e);
                            }
                            select(Day, Month, Year, time[h], codeCountryName, country_code);

                        } else {
                            System.out.println("URL Connection Faild->" + strURL);
                        }
                    }
                }
            }
        }
    }

    private static void select(String day, String monthText, String year, String hour, String codeCountryName, String country_code) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        FileReader fr = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line, line2, line3, line4, line5, line6, line7, keepData = null;
        FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_select.txt");
        int countData = 0;

        //System.out.println("---------------------------------------------keepData---------------------------------------");
        while ((line = br.readLine()) != null) {
            try {
                if (country_code.length() > 2) {
                    String code_1 = (country_code.substring(0, 2));
                    String code_2 = (country_code.substring(2, 4));

                    if (line.substring(0, 2).equals(code_1) || line.substring(0, 2).equals(code_2)) {
                        int line1Length = line.length();
                        int Beforeline1Length = line1Length - 1;
                        if (line.substring(Beforeline1Length, line1Length).equals(" ")) {
                            line = line.substring(0, Beforeline1Length);
                        } else {

                        }

                        if (!line.substring(6, 9).equals("NIL") && !line.substring(6, 10).equals(" NIL") && !line.substring(6, 12).equals("   NIL") && !line.substring(6, 13).equals("   NIL=")) {
                            line2 = br.readLine();
                            int line2Length = line2.length();
                            if (line2Length > 0) { //ข่าว2บรรทัด
                                ////System.out.println("มีข่าวบรรทัดที่ 2 ");
                                // keepData = line + line2.substring(1);
                                if (line2.substring(0, 1).equals("6")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 1).equals("7")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("10")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("1/")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("20")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("2/")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("55")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("56")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("57")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("58")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 2).equals("59")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 3).equals("333")) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 4).equals(" 333")) {
                                    keepData = line + " " + line2.substring(1);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 5).equals("  333")) {
                                    keepData = line + " " + line2.substring(2);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 6).equals("   333")) {
                                    keepData = line + " " + line2.substring(3);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line2.substring(0, 11).equals("        333")) {
                                    keepData = line + " " + line2.substring(8);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else {
                                    /*keepData = line + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                    
                                    else if (!line2.substring(0, 2).equals(code_1) || line2.substring(0, 2).equals(code_2)) {
                                    keepData = line + " " + line2;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                    }
                                     */
                                }

                            } else if (line2Length == 0) { //ข่าว3บรรทัด
                                line3 = br.readLine();
                                if (line3.substring(0, 2).equals(code_1) || line3.substring(0, 2).equals(code_2)) {
                                    if (!line3.substring(6, 9).equals("NIL") && !line3.substring(6, 10).equals(" NIL") && !line3.substring(6, 12).equals("   NIL") && !line3.substring(6, 13).equals("   NIL=")) {
                                        keepData = line;
                                        fw.write(day + hour + "00 " + keepData);
                                        fw.write("\n");
                                        line4 = br.readLine();
                                        int line4Length = line4.length();
                                        if (line4Length > 0) { //ข่าว2บรรทัด
                                            if (line4.substring(0, 1).equals("6")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 1).equals("7")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("10")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("1/")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("20")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("2/")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("55")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("56")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("57")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("58")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 2).equals("59")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 3).equals("333")) {
                                                keepData = line3 + " " + line4;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 4).equals(" 333")) {
                                                keepData = line3 + " " + line4.substring(1);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 5).equals("  333")) {
                                                keepData = line3 + " " + line4.substring(2);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 6).equals("   333")) {
                                                keepData = line3 + " " + line4.substring(3);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line4.substring(0, 11).equals("        333")) {
                                                keepData = line3 + " " + line4.substring(8);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else {
                                                /*
                                             else if (!line4.substring(0, 2).equals(country_code)) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                            }
                                                 */
                                            }
                                        } else if (line4Length == 0) {
                                            line5 = br.readLine();
                                            //System.out.println(line5);
                                            if (line5.substring(0, 1).equals("6")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 1).equals("7")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("10")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("1/")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("20")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("2/")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("55")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("56")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("57")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("58")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals("59")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 3).equals("333")) {
                                                keepData = line3 + " " + line5;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 4).equals(" 333")) {
                                                keepData = line3 + " " + line5.substring(1);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 5).equals("  333")) {
                                                keepData = line3 + " " + line5.substring(2);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 6).equals("   333")) {
                                                keepData = line3 + " " + line5.substring(3);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 11).equals("        333")) {
                                                keepData = line3 + " " + line5.substring(8);
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");
                                            } else if (line5.substring(0, 2).equals(code_1) || line5.substring(0, 2).equals(code_2)) {
                                                keepData = line3;
                                                fw.write(day + hour + "00 " + keepData);
                                                fw.write("\n");

                                                line6 = br.readLine();
                                                int line6Length = line6.length();
                                                if (line6Length > 0) { //ข่าว2บรรทัด
                                                    if (line6.substring(0, 1).equals("6")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 1).equals("7")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("10")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("1/")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("20")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("2/")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("55")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("56")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("57")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("58")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 2).equals("59")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 3).equals("333")) {
                                                        keepData = line5 + " " + line6;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 4).equals(" 333")) {
                                                        keepData = line5 + " " + line6.substring(1);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 5).equals("  333")) {
                                                        keepData = line5 + " " + line6.substring(2);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 6).equals("   333")) {
                                                        keepData = line5 + " " + line6.substring(3);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line6.substring(0, 11).equals("        333")) {
                                                        keepData = line5 + " " + line6.substring(8);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else {

                                                    }
                                                } else if (line6Length == 0) {
                                                    line7 = br.readLine();
                                                    //System.out.println("Line7->" + line7);
                                                    if (line7.substring(0, 1).equals("6")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 1).equals("7")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("10")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("1/")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("20")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("2/")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("55")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("56")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("57")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("58")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 2).equals("59")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 3).equals("333")) {
                                                        keepData = line5 + " " + line7;
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 4).equals(" 333")) {
                                                        keepData = line5 + " " + line7.substring(1);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 5).equals("  333")) {
                                                        keepData = line5 + " " + line7.substring(2);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 6).equals("   333")) {
                                                        keepData = line5 + " " + line7.substring(3);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else if (line7.substring(0, 11).equals("        333")) {
                                                        keepData = line5 + " " + line7.substring(8);
                                                        fw.write(day + hour + "00 " + keepData);
                                                        fw.write("\n");
                                                    } else {

                                                    }
                                                } else {

                                                }
                                            } else {
                                                /* 
                                            if (!line5.substring(0, 2).equals(country_code)) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                            } 
                                                 */
                                            }
                                        } else {

                                        }
                                    }
                                } else if (line3.substring(0, 1).equals("6")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 1).equals("7")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("10")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("1/")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("20")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("2/")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("55")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("56")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("57")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("58")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 2).equals("59")) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 3).equals("333")) {
                                    keepData = line + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 4).equals(" 333")) {
                                    keepData = line + " " + line3.substring(1);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 5).equals("  333")) {
                                    keepData = line + " " + line3.substring(2);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 6).equals("   333")) {
                                    keepData = line + " " + line3.substring(3);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else if (line3.substring(0, 11).equals("        333")) {
                                    keepData = line + " " + line3.substring(8);
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                } else {
                                    /*
                                    else if (!line3.substring(0, 2).equals(code_1) || line3.substring(0, 2).equals(code_2)) {
                                    keepData = line + " " + line3;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                    }
                                     */
                                }
                            } else {
                                keepData = line + "Error line3 ";
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            }
                        }
                    }
                } else if (line.substring(0, 2).equals(country_code)) {
                    //System.out.println("country_code " + line.substring(0, 5));
                    int line1Length = line.length();
                    int Beforeline1Length = line1Length - 1;
                    if (line.substring(Beforeline1Length, line1Length).equals(" ")) {
                        line = line.substring(0, Beforeline1Length);
                    } else {

                    }
                    //System.out.println("Line->" + line);
                    if (!line.substring(6, 9).equals("NIL") && !line.substring(6, 10).equals(" NIL") && !line.substring(6, 11).equals("  NIL") && !line.substring(6, 12).equals("   NIL")) {
                        line2 = br.readLine();
                        int line2Length = line2.length();
                        if (line2Length > 0) { //ข่าว2บรรทัด
                            //System.out.println("Line2->" + line2);
                            ////System.out.println("มีข่าวบรรทัดที่ 2");
                            // keepData = line + line2.substring(1);
                            if (line2.substring(0, 1).equals("6")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 1).equals("7")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("10")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("1/")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("20")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("2/")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("55")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("56")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("57")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("58")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 2).equals("59")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 3).equals("333")) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 4).equals(" 333")) {
                                keepData = line + " " + line2.substring(1);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 5).equals("  333")) {
                                keepData = line + " " + line2.substring(2);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 6).equals("   333")) {
                                keepData = line + " " + line2.substring(3);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line2.substring(0, 11).equals("        333")) {
                                keepData = line + " " + line2.substring(8);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else {
                                /*keepData = line + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                                
                                else if (!line2.substring(0, 2).equals(country_code)) {
                                keepData = line + " " + line2;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                                }
                                 */
                            }

                        } else if (line2Length == 0) { //ข่าว3บรรทัด
                            line3 = br.readLine();
                            //System.out.println("Line3->" + line3);
                            //System.out.println(line3.substring(0, 2));

                            if (line3.substring(0, 2).equals(country_code)) {
                                //System.out.println("line3 เท่ากับ รหัสเมือง");
                                if (!line3.substring(6, 9).equals("NIL") && !line3.substring(6, 10).equals(" NIL") && !line3.substring(6, 12).equals("   NIL") && !line3.substring(6, 13).equals("   NIL=")) {
                                    keepData = line;
                                    fw.write(day + hour + "00 " + keepData);
                                    fw.write("\n");
                                    line4 = br.readLine();
                                    int line4Length = line4.length();
                                    if (line4Length > 0) { //ข่าว2บรรทัด
                                        if (line4.substring(0, 1).equals("6")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 1).equals("7")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("10")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("1/")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("20")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("2/")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("55")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("56")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("57")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("58")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("59")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 3).equals("333")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 4).equals(" 333")) {
                                            keepData = line3 + " " + line4.substring(1);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 5).equals("  333")) {
                                            keepData = line3 + " " + line4.substring(2);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 6).equals("   333")) {
                                            keepData = line3 + " " + line4.substring(3);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 11).equals("        333")) {
                                            keepData = line3 + " " + line4.substring(8);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else {
                                            /*
                                             else if (!line4.substring(0, 2).equals(country_code)) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                            }
                                             */
                                        }
                                    } else if (line4Length == 0) {
                                        line5 = br.readLine();
                                        //System.out.println("Line5->" + line5);
                                        //System.out.println(line5);
                                        if (line5.substring(0, 1).equals("6")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 1).equals("7")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("10")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("1/")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("20")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("2/")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("55")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("56")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("57")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("58")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("59")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 3).equals("333")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 4).equals(" 333")) {
                                            keepData = line3 + " " + line5.substring(1);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 5).equals("  333")) {
                                            keepData = line3 + " " + line5.substring(2);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 6).equals("   333")) {
                                            keepData = line3 + " " + line5.substring(3);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 11).equals("        333")) {
                                            keepData = line3 + " " + line5.substring(8);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals(country_code)) {
                                            keepData = line3;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");

                                            line6 = br.readLine();
                                            int line6Length = line6.length();
                                            if (line6Length > 0) { //ข่าว2บรรทัด
                                                if (line6.substring(0, 1).equals("6")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 1).equals("7")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("10")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("1/")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("20")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("2/")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("55")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("56")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("57")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("58")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 2).equals("59")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 3).equals("333")) {
                                                    keepData = line5 + " " + line6;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 4).equals(" 333")) {
                                                    keepData = line5 + " " + line6.substring(1);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 5).equals("  333")) {
                                                    keepData = line5 + " " + line6.substring(2);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 6).equals("   333")) {
                                                    keepData = line5 + " " + line6.substring(3);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line6.substring(0, 11).equals("        333")) {
                                                    keepData = line5 + " " + line6.substring(8);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else {

                                                }
                                            } else if (line6Length == 0) {
                                                line7 = br.readLine();
                                                //System.out.println("Line7->" + line7);
                                                //System.out.println(line5);
                                                if (line7.substring(0, 1).equals("6")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 1).equals("7")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("10")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("1/")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("20")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("2/")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("55")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("56")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("57")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("58")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 2).equals("59")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 3).equals("333")) {
                                                    keepData = line5 + " " + line7;
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 4).equals(" 333")) {
                                                    keepData = line5 + " " + line7.substring(1);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 5).equals("  333")) {
                                                    keepData = line5 + " " + line7.substring(2);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 6).equals("   333")) {
                                                    keepData = line5 + " " + line7.substring(3);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else if (line7.substring(0, 11).equals("        333")) {
                                                    keepData = line5 + " " + line7.substring(8);
                                                    fw.write(day + hour + "00 " + keepData);
                                                    fw.write("\n");
                                                } else {

                                                }
                                            } else {

                                            }
                                        } else {

                                        }
                                    } else {

                                    }
                                } else {
                                    line4 = br.readLine();
                                    int line4Length = line4.length();
                                    if (line4Length > 0) { //ข่าว2บรรทัด
                                        if (line4.substring(0, 1).equals("6")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 1).equals("7")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("10")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("1/")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("20")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("2/")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("55")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("56")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("57")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("58")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 2).equals("59")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 3).equals("333")) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 4).equals(" 333")) {
                                            keepData = line3 + " " + line4.substring(1);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 5).equals("  333")) {
                                            keepData = line3 + " " + line4.substring(2);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 6).equals("   333")) {
                                            keepData = line3 + " " + line4.substring(3);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line4.substring(0, 11).equals("        333")) {
                                            keepData = line3 + " " + line4.substring(8);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else {
                                            /*
                                             else if (!line4.substring(0, 2).equals(country_code)) {
                                            keepData = line3 + " " + line4;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                            } 
                                             */
                                        }
                                    } else if (line4Length == 0) {
                                        line5 = br.readLine();
                                        //System.out.println(line5);
                                        if (line5.substring(0, 1).equals("6")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 1).equals("7")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("10")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("1/")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("20")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("2/")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("55")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("56")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("57")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("58")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 2).equals("59")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 3).equals("333")) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 4).equals(" 333")) {
                                            keepData = line3 + " " + line5.substring(1);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 5).equals("  333")) {
                                            keepData = line3 + " " + line5.substring(2);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 6).equals("   333")) {
                                            keepData = line3 + " " + line5.substring(3);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else if (line5.substring(0, 11).equals("        333")) {
                                            keepData = line3 + " " + line5.substring(8);
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                        } else {
                                            /* 
                                            if (!line5.substring(0, 2).equals(country_code)) {
                                            keepData = line3 + " " + line5;
                                            fw.write(day + hour + "00 " + keepData);
                                            fw.write("\n");
                                            } 
                                             */
                                        }
                                    } else {

                                    }
                                }
                            } else if (line3.substring(0, 2).equals("10")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("1/")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("20")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("2/")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("55")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("56")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("57")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("58")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 2).equals("59")) {
                                keepData = line + " " + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 3).equals("333")) {
                                keepData = line + line3;
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 4).equals(" 333")) {
                                keepData = line + " " + line3.substring(1);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 5).equals("  333")) {
                                keepData = line + " " + line3.substring(2);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 6).equals("   333")) {
                                keepData = line + " " + line3.substring(3);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else if (line3.substring(0, 11).equals("        333")) {
                                keepData = line + " " + line3.substring(8);
                                fw.write(day + hour + "00 " + keepData);
                                fw.write("\n");
                            } else {
                                System.out.println("Error");
                            }
                        } else {
                            keepData = line + "Error line3 ";
                            fw.write(day + hour + "00 " + keepData);
                            fw.write("\n");
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
            }
            countData++;
            System.out.println(keepData);
        }

        fr.close();
        br.close();
        fw.close();

        //checkDuplicate(day, monthText, year, hour, codeCountryName);
        //check_Group(countData, day, monthText, year, hour, codeCountryName);
    }

    private static void checkDuplicate(String day, String monthText, String year, String hour, String codeCountryName) throws FileNotFoundException, IOException {
        FileReader Read00 = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_select.txt");
        FileWriter Write00 = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_Check_Dup.txt");
        BufferedReader br = new BufferedReader(Read00);

        String line;
        String[] data = new String[1000];
        int countRow = 0;
        while ((line = br.readLine()) != null) {
            data[countRow] = line;
            ////System.out.println(data[countRow]);
            countRow++;
        }
        br.close();
        Read00.close();

        //เรียงข้อมูลในArray
        String[] tempStation = new String[1];
        boolean flag = true;
        while (flag == true) {
            flag = false;

            for (int j = 0; j < countRow; j++) {

                if (j < countRow - 1) {
                    String station1 = data[j].substring(7, 12);
                    String station2 = data[j + 1].substring(7, 12);
                    int intStation1 = Integer.parseInt(station1);
                    int intStation2 = Integer.parseInt(station2);

                    if (intStation1 > intStation2) {
                        tempStation[0] = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = tempStation[0];
                        flag = true;
                    } else {
                    }
                } else {
                    data[j] = data[j];
                }
            }
            //System.out.println(line);
        }

        //ตรวจสอบข่าวซ้ำ
        String currentData;
        String nextData;
        for (int k = 0; k < countRow; k++) {
            if (k < countRow - 1) {
                currentData = data[k].substring(7, 12);
                nextData = data[k + 1].substring(7, 12);

                if (!currentData.equals(nextData)) {
                    Write00.write(data[k] + "\n");
                } else {
                }
            } else {
                Write00.write(data[k] + "\n");
            }
        }
        Write00.close();
    }

    private static void check_Group(int countData, String day, String monthText, String year, String hour, String codeCountryName) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        FileReader Read00 = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_Check_Dup.txt");
        FileWriter Write00 = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_Check_Group.txt");
        BufferedReader Buff00 = new BufferedReader(Read00);
        String dataString00[][] = new String[countData][41];

        String fileData, section, station, ir, ix, bc, vis, cc, wd, ws, tg, at, dg, dp, spg, sp, slpg, slp, ppg, pp, pg1, ta1, lt1,
                pap, ww, w1, w2, cg, alc, cl, cm, ch, sec3, mmtg, mmt, pcg, pc, pg2, ta2, lt2, p24g, p24,
                chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9;
        int i = -1;
        boolean eof = false;

        while (!eof) {
            fileData = Buff00.readLine();
            //System.out.println(fileData);
            if (fileData == null) {
                eof = true;
            } else {
                i = i + 1;

                // check temp
                chk1 = fileData.substring(25, 27);
                if (chk1.equals("10") || chk1.equals("11") || chk1.equals("1/")) {

                } else {
                    fileData = fileData.substring(0, 25) + "10000 " + fileData.substring(25);

                }

                // check dewpoint
                chk2 = fileData.substring(31, 33);
                if (chk2.equals("20") || chk2.equals("21") || chk2.equals("2/")) {

                } else {
                    fileData = fileData.substring(0, 31) + "20000 " + fileData.substring(31);

                }

                // check station pressure
                chk3 = fileData.substring(37, 38);
                if (chk3.equals("3")) {

                } else {
                    fileData = fileData.substring(0, 37) + "30000 " + fileData.substring(37);

                }

                // check slp
                chk4 = fileData.substring(43, 44);
                if (chk4.equals("4")) {

                } else {
                    fileData = fileData.substring(0, 43) + "40000 " + fileData.substring(43);

                }

                chk5 = fileData.substring(49, 50);
                if (chk5.equals("5")) {

                } else {
                    fileData = fileData.substring(0, 49) + "50000 " + fileData.substring(49);

                }

                chk6 = fileData.substring(55, 56);
                if (chk6.equals("6")) {

                } else {
                    fileData = fileData.substring(0, 55) + "60000 " + fileData.substring(55);

                }

                chk7 = fileData.substring(61, 62);
                if (chk7.equals("7")) {

                } else {
                    fileData = fileData.substring(0, 61) + "70000 " + fileData.substring(61);

                }

                chk8 = fileData.substring(67, 68);
                if (chk8.equals("8")) {

                } else {
                    fileData = fileData.substring(0, 67) + "80000 " + fileData.substring(67);

                }

                chk9 = fileData.substring(73, 76);
                if (chk9.equals("222")) {
                    fileData = fileData.substring(0, 72) + " " + fileData.substring(91);
                }

                String chk10 = fileData.substring(73, 76);
                if (chk10.equals("333")) {

                } else {
                    fileData = fileData.substring(0, 73) + "333 " + fileData.substring(73);

                }

                String chk11 = fileData.substring(77, 78);
                if (chk11.equals("0")) {
                    fileData = fileData.substring(0, 76) + " " + fileData.substring(83);

                } else {

                }

                String chk12 = fileData.substring(77, 79);
                if (chk12.equals("10") || (chk12.equals("20"))) {

                } else if (chk12.equals("2/")) {

                } else if (chk12.equals("1/")) {

                } else {
                    fileData = fileData.substring(0, 77) + "10/// " + fileData.substring(77);

                }

                String chk13 = fileData.substring(83, 84);
                if (chk13.equals("3")) {
                    fileData = fileData.substring(0, 82) + fileData.substring(88);

                }

                //System.out.println("Data ---> " + fileData);
                //System.out.println("Check 50 ---> " + fileData.substring(83, 85));
                //System.out.println("Check End Line ---> " + fileData.substring(88, 89));
                ////System.out.println("<---> ");
                String chk14 = fileData.substring(83, 85);
                if (chk14.equals("50") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("50")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("51") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("51")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("52") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("52")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("53") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("53")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("54") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("54")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("55") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("55")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("56") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("56")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                chk14 = fileData.substring(83, 85);
                if (chk14.equals("57") && (fileData.substring(88, 89).equals("="))) {
                    fileData = fileData.substring(0, 83) + "58000 60000 70000";

                } else if (chk14.equals("57")) {
                    fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                } else {

                }

                //System.out.println("Data ---> " + fileData);
                //System.out.println("Check 58 ---> " + fileData.substring(83, 85));
                //System.out.println("Check 58 ---> " + fileData.substring(88, 89));
                chk14 = fileData.substring(83, 85);
                if (chk14.equals("58") || chk14.equals("59") || chk14.equals("5/")) {

                } else {
                    fileData = fileData.substring(0, 83) + "58000 " + fileData.substring(89);

                }

                if (fileData.substring(88, 89).equals("=")) {
                    fileData = fileData.substring(0, 88) + " 60000 70000";

                } else if (fileData.substring(89, 90).equals("6")) {
                    String chk17 = fileData.substring(94, 95);
                    if (chk17.equals("=")) {
                        fileData = fileData.substring(0, 94) + " 70000";

                    } else if (chk17.equals("7")) {
                        fileData = fileData.substring(0, 100);

                    } else {
                        fileData = fileData.substring(0, 94) + " 70000";
                    }
                } else if (fileData.substring(89, 90).equals("7")) {
                    fileData = fileData.substring(0, 89) + "60000 " + fileData.substring(94);

                } else if (fileData.substring(89, 90).equals("8")) {
                    fileData = fileData.substring(0, 88) + " 60000 70000";

                } else {
                    fileData = fileData.substring(0, 88) + " 60000 70000";

                }
                System.out.println(fileData);

                //section
                section = fileData.substring(2, 4);
                dataString00[i][0] = section;

                //station
                station = fileData.substring(7, 12);
                dataString00[i][1] = station;

                //ir
                ir = fileData.substring(13, 14);
                dataString00[i][2] = ir;

                //ix
                ix = fileData.substring(14, 15);
                dataString00[i][3] = ix;

                //base cloud
                bc = fileData.substring(15, 16);
                dataString00[i][4] = bc;

                //vis
                vis = fileData.substring(16, 18);
                dataString00[i][5] = vis;

                //cc
                cc = fileData.substring(19, 20);
                dataString00[i][6] = cc;

                //wind dircetion
                wd = fileData.substring(20, 22);
                dataString00[i][7] = wd;

                //wind speed
                ws = fileData.substring(22, 24);
                dataString00[i][8] = ws;

                // check temp
                tg = fileData.substring(25, 27);
                dataString00[i][9] = tg;
                at = fileData.substring(27, 30);
                dataString00[i][10] = at;

                // check dewpoint
                dg = fileData.substring(31, 33);
                dataString00[i][11] = dg;
                dp = fileData.substring(33, 36);
                dataString00[i][12] = dp;

                // check station pressure
                spg = fileData.substring(37, 38);
                dataString00[i][13] = spg;
                sp = fileData.substring(38, 42);
                dataString00[i][14] = sp;

                // check slp
                slpg = fileData.substring(43, 44);
                dataString00[i][15] = slpg;
                slp = fileData.substring(44, 48);
                dataString00[i][16] = slp;

                ppg = fileData.substring(49, 50);
                dataString00[i][17] = ppg;
                pp = fileData.substring(50, 54);
                dataString00[i][18] = pp;

                pg1 = fileData.substring(55, 56);
                dataString00[i][19] = pg1;
                ta1 = fileData.substring(56, 59);
                dataString00[i][20] = ta1;
                lt1 = fileData.substring(59, 60);
                dataString00[i][21] = lt1;

                pap = fileData.substring(61, 62);
                dataString00[i][22] = pap;
                ww = fileData.substring(62, 64);
                dataString00[i][23] = ww;
                w1 = fileData.substring(64, 65);
                dataString00[i][24] = w1;
                w2 = fileData.substring(65, 66);
                dataString00[i][25] = w2;

                cg = fileData.substring(67, 68);
                dataString00[i][26] = cg;
                alc = fileData.substring(68, 69);
                dataString00[i][27] = alc;
                cl = fileData.substring(69, 70);
                dataString00[i][28] = cl;
                cm = fileData.substring(70, 71);
                dataString00[i][29] = cm;
                ch = fileData.substring(71, 72);
                dataString00[i][30] = ch;

                sec3 = fileData.substring(73, 76);
                dataString00[i][31] = sec3;

                mmtg = fileData.substring(77, 79);
                dataString00[i][32] = mmtg;
                mmt = fileData.substring(79, 82);
                dataString00[i][33] = mmt;

                pcg = fileData.substring(83, 85);
                dataString00[i][34] = pcg;
                pc = fileData.substring(85, 88);
                dataString00[i][35] = pc;

                pg2 = fileData.substring(89, 90);
                dataString00[i][36] = pg2;
                ta2 = fileData.substring(90, 93);
                dataString00[i][37] = ta2;
                lt2 = fileData.substring(93, 94);
                dataString00[i][38] = lt2;
                p24g = fileData.substring(95, 96);
                dataString00[i][39] = p24g;
                p24 = fileData.substring(96, 100);
                dataString00[i][40] = p24;

                //write data
                for (int j = 0; j < 41; j++) {
                    if (j == 0) {
                        Write00.write(day + "-" + monthText + "-" + year + " " + dataString00[i][j] + " ");
                    } else if (j > 0) {
                        Write00.write(dataString00[i][j] + " ");
                    }
                }
                Write00.write("\n");
            }
        }
        ////System.out.println("Check Group Complete");
        Read00.close();
        Write00.close();
        Buff00.close();

        //Read Count Row for importToDatabase
        String path = "D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_Check_Group.txt";
        int countRecord = 0;
        FileReader read = new FileReader(path);
        BufferedReader buffer = new BufferedReader(read);
        while (buffer.readLine() != null) {
            countRecord++;
        }
        buffer.close();
        read.close();
        //System.out.println(countRecord);
        //importData importDB = new importData();
        //importDB.addToArray(path, countRecord);
    }
}
