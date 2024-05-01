package com.example.mycinema.models;

import java.io.File;
import java.util.Scanner;

public class MovieAboutInfo extends ReadableTxtInfo {

    public String searchInfo(String folderPath, String value) {
        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            try {
                if (file.toString().endsWith(".txt")) {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {
                        String read = scan.nextLine();
                        if (read.contains(value)){
                            String info = read.substring(read.lastIndexOf(":") + 1);
                            return info;
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return "";
    }
}
