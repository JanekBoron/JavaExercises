import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Boroń Jan S23370
 */

//package zad1;


public class Service {


    String getWeather(String citie, String country) throws IOException {
        String apiKey = "4fc9f5e3fc3e04f858ccded987efa048";

        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=+" + citie + "," + country + "&appid=" + apiKey;
        String weather = null;
        try {
            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader(conn.getInputStream()));
            weather = jsonElement.toString();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("httpResponseCode: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(weather);
           return weather;

    }

    ;

    String getRateFor(String currency_code) throws IOException {
        String url_str = "https://api.exchangerate.host/convert?from=" + currency_code + "&to=EUR";
        String currency = null;

        try {

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

            currency = root.toString();

            System.out.println(currency);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return currency;
    }


    Double getNBPRate() {
        double d = 2;
        return d;
    }

    ;


}
