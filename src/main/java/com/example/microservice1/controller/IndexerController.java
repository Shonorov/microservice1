package com.example.microservice1.controller;

import com.example.microservice1.model.IndexEntry;
import com.example.microservice1.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.TreeSet;

@RestController
public class IndexerController {

    @Autowired
    private IndexRepository repository;

    private static final String UTF8_BOM = "\uFEFF";

    @GetMapping("/getByWord")
    public TreeSet<Long> getWordIndex(@RequestParam("word") String word) {
        Optional<IndexEntry> optional = repository.findById(word);
        TreeSet<Long> response = new TreeSet<>();
        optional.ifPresent(indexEntry -> response.addAll(indexEntry.getDocuments()));
        return response;
    }

    @PostMapping("/index")
    public HttpStatus indexContent(@RequestParam("bytes") byte[] bytes, @RequestParam("id") long id) {
        String text = new String(bytes);
        text = text.toLowerCase();
        if (text.startsWith(UTF8_BOM)) {
            text = text.substring(1);
        }
        for (String word : text.split(" ")) {
            addIndex(word, id);
        }
        return HttpStatus.OK;
    }

    @PostMapping(value = "/indexFile", consumes = {"multipart/form-data"})
    public HttpStatus indexMultipartContent(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        String text = null;
        try {
            text = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = text.replaceAll("[^A-Za-z0-9 ]", " ");
        text = text.toLowerCase();
        if (text.startsWith(UTF8_BOM)) {
            text = text.substring(1);
        }
        for (String word : text.split(" ")) {
            addIndex(word, id);
        }
        return HttpStatus.OK;
    }

    private void addIndex(String word, long id) {
        Optional<IndexEntry> optional = repository.findById(word);
        if (optional.isPresent()) {
            IndexEntry entry = optional.get();
            entry.getDocuments().add(id);
            repository.save(entry);
        } else {
            IndexEntry newEntry = new IndexEntry(word);
            newEntry.getDocuments().add(id);
            repository.insert(newEntry);
        }
    }
}
