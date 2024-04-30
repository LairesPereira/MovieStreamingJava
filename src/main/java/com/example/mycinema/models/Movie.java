package com.example.mycinema.models;

import com.example.mycinema.Contracts.IMovieAditionalInfoFile;
import com.example.mycinema.Enums.EnumMovieGender;
import jakarta.annotation.PostConstruct;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Movie extends PlayableFile implements IMovieAditionalInfoFile {
    public String gender = EnumMovieGender.OTHER.getGender();
    public String description;
    public String posterId;
    public String posterExtension;
    public String posterParentFolder;

    public Movie(String folderPath) {
        super(folderPath);
        setMovieGender();
        setMoviePoster();
    }

    @Override
    public String fileExtension() {
        return null;
    }

    @Override
    public String fileName() {
        return null;
    }

    @Override
    public float fileDuration() {
        return 0;
    }

    @Override
    public void setMovieGender() {
        File[] files = new File(super.folderPath).listFiles();
        for (File file : files) {
            try {
                if (file.toString().endsWith(".txt")) {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {
                        String read = scan.nextLine();
                        if (read.contains("GENDER:")){
                            String gender = read.substring(read.lastIndexOf(":") + 1).toUpperCase();
                            for (EnumMovieGender enumgender: EnumMovieGender.values()) {
                                System.out.println(enumgender.toString());
                                if (enumgender.toString().equals(gender)) {
                                    this.gender = enumgender.toString();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void movieDescription() {
    }

    @Override
    public void setMoviePoster() {
        File[] files = new File(super.folderPath).listFiles();
        for (File file : files) {
            if (file.toString().endsWith(".jpg") || file.toString().endsWith(".jpeg")) {
                String extension = file.toString().endsWith(".jpg") ? ".jpg" : ".jpeg";
                File rename = new File(file.getParent() + "/" + title + extension);
                file.renameTo(rename);
                this.posterId = file.getName();
                this.posterExtension = extension;
                this.posterParentFolder = folderPath.substring(folderPath.lastIndexOf("/"));
                break;
            }
        }
    }
}
