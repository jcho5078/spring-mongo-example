package com.example.testmongo.mongo.user;

import com.example.testmongo.mongo.board.Board;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;
    private String name;
}
