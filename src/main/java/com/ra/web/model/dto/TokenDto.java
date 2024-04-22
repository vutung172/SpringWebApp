package com.ra.web.model.dto;

import com.ra.web.model.entity.TokenEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    private String employeeId;
    private TokenEntity token;
}
