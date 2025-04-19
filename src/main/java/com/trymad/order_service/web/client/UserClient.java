package com.trymad.order_service.web.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trymad.order_service.web.dto.UserDTO;

@FeignClient(
    name = "${service.userService.name}",
	url = "${service.userService.protocol}://${service.userService.name}:${service.userService.port}"
)
public interface UserClient {
	
	@GetMapping("${service.userService.apiPath}/{uuid}")
	UserDTO getById(@PathVariable UUID uuid);

}
