package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CheckSynoptic {

    public static void checkSynoptic(String day, String monthText, String year, String hour, String codeCountryName,
            String country_code) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        FileReader fr = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + ".txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + "_check.txt");
        String line, keepData = null;
        while ((line = br.readLine()) != null) {
            try {
                int line1Length = line.length();
                int Beforeline1Length = line1Length - 1;
                if (line.substring(Beforeline1Length, line1Length).equals(" ")) {
                    line = line.substring(0, Beforeline1Length);
                } else if (line.substring(0, 1).equals("=")) {
                    keepData = line + "\n";
                    fw.write(keepData);
                    continue;
                } else {
                }

                if (line.substring(0, 1).equals("0") || line.substring(0, 1).equals("1")
                        || line.substring(0, 1).equals("2") || line.substring(0, 1).equals("3")
                        || line.substring(0, 1).equals("4") || line.substring(0, 1).equals("5")
                        || line.substring(0, 1).equals("6") || line.substring(0, 1).equals("7")
                        || line.substring(0, 1).equals("8") || line.substring(0, 1).equals("9")) {
                    int lineLength = line.length();
                    int BeforelineLength = lineLength - 1;
                    if (line.substring(BeforelineLength, lineLength).equals("=")) {
                        keepData = line + "\n";
                        fw.write(keepData);
                    } else {
                        keepData = line + " ";
                        fw.write(keepData);
                    }

                } else if (line.substring(0, 2).equals(" 0") || line.substring(0, 2).equals(" 1")
                        || line.substring(0, 2).equals(" 2") || line.substring(0, 2).equals(" 3")
                        || line.substring(0, 2).equals(" 4") || line.substring(0, 2).equals(" 5")
                        || line.substring(0, 2).equals(" 6") || line.substring(0, 2).equals(" 7")
                        || line.substring(0, 2).equals(" 8") || line.substring(0, 2).equals(" 9")) {
                    int lineLength = line.length();
                    int BeforelineLength = lineLength - 1;
                    if (line.substring(BeforelineLength, lineLength).equals("=")) {
                        keepData = line.substring(1) + "\n";
                        fw.write(keepData);
                    } else {
                        keepData = line.substring(1) + " ";
                        fw.write(keepData);
                    }

                } else if (line.substring(0, 3).equals("  0") || line.substring(0, 3).equals("  1")
                        || line.substring(0, 3).equals("  2") || line.substring(0, 3).equals("  3")
                        || line.substring(0, 3).equals("  4") || line.substring(0, 3).equals("  5")
                        || line.substring(0, 3).equals("  6") || line.substring(0, 3).equals("  7")
                        || line.substring(0, 3).equals("  8") || line.substring(0, 3).equals("  9")) {
                    int lineLength = line.length();
                    int BeforelineLength = lineLength - 1;
                    if (line.substring(BeforelineLength, lineLength).equals("=")) {
                        keepData = line.substring(1) + "\n";
                        fw.write(keepData);
                    } else {
                        keepData = line.substring(1) + " ";
                        fw.write(keepData);
                    }

                } else if (line.substring(0, 4).equals("   0") || line.substring(0, 4).equals("   1")
                        || line.substring(0, 4).equals("   2") || line.substring(0, 4).equals("   3")
                        || line.substring(0, 4).equals("   4") || line.substring(0, 4).equals("   5")
                        || line.substring(0, 4).equals("   6") || line.substring(0, 4).equals("   7")
                        || line.substring(0, 4).equals("   8") || line.substring(0, 4).equals("   9")) {
                    int lineLength = line.length();
                    int BeforelineLength = lineLength - 1;
                    if (line.substring(BeforelineLength, lineLength).equals("=")) {
                        keepData = line.substring(1) + "\n";
                        fw.write(keepData);
                    } else {
                        keepData = line.substring(1) + " ";
                        fw.write(keepData);
                    }

                } else if (line.substring(0, 5).equals("    0") || line.substring(0, 5).equals("    1")
                        || line.substring(0, 5).equals("    2") || line.substring(0, 5).equals("    3")
                        || line.substring(0, 5).equals("    4") || line.substring(0, 5).equals("    5")
                        || line.substring(0, 5).equals("    6") || line.substring(0, 5).equals("    7")
                        || line.substring(0, 5).equals("    8") || line.substring(0, 5).equals("    9")) {
                    int lineLength = line.length();
                    int BeforelineLength = lineLength - 1;
                    if (line.substring(BeforelineLength, lineLength).equals("=")) {
                        keepData = line.substring(1) + "\n";
                        fw.write(keepData);
                    } else {
                        keepData = line.substring(1) + " ";
                        fw.write(keepData);
                    }

                } else {
                    keepData = "\n";
                    fw.write(keepData);
                }

            } catch (StringIndexOutOfBoundsException e) {

            }
            //System.out.println(keepData);
        }

        fr.close();
        br.close();
        fw.close();

        CheckSelect.checkSelect(day, monthText, year, hour, codeCountryName, country_code);
    }
}
