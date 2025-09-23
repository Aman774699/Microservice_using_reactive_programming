package com.example.order_service.ordercontroller;

import com.example.order_service.entity.OrderDto;
import com.example.order_service.entity.UserOrderDto;
import com.example.order_service.entity.userdto.Userdto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/order")
public class Ordercontroller {
    @GetMapping("/user/{userId}")
    public Flux<OrderDto> orderByUser(@PathVariable String userId)
    {
        return Flux.just(
                new OrderDto("o1",userId,"Book")
                ,new OrderDto("o2",userId,"Laptop")
        ).delayElements(Duration.ofMillis(700));
    }

    @GetMapping("/with-user/{userId}")
    public Mono<UserOrderDto> orderWithUser(@PathVariable String userId)
    {
        WebClient userClient= WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
        Mono<Userdto> user=userClient.get().uri("/user/{id}",userId)
                .retrieve().bodyToMono(Userdto.class);

        Flux<OrderDto> orders=orderByUser(userId);
        return user.zipWith(orders.collectList(),(u,o)->new UserOrderDto(u,o));
    }
}

