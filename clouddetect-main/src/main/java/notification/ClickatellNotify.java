package notification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import media.image.CloudImageResult;
import media.image.CloudResult;

public class ClickatellNotify implements Notifier<CloudResult> {
    
    private StringBuffer data = new StringBuffer();

    public void notify(CloudResult image) {
        
//        
//        Command:
//            http://api.clickatell.com/http/sendmsg?session_id=xxxx&to=xxxx&text=xxxx
//            or
//            http://api.clickatell.com/http/sendmsg?api_id=xxxx&user=xxxx&password=xxxx&to=xxxx&text=xx
//            xx
//            Response Single Message:
//            ID: apimsgid
//            Response Multiple Messages:
//            ID: apimsgid To: xxxxxx
//            ID: apimsgid To: xxxxxx
//            Or
//            Response Single Message:
//            ERR: Error number
//            Response Multiple Messages:
//            ERR: Error number To: xxxxxx
//            ERR: Error number To: xxxxxx

        
        try {
            addKeyValue("api_id", "2786391");
            addKeyValue("user", "mrosseel");
     //       addKeyValue("password", "nGc5194");
            addKeyValue("to", "32477608813");
            addKeyValue("from", "32477608813");
            addKeyValue("text", "Het is helder! Tijd om op te staan...\nMike");

            // Send data
            URL url = new URL(" https://api.clickatell.com/http/sendmsg");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn
                    .getOutputStream());
            wr.write(data.toString());
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn
                    .getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {}

    }
    
    private void addKeyValue(String key, String value) throws UnsupportedEncodingException {
        if(this.data.length() != 0) {
            this.data.append("&");
        }
        this.data.append(URLEncoder.encode(key, "UTF-8") + "="
        + URLEncoder.encode(value, "UTF-8"));
    }

}
