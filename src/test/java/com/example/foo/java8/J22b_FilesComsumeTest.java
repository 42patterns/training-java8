package com.example.foo.java8;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;

/**
 * Reading files should not be so verbose. Make it more compact
 */
public class J22b_FilesComsumeTest {

    final String FILENAME = "/sample_file.txt";

    @Test
    /**
     *  Do not change this method, nor its signature.
     */
    public void should_read_data_from_file() {
        String content = readFromFile(FILENAME);
        Assert.assertThat(content, is(equalTo("File content.")));
    }

    /**
     * You can you java.util.Scanner
     *  hint: you can use EOF (end of file) delimiter "\\A"
     * or java.nio.Files methods
     */
    private String readFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            Paths.get(
                                    this.getClass().getResource(FILENAME).toURI()).toFile()));
            StringBuilder builder = new StringBuilder();
            String l;
            while((l = reader.readLine()) != null) {
                builder.append(l);
            }

            return builder.toString();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new String();
        }
    }

}
