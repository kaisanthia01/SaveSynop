package Synoptic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckConnectURL {

    public boolean check(String strURL) throws IOException {
        boolean check = false;
        URL synop = new URL(strURL);
        HttpURLConnection urlConn = (HttpURLConnection) synop.openConnection();
        if ((urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) == true) {
            check = true;
            
        } else {
            check = false;
            
        }
        return check;
    }
}
