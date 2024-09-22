package org.manuel.librarymanagementsystem.service.util;

public interface LibraryInterface<T> {
    void save(T obj);
    void update(Long id,T obj);
    void delete(Long id);
}
