/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

/**
 *
 * @author Giacomo
 */
public class TelegramBotManager {

    String token;
    long offset;

    public TelegramBotManager(String token) {
        this.token = token;
        offset = 0;
    }

    public String getUrlWithToken() {
        return "https://api.telegram.org/bot" + token + "/";
    }

    /**
     * PARAMETERS: method_name: name of the request (ex. "getUpdates") RETURN:
     * jsonString: string containing response json, null: exeption
     *
     * used for gets (getUpdates, getMe, ...)
     */
    public String request(String method_name) {
        try {
            //get url with token, add request name 
            URL url = new URL(getUrlWithToken() + method_name);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            //read response
            String jsonString = br.lines().collect(Collectors.joining());

            //close buffered reader, return json
            br.close();
            return jsonString;
        } catch (IOException ex) {
            return null;
        }
    }

    public String getUpdates() throws IOException {
        //build url
        URL url = new URL(getUrlWithToken() + "getUpdates");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        //read response
        String jsonString = br.lines().collect(Collectors.joining());

        //close buffered reader, return json
        br.close();
        return jsonString;
    }

    public long getOffset() {
        return offset;
    }

    public String getUpdatesWithOffset() throws IOException {
        //build url
        URL url = new URL(getUrlWithToken() + "getUpdates?offset=" + offset);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        //read response
        String jsonString = br.lines().collect(Collectors.joining());

        //close buffered reader, return json
        br.close();
        return jsonString;
    }

    public String getMe() throws IOException {
        URL url = new URL(getUrlWithToken() + "getMe");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        //read response
        String jsonString = br.lines().collect(Collectors.joining());

        //close buffered reader, return json
        br.close();
        return jsonString;
    }

    public void sendMessage(long chat_id, String message) throws InterruptedException {
        try {
            String urlString = getUrlWithToken() + "sendMessage?chat_id=" + chat_id + "&text=" + getEncodedString(message);
            //System.out.println(urlString);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (MalformedURLException ex) {
            Logger.getLogger(TelegramBotManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelegramBotManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getEncodedString(String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, StandardCharsets.UTF_8.toString());
    }
}
