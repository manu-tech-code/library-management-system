package org.manuel.librarymanagementsystem.service.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileUploadPaths {
    UPLOAD_DIR("./uploads"),
    BOOK_COVERS("/books"),
    PROFILE_IMAGES("/authors"),
    FULL_PROFILE_IMAGE_PATH("/uploads/authors/"),
    FULL_BOOK_COVERS_PATH("/uploads/books/")
        ;

    private final String path;

}
