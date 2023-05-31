package com.example.springboot.controller.myController;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class myController {
    
    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        return ResponseEntity.ok(" -Info: \n" + response);
    }
    
    @GetMapping("/random")
    public ResponseEntity<String> getRandom() {
        boolean random= new Random().nextBoolean();
        return random ?
                       ResponseEntity.ok(" -Random boolean Ok ") :
                       ResponseEntity.badRequest().body(" -Random boolean Bad ");
    }
    
}
