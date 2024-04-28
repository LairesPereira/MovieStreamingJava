package com.example.mycinema.models;

import com.example.mycinema.Contracts.IDirInfo;

import java.io.File;
import java.util.ArrayList;

public class Cinema implements IDirInfo {
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    private File[] moviesDir = new File("src/main/resources/movies").listFiles();

    public Cinema() {
        getMoviesList();
    }

    public void getMoviesList() {
        for (File movieDir: moviesDir) {
            if(isDirectory(movieDir)) {
                System.err.println(getDirFilesExtensions(movieDir.getPath()));
                if (getDirFilesExtensions(movieDir.getPath()).equals(".mp4") || getDirFilesExtensions(movieDir.getPath()).equals(".mkv")){
                    movies.add(new Movie(movieDir.getPath()));
                };
            }
        }
    }

    @Override
    public boolean isDirectory(File path) {
        if (path.isDirectory()) {
            return true;
        }
        return false;
    }

    @Override
    public String getDirFilesExtensions(String path) {
        File[] subFiles = new File(path).listFiles();
        for (File file : subFiles) {
            System.out.println(file.getName());
            return file.toString().substring(file.toString().length() - 4);
        }
        return "";
    }
}
