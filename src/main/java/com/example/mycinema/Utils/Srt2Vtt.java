package com.example.mycinema.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Srt2Vtt {

    public static void main(String[] args) {
        // Set the input and output file paths here
        String inputFilePath = "src/main/java/com/example/mycinema/Utils/r1.srt";
        String outputFilePath = "src/main/java/com/example/mycinema/Utils/r1.vtt";

        try {
            String srtContent = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String webvttContent = srt2webvtt(srtContent);
            Files.write(Paths.get(outputFilePath), webvttContent.getBytes());
            System.out.println("Conversion successful! WebVTT file created at " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
        }
    }

    public static String srt2webvtt(String data) {
        // Remove carriage returns
        String srt = data.replaceAll("\r+", "");
        // Trim white space from start and end
        srt = srt.trim();

        // Get cues
        String[] cuelist = srt.split("\n\n");
        StringBuilder result = new StringBuilder();

        if (cuelist.length > 0) {
            result.append("WEBVTT\n\n");
            for (String cue : cuelist) {
                result.append(convertSrtCue(cue));
            }
        }

        return result.toString();
    }

    public static String convertSrtCue(String caption) {
        // Remove all HTML tags for security reasons
        // caption = caption.replaceAll("<[a-zA-Z\\/][^>]*>", "");

        StringBuilder cue = new StringBuilder();
        String[] s = caption.split("\n");

        // Concatenate multi-line string separated in array into one
        while (s.length > 3) {
            StringBuilder secondLine = new StringBuilder(s[2]);
            for (int i = 3; i < s.length; i++) {
                secondLine.append("\n").append(s[i]);
            }
            s[2] = secondLine.toString();
            String[] temp = new String[3];
            System.arraycopy(s, 0, temp, 0, 3);
            s = temp;
        }

        int line = 0;

        // Detect identifier
        if (!s[0].matches("\\d+:\\d+:\\d+") && s[1].matches("\\d+:\\d+:\\d+")) {
            cue.append(s[0].replaceAll("\\w+", "")).append("\n");
            line += 1;
        }

        // Get time strings
        if (s[line].matches("\\d+:\\d+:\\d+")) {
            // Convert time string
            Pattern pattern = Pattern.compile("(\\d+):(\\d+):(\\d+)(?:,(\\d+))?\\s*--?>\\s*(\\d+):(\\d+):(\\d+)(?:,(\\d+))?");
            Matcher matcher = pattern.matcher(s[line]);
            if (matcher.find()) {
                cue.append(String.format("%s:%s:%s.%s --> %s:%s:%s.%s\n",
                        matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                        matcher.group(5), matcher.group(6), matcher.group(7), matcher.group(8)));
                line += 1;
            } else {
                // Unrecognized time string
                return "";
            }
        } else {
            // File format error or comment lines
            return "";
        }

        // Get cue text
        if (s.length > line) {
            cue.append(s[line]).append("\n\n");
        }

        return cue.toString();
    }

}
