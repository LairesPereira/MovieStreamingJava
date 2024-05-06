package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumAgeGroup;

import java.io.File;
import java.util.Scanner;

public class Movie extends PlayableFile{
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

    public void setMoviePoster() {
        File[] files = new File(super.folderPath).listFiles();
        for (File file : files) {
            if (file.toString().endsWith(".jpg") || file.toString().endsWith(".jpeg")) {
                String extension = file.toString().endsWith(".jpg") ? ".jpg" : ".jpeg";
                File rename = new File(file.getParent() + "/" + fileName + extension);
                file.renameTo(rename);
                String posterParentPath = folderPath.substring(folderPath.lastIndexOf("/"));
                this.posterSource = posterParentPath + "/" + file.getName();
                break;
            }
        }
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
