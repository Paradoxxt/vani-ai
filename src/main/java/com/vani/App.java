package com.vani;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App{

    // Sarvam APIU Key
    static String API_KEY = System.getenv("SARVAM_API_KEY");

    public static void main(String[] args) throws Exception {

        // Force terminal to display Unicode (Hindi script)
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));

        // Step 1:Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Step 2: Prepare json body for the request
        String json = """
                            {                     
                               "input": "Hello, My name is Vani!",
                               "source_language_code": "en-IN",
                               "target_language_code": "hi-IN"
                            }
                            """;

        // Step 3: Create an HTTP request                    
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.sarvam.ai/translate"))
                .header("Content-Type", "application/json")
                .header("api-subscription-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Step 4: Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Step 5: Print the response body
        System.out.println(response.body());
        
        // step 6: Parse the JSON response 
        org.json.JSONObject jsonResponse = new org.json.JSONObject(response.body());
        String translatedText = jsonResponse.getString("translated_text");

        // step 7: Print and Save the translated text   
        System.out.println("Translation Received!");
        System.out.println("Raw: " + translatedText);
        
        // Save to file so we can read Hindi properly
        java.nio.file.Files.writeString(java.nio.file.Path.of("translation_output.txt"), 
        "Original: Hello, My name is Vani!\n" + 
        "Translated: " + translatedText,
         java.nio.charset.StandardCharsets.UTF_8
        );
        System.out.println("Saved to translation_output.txt - open it to see Hindi!");
    }
}