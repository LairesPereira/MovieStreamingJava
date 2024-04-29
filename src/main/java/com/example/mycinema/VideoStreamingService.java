package com.example.mycinema;

import com.example.mycinema.Contracts.IDirInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
public class VideoStreamingService {
    public File movie;
    private final File MOVIES_SOURCE_PATH = new File("src/main/resources/movies");
    private static final String FORMAT="classpath:/movies";

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getVideoStreaming(String titleName) {
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format("%s%s", FORMAT,  getMovieFolder(titleName))));
    }

    public String getMovieFolder(String titleName) {
        File[] moviesFolders = MOVIES_SOURCE_PATH.listFiles();
        String fileParentFolder = "";
        String finalPath = "";

        assert moviesFolders != null;
        for (File movieFolder : moviesFolders) {
            if (movieFolder.isDirectory()) {
                File[] filesInside = new File(movieFolder.getPath()).listFiles();
                for (File file : filesInside) {
                    // remove extension to compare with titleName from browser request, then check if it is a valid format
                    if (
                        file.getName().substring(0, file.getName().length() - 4).equals(titleName) &&
                        (file.getName().endsWith(".mp4") || file.getName().endsWith(".mkv")))
                    {
                        fileParentFolder = file.getParent().substring(file.getParent().lastIndexOf("/"));
                        finalPath = fileParentFolder + "/" + file.getName();
                    }
                }
            }
        }
        return  finalPath;
    }
}