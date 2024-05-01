package com.example.mycinema.models;

import com.example.mycinema.Contracts.IDirInfo;

import java.io.File;
import java.util.ArrayList;

public class Cinema  {
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    private File[] moviesDir = new File("src/main/resources/static/movies").listFiles();

    public Cinema() {
        getMoviesList();
    }

    public void getMoviesList() {
        for (File movieDir: moviesDir) {
            if(movieDir.isDirectory()) {
                File[] subFiles = new File(movieDir.getPath()).listFiles();
                for (File file : subFiles) {
                    if(file.getName().endsWith(".mp4") || file.getName().endsWith(".mkv")) {
                        movies.add(new Movie(movieDir.getPath(), new MovieAboutInfo()));
                    }
                }
            }
        }//
    }
}
