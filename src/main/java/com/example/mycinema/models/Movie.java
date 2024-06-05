package com.example.mycinema.models;

import com.example.APIs.OMDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

public class Movie extends PlayableFile  {
    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";
    public String gender;
    public String description;
    public String posterSource;

    public Movie(String folderPath) {
        super(folderPath);
        createInfoTxt();
        setMovieGender();
        setMoviePoster();
        setMovieDescription();
        setFileName();
        setAgeGroup();
    }

    private void createInfoTxt() {
        try {
            File info = new File(super.folderPath + "/info.txt");
            info.createNewFile();

            System.out.println(info);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void setMoviePoster() {
        File[] files = new File(super.folderPath).listFiles();
        for (File file : files) {
            if (file.toString().endsWith(".jpg") || file.toString().endsWith(".jpeg")) {
                String extension = file.toString().endsWith(".jpg") ? ".jpg" : ".jpeg";
                File rename = new File(file.getParent() + "/" + fileName + extension);
                file.renameTo(rename);
                File image = new File(folderPath + "/" + file.getName());
                File f =  new File(image.getPath());
                String encodstring = encodeFileToBase64Binary(f);
                this.posterSource = "data:image/jpeg;base64," + encodstring;
                break;
            } else {
                this.posterSource = "load-default";
            }
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
