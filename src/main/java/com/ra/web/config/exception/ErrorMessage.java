package com.ra.web.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String code;
    private String message;
}
