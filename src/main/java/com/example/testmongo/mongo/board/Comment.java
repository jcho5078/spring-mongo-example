package com.example.testmongo.mongo.board;

import lombok.Data;

@Data
public class Comment {

    private String id;
    private String boardId;
    private String userId;
    private String detail;
}
