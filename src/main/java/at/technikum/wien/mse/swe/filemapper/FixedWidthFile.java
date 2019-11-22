package at.technikum.wien.mse.swe.filemapper;

import at.technikum.wien.mse.swe.exception.SecurityAccountOverviewReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FixedWidthFile {
    private final Path path;
    private String content;

    FixedWidthFile(Path path) {
        this.path = path;
    }

    String getContent() {
        if (content == null) {
            readFileContent();
        }
        return content;
    }

    private void readFileContent() {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            content = reader.readLine();
        } catch (IOException e) {
            throw new SecurityAccountOverviewReadException(e);
        }
    }
}
