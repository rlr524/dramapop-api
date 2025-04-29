package com.emiyaconsulting.dramapopapi.repository;

import com.emiyaconsulting.dramapopapi.model.Drama;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DramaRepository extends CrudRepository<Drama, Long> {
}
