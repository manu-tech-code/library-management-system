package org.manuel.librarymanagementsystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    NOT_FOUND("Record not found");

    private final String message;
}
