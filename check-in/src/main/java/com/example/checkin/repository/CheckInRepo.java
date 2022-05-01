package com.example.checkin.repository;

import com.example.checkin.model.CheckIn;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepo extends MongoRepository<CheckIn, Long> {

}
