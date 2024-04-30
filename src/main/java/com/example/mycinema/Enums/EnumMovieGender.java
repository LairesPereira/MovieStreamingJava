package com.example.mycinema.Enums;

public enum EnumMovieGender {
    HORROR("Horror"),
    DRAMA("Drama"),
    DOCUMENTARY("Documentary"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    OTHER("Other");

    public String gender;

    EnumMovieGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
