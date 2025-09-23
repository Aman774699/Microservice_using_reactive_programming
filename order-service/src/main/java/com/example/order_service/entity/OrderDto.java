package com.example.order_service.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public record OrderDto(String id, String userId, String item) {
}
