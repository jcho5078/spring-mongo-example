package com.example.testmongo.mongo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BoardRepositiory boardRepositiory;

    @PostMapping("/save")
    public String test1(@RequestBody Board board){
        Board result = boardRepositiory.save(board);

        return result.toString();
    }

    @GetMapping("/find")
    public String test2(Map<String, String> param){
        Board result = boardRepositiory.findById(param.get("id")).get();

        return result.toString();
    }

    @GetMapping("/findAll")
    public String test3(){
        List<Board> list = boardRepositiory.findAll();

        return list.toString();
    }

    @GetMapping("/findByUserId")
    public String test4(Map<String, String> param){
        LookupOperation lookup = LookupOperation.newLookup()
                .from("user")
                .localField("userId")
                .foreignField("boardId")
                .as("comments");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(param.get("userId"))), lookup);
        List<Board> resultList = mongoTemplate.aggregate(aggregation, "board", Board.class).getMappedResults();

        return resultList.toString();
    }
}
