package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CheckDuplicate {
    public static void checkDuplicate(String day, String monthText, String year, String hour, String codeCountryName,
            int countData) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        FileReader Read00 = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + "_select.txt");
        FileWriter Write00 = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year
                + hour + "_" + codeCountryName + "_Check_Dup.txt");
        BufferedReader br = new BufferedReader(Read00);

        String line;
        String[] data = new String[3000];
        int countRow = 0;
        while ((line = br.readLine()) != null) {
            data[countRow] = line;
            //// System.out.println(data[countRow]);
            countRow++;
        }
        br.close();
        Read00.close();

        // เรียงข้อมูลในArray
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
            // System.out.println(line);
        }

        // ตรวจสอบข่าวซ้ำ
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

        CheckGroup.checkGroup(day, monthText, year, hour, codeCountryName, countData);
    }
}
