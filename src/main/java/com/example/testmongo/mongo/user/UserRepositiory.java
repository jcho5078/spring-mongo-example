package com.example.testmongo.mongo.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositiory extends MongoRepository<User, String> {

    public User findByName(String name);
}
