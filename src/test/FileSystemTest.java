package test;

import main.java.FileSystem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class FileSystemTest {

    @Test
    public void givenNOnEmptyFolder_shouldReturnFileNamesWithSameContent() throws IOException {
        File[] testFiles = FileSystem.listFiles("src/main/resources");

        List<String> duplicateFileNames = FileSystem.findDuplicate(testFiles);

        assertTrue(duplicateFileNames.contains("test.txt"));
        assertTrue(duplicateFileNames.contains("file1.txt"));
    }

    @Test
    public void givenEmptyFolder_shouldReturnNoFileName() throws IOException {
        File[] testFiles = FileSystem.listFiles("src/main/resources/empty");

        List<String> duplicateFileNames = FileSystem.findDuplicate(testFiles);

        assertTrue(duplicateFileNames.isEmpty());
    }

}
