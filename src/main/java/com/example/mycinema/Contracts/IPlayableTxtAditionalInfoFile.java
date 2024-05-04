package com.example.mycinema.Contracts;

import com.example.mycinema.Enums.EnumAgeGroup;

public interface IPlayableTxtAditionalInfoFile {
    String searchInfo(String folderPath, String value);
    void setAgeGroup(String folderPath);
}
