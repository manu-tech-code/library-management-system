package org.manuel.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static org.manuel.librarymanagementsystem.service.util.FileUploadPaths.FULL_PROFILE_IMAGE_PATH;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Author name is required")
    private String name;

    @Column(nullable = false)
    private String description;

    private String profileImage;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    @Transient
    public String getImagePath(){
        if (profileImage == null) return null;
        return FULL_PROFILE_IMAGE_PATH.getPath() + profileImage;
    }
}

