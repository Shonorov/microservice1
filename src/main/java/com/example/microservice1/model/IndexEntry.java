package com.example.microservice1.model;

import org.springframework.data.annotation.Id;

import java.util.TreeSet;

public class IndexEntry {

    @Id
    private String word;

    private TreeSet<Long> documents;

    public IndexEntry() {
    }

    public IndexEntry(String word) {
        this.word = word;
        this.documents = new TreeSet<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TreeSet<Long> getDocuments() {
        return documents;
    }

    public void setDocuments(TreeSet<Long> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "IndexEntry{" +
                "word='" + word + '\'' +
                ", documents=" + documents +
                '}';
    }
}
