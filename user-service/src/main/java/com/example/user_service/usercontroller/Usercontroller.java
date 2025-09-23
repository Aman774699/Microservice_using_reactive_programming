package com.example.user_service.usercontroller;

import com.example.user_service.entity.Userdto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @GetMapping("/getAll")
    public Flux<Userdto> allUser() {
        return Flux
                .just(new Userdto("user_1", "Fraser"),
                        new Userdto("user_2", "Aman"))
                .delayElements(Duration.ofMillis(500));
    }

    @GetMapping("/{id}")
    public Mono<Userdto> userById(@PathVariable String id)
    {
        return Mono.just(new Userdto(id,"Name-"+id));
    }
}
