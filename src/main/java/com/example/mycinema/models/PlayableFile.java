package com.example.mycinema.models;

import com.example.mycinema.Contracts.IPlayableInfo;
import com.example.mycinema.Contracts.IPlayableTxtAditionalInfoFile;
import java.io.File;

public abstract class PlayableFile implements IPlayableInfo, IPlayableTxtAditionalInfoFile {
    public String folderPath;
    public String fileName;
    public String extension;
    public File file;

    public PlayableFile(String folderPath) {
        this.folderPath = folderPath;
        setPlayableFile(folderPath);
        setFileExtension();
    }

    private void setPlayableFile(String folderPath) {
        File[] subFiles = new File(folderPath).listFiles();
        File movieFile = null;
        for (File file : subFiles) {
            if(file.toString().endsWith(".mp4") || file.toString().endsWith(".mkv")) {
                this.file = file;
                this.fileName = this.file.getName().trim().substring(0, file.getName().length() - 4);
                this.extension = this.file.getName().trim().substring(file.getName().length() - 4);
                String fileOriginalName = this.fileName;
                String sanitizedName = fileOriginalName.replaceAll("[\\[\\]\\{\\}\\(\\)]","");
                file.renameTo(new File(file.getParent() + "/" + sanitizedName + extension));
                this.fileName = sanitizedName;
            }
        }
    }

    @Override
    public void setFileName() {
        this.fileName = this.file.getName().trim().substring(0, file.getName().length() - 4);
    }

    @Override
    public void setFileExtension() {
        this.extension = this.file.getName().trim().substring(file.getName().length() - 4);
    }
}
