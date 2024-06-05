package com.example.APIs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class OMDB {
    private static final String API_URL = "http://www.omdbapi.com/?apikey=1508ce91&";
    private final String API_SECRET_KEY = "1508ce91";

    ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> searchTitle(String movieTitle) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "t=" + movieTitle)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> responseMap = gson.fromJson(jsonResponse, type);

            return responseMap;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public ResponseBody searchTitleJson(String movieTitle) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "t=" + movieTitle)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
           return response.body();

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
