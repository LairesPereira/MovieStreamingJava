package com.example.mycinema.models;

import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;

@Service
public class Cinema  {
    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    private File[] moviesDir = new File(SOURCE_PATH).listFiles();

    public Cinema() {
        createCatalog();
    }

    public void createCatalog() {
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
