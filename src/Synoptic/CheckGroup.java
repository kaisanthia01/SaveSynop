package Synoptic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CheckGroup {

    public static void checkGroup(String day, String monthText, String year, String hour, String codeCountryName,
            int countData) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        FileReader Read00 = new FileReader("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour
                + "_" + codeCountryName + "_Check_Dup.txt");
        FileWriter Write00 = new FileWriter("D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year
                + hour + "_" + codeCountryName + "_Check_Group.txt");
        BufferedReader Buff00 = new BufferedReader(Read00);
        String dataString00[][] = new String[countData][41];

        String fileData, section, station, ir, ix, bc, vis, cc, wd, ws, tg, at, dg, dp, spg, sp, slpg, slp, ppg, pp,
                pg1, ta1, lt1, pap, ww, w1, w2, cg, alc, cl, cm, ch, sec3, mmtg, mmt, pcg, pc, pg2, ta2, lt2, p24g, p24,
                chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9;
        int i = -1;
        boolean eof = false;

        while (!eof) {
            fileData = Buff00.readLine();
            // System.out.println(fileData);
            if (fileData == null) {
                eof = true;
            } else {
                i = i + 1;

                try {
                    // check temp
                    chk1 = fileData.substring(25, 27);
                    if (chk1.equals("10") && (fileData.substring(30, 31).equals("="))) {
                        //fileData = fileData.substring(0, 30)
                        //        + " 20000 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk1.equals("11") && (fileData.substring(30, 31).equals("="))) {
                        //fileData = fileData.substring(0, 30)
                        //        + " 20000 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk1.equals("1/") && (fileData.substring(30, 31).equals("="))) {
                        //fileData = fileData.substring(0, 30)
                        //        + " 20000 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk1.equals("10")) {

                    } else if (chk1.equals("11")) {

                    } else if (chk1.equals("1/")) {

                    } else {
                        //fileData = fileData.substring(0, 25) + "10000 " + fileData.substring(25);
                        continue;
                    }

                    // check dewpoint
                    chk2 = fileData.substring(31, 33);
                    if (chk2.equals("20") && (fileData.substring(36, 37).equals("="))) {
                        //fileData = fileData.substring(0, 36)
                        //        + " 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk2.equals("21") && (fileData.substring(36, 37).equals("="))) {
                        //fileData = fileData.substring(0, 36)
                        //        + " 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk2.equals("2/") && (fileData.substring(36, 37).equals("="))) {
                        //fileData = fileData.substring(0, 36)
                        //        + " 30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (chk2.equals("20")) {

                    } else if (chk2.equals("21")) {

                    } else if (chk2.equals("2/")) {

                    } else {
                        //fileData = fileData.substring(0, 31) + "20000 " + fileData.substring(31);
                        continue;

                    }

                    // check station pressure
                    if (fileData.substring(37, 41).equals("333=")) {
                        //fileData = fileData.substring(0, 37)
                        //        + "30000 40000 50000 60000 70000 80000 333 10/// 58000 60000 70000";
                        continue;

                    } else if (fileData.substring(37, 41).equals("333 ")) {
                        //fileData = fileData.substring(0, 37) + "30000 " + fileData.substring(37);
                        continue;

                    } else {
                        chk3 = fileData.substring(37, 38);
                        if (chk3.equals("3")) {

                        } else {
                            fileData = fileData.substring(0, 37) + "3//// " + fileData.substring(37);

                        }
                    }

                    // check slp
                    chk4 = fileData.substring(43, 44);
                    if (chk4.equals("4")) {

                    } else {
                        //fileData = fileData.substring(0, 43) + "4//// " + fileData.substring(43);
                        continue;
                    }

                    chk5 = fileData.substring(49, 50);
                    if (chk5.equals("5")) {

                    } else {
                        fileData = fileData.substring(0, 49) + "5//// " + fileData.substring(49);

                    }

                    chk6 = fileData.substring(55, 56);
                    if (chk6.equals("6")) {

                    } else {
                        fileData = fileData.substring(0, 55) + "6//// " + fileData.substring(55);

                    }

                    chk7 = fileData.substring(61, 62);
                    if (chk7.equals("7")) {

                    } else {
                        fileData = fileData.substring(0, 61) + "7//// " + fileData.substring(61);

                    }

                    chk8 = fileData.substring(67, 68);
                    if (chk8.equals("8")) {
                        if (fileData.substring(68, 69).equals(" ")) {
                            fileData = fileData.substring(0, 67) + "8//// " + fileData.substring(67);
                            
                        } else if (fileData.substring(69, 70).equals(" ")) {
                            fileData = fileData.substring(0, 69) + "/// " + fileData.substring(70);
                            
                        } else if (fileData.substring(70, 71).equals(" ")) {
                            fileData = fileData.substring(0, 70) + "// " + fileData.substring(71);
                            
                        } else if (fileData.substring(71, 72).equals(" ")) {
                            fileData = fileData.substring(0, 71) + "/ " + fileData.substring(72);
                            
                        } else {

                        }
                    } else {
                        fileData = fileData.substring(0, 67) + "8//// " + fileData.substring(67);

                    }
                    
                    chk9 = fileData.substring(73, 76);
                    if (chk9.equals("222") && (fileData.substring(76, 77).equals("="))) {
                        fileData = fileData.substring(0, 72) + " 333 10/// 58/// 6//// 7////";

                    } else if (chk9.equals("222") && (fileData.substring(76, 78).equals(" ="))) {
                        fileData = fileData.substring(0, 72) + " 333 10/// 58/// 6//// 7////";

                    } else if (chk9.equals("222")) {
                        fileData = fileData.substring(0, 72) + " 333 10/// 58/// 6//// 7////";

                    }

                    String chk10 = fileData.substring(73, 76);
                    if (chk10.equals("333") && (fileData.substring(76, 77).equals("="))) {
                        fileData = fileData.substring(0, 72) + " 333 10/// 58/// 6//// 7////";

                    } else if (chk10.equals("333") && (fileData.substring(76, 78).equals(" ="))) {
                        fileData = fileData.substring(0, 72) + " 333 10/// 58/// 6//// 7////";

                    } else if (chk10.equals("333")) {

                    } else if (chk10.equals(" 33")) {
                        fileData = fileData.substring(0, 72) + "333" + fileData.substring(76);

                    } else {
                        fileData = fileData.substring(0, 72) + " 333 " + fileData.substring(73);

                    }

                    /*String chk11 = fileData.substring(77, 78);
                    if (chk11.equals("0")) {
                        fileData = fileData.substring(0, 76) + " " + fileData.substring(83);

                    } else {

                    }*/
                    String chk12 = fileData.substring(77, 79);
                    if ((chk12.equals("10") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if ((chk12.equals("11") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if ((chk12.equals("20") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if ((chk12.equals("21") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if ((chk12.equals("2/") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if ((chk12.equals("1/") && (fileData.substring(82, 83).equals("=")))) {
                        fileData = fileData.substring(0, 82) + " 58/// 6//// 7////";

                    } else if (chk12.equals("10")) {

                    } else if (chk12.equals("11")) {

                    } else if (chk12.equals("20")) {

                    } else if (chk12.equals("21")) {

                    } else if (chk12.equals("2/")) {

                    } else if (chk12.equals("1/")) {

                    } else {
                        fileData = fileData.substring(0, 77) + "10/// " + fileData.substring(77);

                    }

                    String chk13 = fileData.substring(83, 84);
                    if (chk13.equals("3")) {
                        fileData = fileData.substring(0, 82) + fileData.substring(88);

                    }

                    // System.out.println("Data ---> " + fileData);
                    // System.out.println("Check 50 ---> " + fileData.substring(83, 85));
                    // System.out.println("Check End Line ---> " + fileData.substring(88, 89));
                    //// System.out.println("<---> ");
                    String chk14 = fileData.substring(83, 85);
                    if (chk14.equals("50") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("50") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("50")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("51") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("51") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("51")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("52") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("52") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("52")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("53") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("53") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("53")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("54") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("54") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("54")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("55") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("55") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("55")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("56") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("56") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("56")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("57") && (fileData.substring(88, 89).equals("="))
                            || chk14.equals("57") && (fileData.substring(89, 90).equals("="))) {
                        fileData = fileData.substring(0, 83) + "58/// 6//// 7////";

                    } else if (chk14.equals("57")) {
                        fileData = fileData.substring(0, 83) + "" + fileData.substring(89);
                    } else {

                    }

                    // System.out.println("Data ---> " + fileData);
                    // System.out.println("Check 58 ---> " + fileData.substring(83, 85));
                    // System.out.println("Check 58 ---> " + fileData.substring(88, 89));
                    chk14 = fileData.substring(83, 85);
                    if (chk14.equals("58") || chk14.equals("59") || chk14.equals("5/")) {

                    } else {
                        fileData = fileData.substring(0, 83) + "58/// " + fileData.substring(89);

                    }

                    if (fileData.substring(88, 89).equals("=")) {
                        fileData = fileData.substring(0, 88) + " 6//// 7////";

                    } else if (fileData.substring(89, 90).equals("6")) {
                        String chk17 = fileData.substring(94, 95);
                        if (chk17.equals("=")) {
                            fileData = fileData.substring(0, 94) + " 7////";

                        } else if (chk17.equals("7")) {
                            fileData = fileData.substring(0, 100);

                        } else {
                            fileData = fileData.substring(0, 94) + " 7////";
                        }
                    } else if (fileData.substring(89, 90).equals("7")) {
                        fileData = fileData.substring(0, 89) + "6//// " + fileData.substring(94);

                    } else if (fileData.substring(89, 90).equals("8")) {
                        fileData = fileData.substring(0, 88) + " 6//// 7////";

                    } else {
                        fileData = fileData.substring(0, 88) + " 6//// 7////";

                    }
                    //System.out.println(fileData);

                    // section
                    section = fileData.substring(2, 4);
                    dataString00[i][0] = section;

                    // station
                    station = fileData.substring(7, 12);
                    dataString00[i][1] = station;

                    // ir
                    ir = fileData.substring(13, 14);
                    dataString00[i][2] = ir;

                    // ix
                    ix = fileData.substring(14, 15);
                    dataString00[i][3] = ix;

                    // base cloud
                    bc = fileData.substring(15, 16);
                    dataString00[i][4] = bc;

                    // vis
                    vis = fileData.substring(16, 18);
                    dataString00[i][5] = vis;

                    // cc
                    cc = fileData.substring(19, 20);
                    dataString00[i][6] = cc;

                    // wind dircetion
                    wd = fileData.substring(20, 22);
                    dataString00[i][7] = wd;

                    // wind speed
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

                    // write data
                    for (int j = 0; j < 41; j++) {
                        if (j == 0) {
                            Write00.write(day + "-" + monthText + "-" + year + " " + dataString00[i][j] + " ");
                        } else if (j > 0) {
                            Write00.write(dataString00[i][j] + " ");
                        }
                    }
                    Write00.write("\n");
                } catch (StringIndexOutOfBoundsException e) {
                }
            }
        }
        //// System.out.println("Check Group Complete");
        Read00.close();
        Write00.close();
        Buff00.close();

        // Read Count Row for importToDatabase
        String path = "D:\\DataSynoptic\\" + codeCountryName + "\\" + day + monthText + year + hour + "_"
                + codeCountryName + "_Check_Group.txt";
        int countRecord = 0;
        FileReader read = new FileReader(path);
        BufferedReader buffer = new BufferedReader(read);
        while (buffer.readLine() != null) {
            countRecord++;
        }
        buffer.close();
        read.close();
        // System.out.println(countRecord);
        CheckImportData checkImportData = new CheckImportData();
        checkImportData.addToArray(path, countRecord);
    }
}
