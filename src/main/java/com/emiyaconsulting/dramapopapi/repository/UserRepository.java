package com.emiyaconsulting.dramapopapi.repository;

import com.emiyaconsulting.dramapopapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
