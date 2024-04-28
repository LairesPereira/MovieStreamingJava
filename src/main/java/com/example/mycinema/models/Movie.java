package com.example.mycinema.models;

import com.example.mycinema.Contracts.IMovieInfo;

import java.io.File;

public class Movie implements IMovieInfo {
    public String folderPath;
    public File movie;
    public String name;
    public String extension;
    public boolean isValidFormat;

    public Movie(String folderPath) {
        setMovieFile(folderPath);
        this.name = movieName();
        this.extension = movieExtension();
    }

    private void setMovieFile(String folderPath) {
        File[] subFiles = new File(folderPath).listFiles();
        File movieFile = null;
        for (File file : subFiles) {
            if( file.toString().substring(file.toString().length() - 4).equals(".mp4") ||
                file.toString().substring(file.toString().length() - 4).equals(".mkv"))
            {
                movieFile = file;
                break;
            }
        }
        this.movie = movieFile;
    }

    public String movieName() {
        return this.movie.getName().trim().substring(0, movie.getName().length() - 4);
    }

    @Override
    public String movieExtension() {
        return movie.getName().substring(movie.getName().length() - 4);
    }

    @Override
    public String movieGender() {
        return null;
    }

    @Override
    public String movieDescription() {
        return null;
    }

    @Override
    public File poster() {
        return null;
    }

    @Override
    public float movieDuration() {
        return 0;
    }

    @Override
    public boolean isValidFormat() {
        return false;
    }
}
