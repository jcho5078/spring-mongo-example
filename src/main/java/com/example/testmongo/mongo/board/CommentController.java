package com.example.testmongo.mongo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BoardRepositiory boardRepositiory;

    @PostMapping("/save")
    public String test1(Comment comment){
        String result = "";

        Board board = boardRepositiory.findById(comment.getBoardId());

        if(board != null){
            Comment[] comments = board.getComments();
            for (comments:comment) {

            }
        }else{
            result = "N";
        }

        return result;
    }
}
