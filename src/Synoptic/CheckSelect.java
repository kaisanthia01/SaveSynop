package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CheckSelect {
    public static void checkSelect(String day, String monthText, String year, String hour, String codeCountryName,
            String country_code) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        FileReader fr = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + "_check.txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + "_select.txt");
        String line, keepData = null;
        int countData = 0;
        while ((line = br.readLine()) != null) {
            try {
                if (country_code.length() > 2) {
                    String code_1 = (country_code.substring(0, 2));
                    String code_2 = (country_code.substring(2, 4));

                    if (line.substring(0, 2).equals(code_1) || line.substring(0, 2).equals(code_2)) {
                        if (!line.substring(6, 9).equals("NIL") && !line.substring(6, 10).equals(" NIL")
                                && !line.substring(6, 11).equals("  NIL") && !line.substring(6, 12).equals("   NIL")) {
                            keepData = day + hour + "00 " + line + "\n";
                            fw.write(keepData);
                        }
                    }
                } else {
                    if (line.substring(0, 2).equals(country_code)) {
                        if (!line.substring(6, 9).equals("NIL") && !line.substring(6, 10).equals(" NIL")
                                && !line.substring(6, 11).equals("  NIL") && !line.substring(6, 12).equals("   NIL")) {
                            keepData = day + hour + "00 " + line + "\n";
                            fw.write(keepData);
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }
            countData++;
            // System.out.println(keepData);
        }

        fr.close();
        br.close();
        fw.close();

        CheckDuplicate.checkDuplicate(day, monthText, year, hour, codeCountryName, countData);
    }
}
