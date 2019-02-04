package com.example.microservice1.repository;

import com.example.microservice1.model.IndexEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends MongoRepository<IndexEntry, String> {
}
