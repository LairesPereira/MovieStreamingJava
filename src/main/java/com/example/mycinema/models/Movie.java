package com.example.mycinema.models;

import com.example.APIs.OMDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

public class Movie extends PlayableFile  {
    public Map<String, Object> about;
    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";
    public String gender;
    public String description;
    public String posterSource;
    public String posterSourceURL;

    public Movie(String folderPath) {
        super(folderPath);
        setAbout();
        createInfoTxt();
        setMovieGender();
        setMoviePoster();
        setMovieDescription();
        setFileName();
        setAgeGroup();
    }

    public void setAbout() {
        OMDB omdbClient = new OMDB();
        Map<String, Object> responseMap = omdbClient.searchTitle(super.fileName);
        this.about = responseMap;
        System.out.println(this.about);
    }

    private void createInfoTxt() {
        try {
            File info = new File(super.folderPath + "/info.txt");
            info.createNewFile();
            FileWriter writer = new FileWriter(info.getPath());
            writer.write(this.about.toString());
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
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
