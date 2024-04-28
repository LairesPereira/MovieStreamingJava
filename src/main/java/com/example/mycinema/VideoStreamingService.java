package com.example.mycinema;

import com.example.mycinema.Contracts.IDirInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
public class VideoStreamingService implements IDirInfo {
    public File movie;
    private final String MOVIES_SOURCE_PATH = "src/main/resources/movies";
    private File moviesFolder = new File(MOVIES_SOURCE_PATH);
    private static final String FORMAT="classpath:/movies";
    private String streamTitle;

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getVideoStreaming(String titleName) {
        this.streamTitle = titleName;
        System.out.println("Aqui");
        System.out.println(String.format("%s%s%s%s%s", FORMAT, getMovie(titleName), "/", titleName, ".mp4"));
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format("%s%s%s%s%s", FORMAT,  getMovie(titleName), "/", titleName, ".mp4")));
    }

    public String getMovie(String titleName) {
        File[] moviesFolders = moviesFolder.listFiles();
        for (File movieFolder : moviesFolders) {
            if (isDirectory(movieFolder)) {
                return getDirFilesExtensions(movieFolder.getPath());
            }
        }
        return  "";
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
        String parentPath = "";
        for (File file : subFiles) {
            if (file.getName().equals(streamTitle + ".mp4")) {
                System.err.println(file.getParent());
                parentPath = file.getParent().substring(file.getParent().lastIndexOf("/"));
                break;
            }
        }
        return parentPath;
    }

    public String getVideoExtension(String titleSearch) {
        File[] moviesTitles = moviesFolder.listFiles();
        String extension;
        assert moviesTitles != null;
        for (File movie : moviesTitles) {
            String title = movie.getName().trim().substring(0, movie.getName().length() - 4);
            if (title.equals(titleSearch)) {
                return  movie.getName().trim().substring(movie.getName().length() - 4);
            }
        }
        return "mp4";
    }
}