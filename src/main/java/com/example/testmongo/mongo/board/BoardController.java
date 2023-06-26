package com.example.testmongo.mongo.board;

import com.example.testmongo.mongo.user.User;
import com.example.testmongo.mongo.user.UserRepositiory;
import com.example.testmongo.mongo.user.result.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BoardRepositiory boardRepositiory;

    @Autowired
    private UserRepositiory userRepositiory;

    @PostMapping("/save")
    public String test1(@RequestBody Board board){
        Board result = boardRepositiory.save(board);

        return result.toString();
    }

    @GetMapping("/find")
    public String test2(@RequestParam(value = "id") String id){
        Board result = boardRepositiory.findById(id).get();

        return result.toString();
    }

    @GetMapping("/findAll")
    public String test3(){
        List<Board> list = boardRepositiory.findAll();

        return list.toString();
    }

    @GetMapping("/findBoardsByUserId")
    public String test4(@RequestParam(value = "userId") String userId){

        LookupOperation lookup = LookupOperation.newLookup()
                .from("board")
                .localField("_id")
                .foreignField("userId")
                .as("boards");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(userId)), lookup);
        UserResult result = mongoTemplate.aggregate(aggregation, "user", UserResult.class).getMappedResults().get(0);

        return result.toString();
    }

    @GetMapping("/findCommentsByUserId")
    public String test5(@RequestParam(value = "userId") String userId){

        LookupOperation lookup = LookupOperation.newLookup()
                .from("board")
                .localField("_id")
                .foreignField("userId")
                .as("boards");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(userId)), lookup);
        UserResult userResult = mongoTemplate.aggregate(aggregation, "user", UserResult.class).getMappedResults().get(0);

        Board[] boards = userResult.getBoards();
        List<Comment> resultComments = new ArrayList();

        for(Board board:boards){
            if(board.getComments() != null){
                for(int i=0; i<board.getComments().length; i++){
                    if(userId.equals(board.getComments()[i].getUserId())){
                        resultComments.add(board.getComments()[i]);
                    }
                }
            }
        }

        // convert to array
        userResult.setComments(resultComments.toArray(new Comment[resultComments.size()]));

        return userResult.toString();
    }
}
