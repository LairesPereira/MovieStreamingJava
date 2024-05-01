package com.example.mycinema.models;

import java.io.File;

public class Movie extends PlayableFile  {
    public String gender;
    public String description;
    public String posterSource;
    private MovieAboutInfo movieAboutInfo;

    public Movie(String folderPath, MovieAboutInfo movieAboutInfo) {
        super(folderPath);
        this.movieAboutInfo = movieAboutInfo;
        setMovieGender();
        setMoviePoster();
        setMovieDescription();
        setFileName();
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
        this.gender = movieAboutInfo.searchInfo(super.folderPath, "GENDER");
    }

    public void setMovieDescription() {
        this.description = movieAboutInfo.searchInfo(super.folderPath, "DESCRIPTION");
    }
}
