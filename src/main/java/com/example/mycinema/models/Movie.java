package com.example.mycinema.models;

import com.example.APIs.OMDB;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.ResponseBody;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

public class Movie extends PlayableFile  {
    private OMDB omdbClient = new OMDB();
    public Map<String, Object> about;
    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";
    public String gender;
    public String description;
    public String posterSource;
    public String posterSourceURL;
    private ResponseBody aboutResponseJson;

    public Movie(String folderPath) {
        super(folderPath);
        setAbout();
        setMovieGender();
        setMoviePoster();
        setMovieDescription();
        setFileName();
        setAgeGroup();
    }

    public void setAbout() {
        if(!searchAboutJsonFile()) {
            Map<String, Object> responseMap = omdbClient.searchTitle(super.fileName);
            this.about = responseMap;
            createAboutTxt();
        } else {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Type type = new TypeToken<Map<String, Object>>() {}.getType();
                FileReader reader = new FileReader(super.folderPath + "/about.json");
                Map<String, Object> responseMap = gson.fromJson(reader, type);
                reader.close();
                if(responseMap.containsKey("Error") && responseMap.get("Error").equals("Movie not found!")) {
                    createAboutTxt();
                }
                this.about = responseMap;

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private void createAboutTxt() {
        try {
            this.aboutResponseJson = omdbClient.searchTitleJson(super.fileName);
            Writer writer = new FileWriter(super.folderPath + "/about.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = JsonParser.parseString(this.aboutResponseJson.string());
            gson.toJson(jsonElement, writer);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private boolean searchAboutJsonFile() {
        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            try {
                if (file.toString().endsWith("about.json")) {
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return false;
    }

    public void setMoviePoster() {
        if(this.about != null && this.about.get("Poster") != null) {
            this.posterSourceURL = this.about.get("Poster").toString();
        } else {
            this.posterSource = "load-default";
        }
    }

    private static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.getEncoder().encodeToString(bytes).getBytes(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }

    public void setMovieGender() {
        this.gender = searchInfo(super.folderPath, "GENDER");
    }

    public void setMovieDescription() {
        OMDB omdbClient = new OMDB();
        Map<String, Object> responseMap = omdbClient.searchTitle(super.fileName);
        if (responseMap != null && responseMap.containsKey("Plot")) {
            this.description = responseMap.get("Plot").toString();
        } else {
            this.description = "Description not found. Consider checking movies name or removing special characters.";
        }
    }

    @Override
    public void setAgeGroup() {}

    @Override
    public String searchInfo(String folderPath, String value) {
        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            try {
                if (file.toString().endsWith(".txt")) {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {
                        String read = scan.nextLine();
                        if (read.contains(value)){
                            String info = read.substring(read.lastIndexOf(":") + 1);
                            return info;
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return "";
    }
}
