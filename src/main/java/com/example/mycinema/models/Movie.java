package com.example.mycinema.models;

import java.io.File;

public class Movie extends PlayableFile {

    public Movie(String folderPath) {
        super(folderPath);
    }

    @Override
    public String movieExtension() {
        return null;
    }

    @Override
    public String movieName() {
        return null;
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
}
