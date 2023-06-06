package com.cuota.service.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {
	private String exception;
	private String message;
	private String path;
}
