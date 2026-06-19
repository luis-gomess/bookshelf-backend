package com.bookshelf.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldValidationError> fields
) {
}
