package com.example.mycinema.Contracts;

import java.io.File;

public interface IPlayableInfo {
     String movieExtension();
     String movieName();
     String movieGender();
     String movieDescription();
     File poster();
     float movieDuration();
}
