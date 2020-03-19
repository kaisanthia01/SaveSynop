package testsave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TestRead {

    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.gts.tmd.go.th/examtest/all.php?ld=VBRR&pt=2559/Synoptic/31-OCT16.T06&sig=VBRR");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            String nohtml = sb.toString().replaceAll("\\<.*?>", "");

            // nohtml = sb.toString().replaceAll("\\<!--.*?-->", "");
            FileWriter fw = new FileWriter("D:\\DataSynoptic\\TEST.txt");
            fw.write(nohtml);
            fw.close();
        }

        String line2;
        FileReader fr = new FileReader("D:\\DataSynoptic\\TEST.txt");
        BufferedReader brfr = new BufferedReader(fr);
        FileWriter fw2 = new FileWriter("D:\\DataSynoptic\\TEST2.txt");
        while ((line2 = brfr.readLine()) != null) {
            System.out.println(line2);
            fw2.write(line2);

        }
        fw2.close();
    }
}
