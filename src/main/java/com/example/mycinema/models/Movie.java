package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumAgeGroup;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

public class Movie extends PlayableFile implements WebMvcConfigurer {

    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";

    public String gender;
    public String description;
    public String posterSource;
    public int enumAgeGroup = EnumAgeGroup.FIRST_AGE_GROUP.age;

    public Movie(String folderPath) {
        super(folderPath);
        setMovieGender();
        setMoviePoster();
        setMovieDescription();
        setFileName();
        setAgeGroup();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ajuste o caminho conforme o local do seu diretório
        registry.addResourceHandler(SOURCE_PATH + "/**")
                .addResourceLocations(SOURCE_PATH);
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
        this.description = searchInfo(super.folderPath, "DESCRIPTION");
    }

    @Override
    public void setAgeGroup() {
        try {
            this.enumAgeGroup = Integer.parseInt(searchInfo(folderPath, "AGEGROUP:"));
        } catch (NumberFormatException e) {
            System.out.println("No age group Found");
        }
    }

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
