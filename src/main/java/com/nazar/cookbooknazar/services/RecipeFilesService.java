package com.nazar.cookbooknazar.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesService {
    @Value("${path.to.data.fileRecipe}")
    private String dataFilePath;
    @Value("${name.of.data.fileRecipe}")
    private String dataFileName;

    public boolean saveToFileRec(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String readFromFileRec() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileName));
            Files.createFile(Path.of(dataFilePath, dataFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}