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
                if (!line.substring(6, 9).equals("NIL") && !line.substring(6, 10).equals(" NIL")
                        && !line.substring(6, 11).equals("  NIL") && !line.substring(6, 12).equals("   NIL")) {
                    keepData = day + hour + "00 " + line + "\n";
                    fw.write(keepData);
                }
            } catch (StringIndexOutOfBoundsException e) {
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
