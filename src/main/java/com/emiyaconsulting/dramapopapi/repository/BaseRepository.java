package com.emiyaconsulting.dramapopapi.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long> {
    
    @NonNull
    Optional<T> findById(@NonNull Long id);
    @NonNull
    Iterable<T> findAllById(@NonNull Iterable<Long> ids);
    
    void deleteById(@NonNull Long id);
    void delete(@NonNull T entity);
}
