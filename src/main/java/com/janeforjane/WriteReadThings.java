package com.janeforjane;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WriteReadThings {



    public void writeToFile() throws IOException {

        File file = new File("test.txt");

        //new file
        String expectedValue = "Hello world";

        Files.write(expectedValue, file, Charsets.UTF_8);
        String result = Files.toString(file, Charsets.UTF_8);

        System.out.println(result);//Hello world


        //append to an existing file
        String extraValue = "Hello wonderful world";

        Files.append(extraValue, file, Charsets.UTF_8);
        String result2 = Files.toString(file, Charsets.UTF_8);

        System.out.println(result2);//Hello worldHello wonderful world


    }

    public void writeToFileMultipleLinesWithSeparator() throws IOException {

        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        File file = new File("test.txt");
        CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
        sink.writeLines(names, " ");
//        sink.write(expectedValue); - just for string

        String result = Files.toString(file, Charsets.UTF_8);
        String expectedValue = Joiner.on(" ").join(names);
    }

    public void writeBytesToFile() throws IOException {

        //jpg file
        File im = new File("korzhik.jpg");
        byte[] fileContent = Files.toByteArray(im);

        File file = new File("k.jpg");

        ByteSink sink = Files.asByteSink(file);
        sink.write(fileContent);

        //text file
        String expectedValue = "Hello world";

        File txtfile = new File("test.txt");

        ByteSink tsink = Files.asByteSink(txtfile);
        tsink.write(expectedValue.getBytes());
    }

    public void readFromFile() throws IOException {

        File file = new File("test.txt");
        String result = Files.toString(file, Charsets.UTF_8);

        //2nd variant
//        CharSource source = Files.asCharSource(file, Charsets.UTF_8);
//        String result = source.read();

        System.out.println(result);
    }

    public void readFromFileToList() throws IOException {

        File file = new File("test.txt");
        List<String> result = Files.readLines(file, Charsets.UTF_8);

        System.out.println(result.get(1));
    }

    public void readAndConcatenateTwoFiles() throws IOException {

        File file1 = new File("test1.txt");
        File file2 = new File("test.txt");

        CharSource source1 = Files.asCharSource(file1, Charsets.UTF_8);
        CharSource source2 = Files.asCharSource(file2, Charsets.UTF_8);
        CharSource source = CharSource.concat(source1, source2);

        String result = source.read();
    }

    public void readBytesFromFile() throws IOException {
        File file = new File("test.txt");
        ByteSource source = Files.asByteSource(file);

        byte[] result = source.read();
    }

    public void readBytesFromFileAfterSpecificPlace() throws IOException {
        File file = new File("test.txt");
        long offset = 3;
        long len = 1000;
        ByteSource source = Files.asByteSource(file).slice(offset, len);

        byte[] result = source.read();
    }

    public void readFromFileStream() throws IOException {
        FileInputStream reader = new FileInputStream("test.txt");
        byte[] result = ByteStreams.toByteArray(reader);
        reader.close();
    }

    public void readFromResource() throws IOException {
        URL url = Resources.getResource("test.txt");
        String result = Resources.toString(url, Charsets.UTF_8);
    }
}
