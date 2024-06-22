package java_11._4_FileReadStringAndWriteString;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author atquil
 */
public class FileReadStringAndWriteString {
    public static void main(String[] args) throws IOException {

        System.out.println("------------ Reading Using FileSystem -------------");
        Path path = FileSystems.getDefault().getPath("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");

        String multiLinesTes = Files.readString(path, StandardCharsets.UTF_8);

        multiLinesTes.lines()
                .forEach(System.out::println);


        System.out.println("------------ Reading Using BufferReader-------------");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        reader.lines().forEach(System.out::println);

        System.out.println("------------ Writing on file -------------");
        Files.writeString(path,"I am a new line",StandardCharsets.UTF_8);
        Files.readString(path, StandardCharsets.UTF_8)
                .lines()
                .forEach(System.out::println);
    }
}
