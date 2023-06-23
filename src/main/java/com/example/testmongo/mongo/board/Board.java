package com.example.testmongo.mongo.board;

import com.example.testmongo.mongo.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "board")
@Data
public class Board {

    @Id
    private String id;
    private String userId;
    private String title;
    private String detail;

    private Comment[] comments;
}
