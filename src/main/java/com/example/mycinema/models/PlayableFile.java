package com.example.mycinema.models;

import com.example.mycinema.Contracts.IPlayableInfo;

import java.io.File;

abstract public class PlayableFile implements IPlayableInfo {
    public String folderPath;
    public String title;
    public File movie;

    public PlayableFile(String folderPath) {
        this.folderPath = folderPath;
        setMovieFile(folderPath);
        this.title = setMovieTitle();
    }

    private void setMovieFile(String folderPath) {
        File[] subFiles = new File(folderPath).listFiles();
        File movieFile = null;
        for (File file : subFiles) {
            if(file.toString().endsWith(".mp4") || file.toString().endsWith(".mkv")) {
                movieFile = file;
                break;
            }
        }
        this.movie = movieFile;
    }

    private String setMovieTitle() {
        return this.movie.getName().trim().substring(0, movie.getName().length() - 4);
    }
}
