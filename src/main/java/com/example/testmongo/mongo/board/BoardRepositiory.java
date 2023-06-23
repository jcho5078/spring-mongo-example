package com.example.testmongo.mongo.board;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepositiory extends MongoRepository<Board, String> {
}
