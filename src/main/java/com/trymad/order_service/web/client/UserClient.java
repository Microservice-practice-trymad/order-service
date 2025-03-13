package com.trymad.order_service.web.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trymad.order_service.web.dto.UserDTO;

@FeignClient(
    name = "${client.user.name}"
)
public interface UserClient {
	
	@GetMapping("${client.user.apiPath}/{uuid}")
	UserDTO getById(@PathVariable UUID uuid);

}
