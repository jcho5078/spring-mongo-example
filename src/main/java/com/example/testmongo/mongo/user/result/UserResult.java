package com.example.testmongo.mongo.user.result;

import com.example.testmongo.mongo.board.Board;
import com.example.testmongo.mongo.board.Comment;
import lombok.Data;

@Data
public class UserResult {

    private String id;
    private String name;
    private Board[] boards;
    private Comment[] comments;
}
