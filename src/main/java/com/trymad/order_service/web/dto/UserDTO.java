package com.trymad.order_service.web.dto;

import java.util.UUID;

public record UserDTO(
	UUID id,

	String name,

	String surname,

	String mail, 

	String phone
) {}
