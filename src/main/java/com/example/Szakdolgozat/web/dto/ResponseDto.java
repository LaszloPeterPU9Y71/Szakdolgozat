package com.example.Szakdolgozat.web.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class ResponseDto<T> {
    private T payload;
    private Set<String> errors;

    public ResponseDto(T payload) {
        this.payload = payload;
    }

    public ResponseDto(Set<String> errors) {
        this.errors = errors;
    }
}
