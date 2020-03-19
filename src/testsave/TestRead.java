package testsave;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

public class TestRead {

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

                        String country_code = "48";
                        String codeCountryName = "MM";
                        String strURL = ("http://www.gts.tmd.go.th/examtest/all.php?ld=VBRR&pt=" + yThai + "/Synoptic/" + Day + "-" + Month + Year + ".T" + time[h] + "&sig=VBRR");

                        URL synop = new URL(strURL);
                        testCheck checkStatus = new testCheck();
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
        String line, line2, line3, line4, line5, line6, line7, keepData = null, Check = "=", words[] = null;;
        FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_select.txt");
        int countData = 0;

        //System.out.println("---------------------------------------------keepData---------------------------------------");
        while ((line = br.readLine()) != null) {
            try {
                int line1Length = line.length();
                int Beforeline1Length = line1Length - 1;
                if (line.substring(Beforeline1Length, line1Length).equals(" ")) {
                    line = line.substring(0, Beforeline1Length);
                } else {

                }

                if (line.substring(0, 1).equals("0") || line.substring(0, 1).equals("1") || line.substring(0, 1).equals("2") || line.substring(0, 1).equals("3") || line.substring(0, 1).equals("4") || line.substring(0, 1).equals("5") || line.substring(0, 1).equals("6") || line.substring(0, 1).equals("7") || line.substring(0, 1).equals("8") || line.substring(0, 1).equals("9")) {
                    //System.out.println("Line->" + line);
                    keepData = line + "\n";
                    //String nohtml = keepData.toString().replaceAll("=", "\\= ");
                    fw.write(keepData);
                    System.out.println(keepData);
                }
            } catch (StringIndexOutOfBoundsException e) {
            }

        }

        fr.close();
        br.close();
        fw.close();

        /*FileReader fr2 = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_select.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        FileWriter fw2 = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_" + codeCountryName + "_select_new.txt");
        String s;
        while ((s = br2.readLine()) != null) //Reading Content from the file
        {
            words = s.split(" ");  //Split the word using space
            for (String word : words) {
                if (word.equals("=")) {
                    keepData = s + "\n";
                    fw.write(keepData);
                    System.out.println(keepData);
                }
            }
        }
        fr2.close();
        br2.close();
        fw2.close();*/
        //checkDuplicate(day, monthText, year, hour, codeCountryName);
        //check_Group(countData, day, monthText, year, hour, codeCountryName);
    }

}
