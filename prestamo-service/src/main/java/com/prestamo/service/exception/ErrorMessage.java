package com.prestamo.service.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
	private String exception;
	private String message;
	private String path;
}
