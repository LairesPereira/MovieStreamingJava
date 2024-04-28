package com.example.mycinema.Contracts;

import java.io.File;

public interface IDirInfo {
    boolean isDirectory(File path);
    String getDirFilesExtensions(String path);
}
