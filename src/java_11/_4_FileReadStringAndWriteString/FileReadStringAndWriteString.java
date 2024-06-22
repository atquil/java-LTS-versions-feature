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
        //Older ways of creating the path
        Path path = FileSystems.getDefault().getPath("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
        String multiLinesFile = Files.readString(path, StandardCharsets.UTF_8);

        Path betterWayOfCreatingPath = Path.of("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
        String multiLinesFileWithNewPath= Files.readString(betterWayOfCreatingPath, StandardCharsets.UTF_8);

        multiLinesFileWithNewPath.lines()
                .forEach(System.out::println);

        System.out.println("------------ Reading Using FileSystem with Path.of() -------------");
        multiLinesFileWithNewPath.lines()
                .forEach(System.out::println);


        System.out.println("------------ Reading Using BufferReader-------------");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        reader.lines().forEach(System.out::println);

        System.out.println("------------ Writing on file -------------");
        Files.writeString(path,"I am a new line",StandardCharsets.UTF_8);
        Files.readString(path, StandardCharsets.UTF_8)
                .lines()
                .forEach(System.out::println);

        //We can also create Path of file instead of using Paths.get() or File.toPath() we can use

        //Relative Path <path>
        Path path1 = Path.of("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
        Path path2 = Path.of("src","java_11","_4_FileReadStringAndWriteString","TestFile.txt");
        Path path3 = Path.of("src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");

        //Absolute Path /<path>
        Path path5 = Path.of("/src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
        Path path6 = Path.of("/src","java_11","_4_FileReadStringAndWriteString","TestFile.txt");
        Path path7 = Path.of("/src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");
        Path path8 = Path.of("/","/src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");

    }
}
