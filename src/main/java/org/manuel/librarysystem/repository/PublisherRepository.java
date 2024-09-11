package org.manuel.librarysystem.repository;

import org.manuel.librarysystem.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
