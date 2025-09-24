package com.pla.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ITEM_ID_INVALID(1001, "Boss id must be at least {min} characters", HttpStatus.BAD_REQUEST),
    ITEM_NAME_INVALID(1002, "Boss name must be at least {min} characters", HttpStatus.BAD_REQUEST),
    ITEM_ID_EXISTED(1003, "Boss id existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1004, "Category not existed", HttpStatus.NOT_FOUND),
    CATEGORY_ID_REQUIRED(1005, "Category id is required", HttpStatus.BAD_REQUEST),
    NO_DATA(1006, "No data", HttpStatus.NOT_FOUND),
    INVALID_VALIDATION_KEY(1007, "Key validation error", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_INVALID(1008, "Category name must be at least {min} characters", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_EXISTED(1009, "Category name existed", HttpStatus.BAD_REQUEST),
    IO_EXCEPTION(1010, "Io exeption with the image", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
