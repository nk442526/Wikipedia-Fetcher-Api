//ye page ko copy kiye hi sir ke pastebin ke diye gye link se

package utilitydy_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

    private final static String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public static String sendGet(String urlStr) throws Exception {

        StringBuilder result = new StringBuilder();
        /*why we r using stringbuildr--if we use the string and keep appending the output to tht string so new string will get created.like "abc"
        is string and appending "c" to it then apending 'd' to it...so as many as character we r adding that many differnt string is
         created ,so why we use stringbuilder so that appending got easy */

        URL url = new URL(urlStr);
        //this URL class is under java.net  by importing java.net . And under this URL we r passing 'urlstr'

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //here casting is done.------casting means converting one datatype to another datatyp.

        conn.setRequestMethod("GET"); //here we are defining that this connection is of get method
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        //bufferreader is somthing which we can say a bucket
        //and here this bfferreader it will gone connect of conn.getInput . like previouly we are using system.in to get input ,similarly buffereader uses conn.inputstrm

        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);//we r appenging result in stringbuildr
        }
        rd.close();//we r closing buffrreader
        return result.toString();
    }


    public static void main(String[] args) {
        try {
            System.out.println(sendGet("https://codingclub.tech/test-get-request?name=Neha"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
