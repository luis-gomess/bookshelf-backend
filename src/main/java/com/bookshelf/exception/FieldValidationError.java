package com.bookshelf.exception;

public record FieldValidationError(
        String field,
        String message
) {
}
