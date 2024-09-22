package org.manuel.librarymanagementsystem.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.manuel.librarymanagementsystem.entity.Author;
import org.manuel.librarymanagementsystem.repository.AuthorRepository;
import org.manuel.librarymanagementsystem.service.util.LibraryInterface;
import org.springframework.stereotype.Service;

import static org.manuel.librarymanagementsystem.enums.ErrorMessages.NOT_FOUND;

/**
 * The type Author service.
 */
@Service
@RequiredArgsConstructor
public class AuthorService implements LibraryInterface<Author> {
    private final AuthorRepository authorRepository;

    /**
     * Gets all authors.
     *
     * @return the all authors
     */
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Gets author by id.
     *
     * @param id the id
     * @return the author by id
     */
    public Author getAuthorById(Long id) {
        return getAuthor(id);
    }

    /**
     * Save author.
     *
     * @param author the author
     */
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    /**
     * Update author.
     *
     * @param author the author
     */
    @Override
    public void update(Long id,@NonNull Author author) {
        Author oldAuthor = getAuthor(id);
        oldAuthor.setName(author.getName());
        oldAuthor.setDescription(author.getDescription());
        oldAuthor.setBooks(author.getBooks());
        oldAuthor.setProfileImage(author.getProfileImage());

        authorRepository.save(oldAuthor);
    }


    /**
     * Delete author.
     *
     * @param authorId the author id
     */
    @Override
    public void delete(Long authorId) {
        authorRepository.delete(getAuthor(authorId));
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new LibraryException(NOT_FOUND.getMessage()));
    }

}
