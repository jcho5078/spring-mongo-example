package com.example.testmongo.mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepositiory userRepositiory;

    @PostMapping("/save")
    public String test1(@RequestBody User user){
        User result = userRepositiory.save(user);

        return result.toString();
    }

    @GetMapping("/find")
    public String test2(Map<String, String> param){
        User result = userRepositiory.findById(param.get("id")).get();

        return result.toString();
    }

    @GetMapping("/findAll")
    public String test3(){
        List<User> list = userRepositiory.findAll();

        return list.toString();
    }

    @GetMapping("/findByName")
    public String test4(Map<String, String> param){
        User result = userRepositiory.findByName(param.get("name"));

        return result.toString();
    }
}
