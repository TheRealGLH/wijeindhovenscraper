/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijeindhovenscraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;

/**
 *
 * @author Martijn
 */
public class HttpURLConnectionExample {

    public static void main(String[] args) throws IOException {

        //sendGET();
        System.out.println("GET DONE");
    }

    public static void sendGET(String requestURL) throws IOException {
        URL obj = new URL(requestURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    public static String executeGet(final String https_url, final String proxyName, final int port) {
        String ret = "";

        URL url;
        try {

            HttpsURLConnection con = null;
            url = new URL(https_url);

            if (proxyName.isEmpty()) {
                con = (HttpsURLConnection) url.openConnection();
            } else {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyName, port));
//            con = (HttpsURLConnection) url.openConnection(proxy);
//            Authenticator authenticator = new Authenticator() {
//                public PasswordAuthentication getPasswordAuthentication() {
//                        return (new PasswordAuthentication(USERNAME, PASSWORD.toCharArray()));
//                    }
//                };
//            Authenticator.setDefault(authenticator);

            }

            ret = (String) con.getContent();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
