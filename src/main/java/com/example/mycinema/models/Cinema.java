package com.example.mycinema.models;

import java.io.File;
import java.util.ArrayList;

public class Cinema  {

    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";

    public ArrayList<Movie> movies = new ArrayList<Movie>();
    private File[] moviesDir = new File(SOURCE_PATH).listFiles();

    public Cinema() {
        getMoviesList();
    }

    public void removeMovie(String title) {
        for (Movie movie : movies) {
            if (movie.fileName.equals(title)) {
                movies.remove(movie);
            }
        }
    }

    public void getMoviesList() {
        System.err.println(moviesDir.length);
        if (moviesDir.length != 0) {
            for (File movieDir: moviesDir) {
                if(movieDir.isDirectory()) {
                    File[] subFiles = new File(movieDir.getPath()).listFiles();
                    for (File file : subFiles) {
                        if(file.getName().endsWith(".mp4") || file.getName().endsWith(".mkv")) {
                            System.out.println(file.getParent());
                            movies.add(new Movie(movieDir.getPath()));
                        }
                    }
                }
            }
        }

    }
}
