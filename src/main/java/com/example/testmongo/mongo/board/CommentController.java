package com.example.testmongo.mongo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BoardRepositiory boardRepositiory;

    @PostMapping("/save")
    public String test1(@RequestBody  Comment comment){
        String result = "";

        Board board = boardRepositiory.findById(comment.getBoardId()).get();

        if(board != null){
            Comment[] comments = board.getComments();
            int arrLen = comments == null ? 0 : comments.length;

            // new comment
            if(StringUtils.isEmpty(comment.getCommentId())){
                comment.setCommentId(Integer.toString(arrLen+1));
                if(comments == null) {
                    comments = new Comment[1];
                    comments[comments.length-1] = comment;
                }else{
                    // array length increase
                    comments = Arrays.copyOf(comments, comments.length+1);
                    comments[arrLen] = comment;
                }
            }
            // modify comment
            else{
                for (int i=0; i<comments.length; i++) {
                    if(comments[i].getCommentId().equals(comment.getCommentId())){
                        comments[i] = comment;
                    }
                }
            }
            board.setComments(comments);
            boardRepositiory.save(board);
            result = "Y";
        }else{
            result = "N";
        }

        return result;
    }
}
