package com.example.new_rss.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPDataHandler {

    static String stream = null;

    public HTTPDataHandler() {
    }

    public String GetHTTPData (String urlString) {

        try {
            URL url = new URL(urlString);

            //Opening the url connection
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            //Checking that the response is ok = 200
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                //Getting the data stream
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                //Read the data from the input stream
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = br.readLine()) != null)
                    stringBuilder.append(line);

                //converting the result to string
                stream = stringBuilder.toString();

                //disconnecting the url connection
                urlConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        // returning the results
        } return  stream;
    }
}
