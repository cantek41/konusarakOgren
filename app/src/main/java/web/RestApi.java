package web;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http post yapmak için kullanılır
 * Created by Cantekin on 28.7.2016.
 */

public class RestApi<T> {
    private String TAG = "RestApi";
    private final String webApiAddress;

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public RestApi(String webApiAddress) {
        this.webApiAddress = webApiAddress;
    }

    public String GET() {
        String response = "";
        try {

            URL url = new URL(webApiAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (urlConnection.getInputStream())));

            String row;
            String output = "";
            while ((row = br.readLine()) != null) {
                output += row;
            }
            urlConnection.disconnect();
            response = output;
          //  Log.w("GET", output);
          //  Log.w("GETfff",String.valueOf(output.length()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}

