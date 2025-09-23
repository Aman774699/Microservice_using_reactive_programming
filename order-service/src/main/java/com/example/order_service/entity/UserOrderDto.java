package com.example.order_service.entity;

import com.example.order_service.entity.userdto.Userdto;

import java.util.List;

public record UserOrderDto(Userdto userdto ,List<OrderDto> order) {
}
