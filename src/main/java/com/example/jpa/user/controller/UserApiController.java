package com.example.jpa.user.controller;

import com.example.jpa.user.db.UserEntity;
import com.example.jpa.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/find-all")
    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    //Java8 이상부터 @RequestParam에 파라미터 이름을 명시적으로 지정해줘야함
    @GetMapping("/name")
    public ResponseEntity<Void> autoSave(@RequestParam("name") String name) {
        var user = UserEntity.builder()
                .name(name)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}