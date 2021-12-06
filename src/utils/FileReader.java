package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {
    public static List<String> readLinesFromFile(String path) {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.toList();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
    public static String[] readLinesFromFileToStringArray(String path) {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
