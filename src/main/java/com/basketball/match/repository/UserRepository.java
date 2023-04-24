package com.basketball.match.repository;

import com.basketball.match.entity.Fixtures;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Fixtures, Long> {
}