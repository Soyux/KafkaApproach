package com.laguna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTHandler {

    public void connect(String apiCompletionsUrl,String openaiApiKey,String prompt) {
        
        String url = apiCompletionsUrl;//"https://api.openai.com/v1/chat/completions";
        String apiKey = openaiApiKey;

        try {
          
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);
            connection.setDoInput(false);
            String requestBody = "{\n" +
                    "  \"model\": \"gpt-3.5-turbo\",\n" +
                    "  \"messages\": [{\"role\": \"user\", \"content\": \""+prompt+"\"}],\n" +
                    "  \"temperature\": 0.7\n" +
                    "}";

            // Write the request body to the connection
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            // Get the response code from the endpoiint of ChatGPT
            int responseCode = connection.getResponseCode();

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //The outpout will the chatgpt response from what we want
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response: " + response.toString());

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of connect
}//end of class
