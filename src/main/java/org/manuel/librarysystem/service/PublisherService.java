package org.manuel.librarysystem.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarysystem.config.LibraryException;
import org.manuel.librarysystem.entity.Publisher;
import org.manuel.librarysystem.repository.PublisherRepository;
import org.manuel.librarysystem.service.util.LibraryInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherService implements LibraryInterface<Publisher> {
    private final PublisherRepository publisherRepository;

    /**
     * Gets all authors.
     *
     * @return the all authors
     */
    public Iterable<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    /**
     * Gets author by id.
     *
     * @param id the id
     * @return the author by id
     */
    public Publisher getPublisherById(Long id) {
        return getPublisher(id);
    }

    /**
     * Save author.
     *
     * @param publisher the author
     */
    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }
    /**
     * Update author.
     *
     * @param publisher the author
     */
    @Override
    public void update(Long id,@NonNull Publisher publisher) {
        Publisher oldPublisher = getPublisher(id);
        oldPublisher.setName(publisher.getName());
        oldPublisher.setBooks(publisher.getBooks());

        publisherRepository.save(oldPublisher);
    }

    /**
     * Delete author.
     *
     * @param id the publisher id
     */
    @Override
    public void delete(Long id) {
        publisherRepository.delete(getPublisher(id));
    }

    private Publisher getPublisher(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new LibraryException("Publisher not found"));
    }
}
