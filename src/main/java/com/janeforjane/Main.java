package com.janeforjane;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        CharsThings charsThings = new CharsThings();
        CollectionThings collectionThings = new CollectionThings();
        WriteReadThings writeReadThings = new WriteReadThings();

//        charsThings.removeChars();
//        charsThings.checkString();
//        charsThings.trimString();
//        charsThings.collapseFromString();
//        charsThings.replaceFromString();
//        charsThings.countOccurrences();
//        charsThings.splitString();

//        collectionThings.listIntoString();
//        collectionThings.mapIntoString();
//        collectionThings.joinNestedColl();
//        collectionThings.listFromString();
//        collectionThings.checkContainingWithCustomRule();

//        writeReadThings.writeToFile();
//        writeReadThings.writeToFileMultipleLinesWithSeparator();
//        writeReadThings.writeBytesToFile();
//        writeReadThings.readFromFile();
        writeReadThings.readFromFileToList();


    }
}
