package com.example.checkin.repository;

import java.util.List;

import com.example.checkin.classes.CheckIn;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInDetailsRepo extends MongoRepository<CheckIn, Long> {
    public List<CheckIn> findByTicketId(String ticketId);
}
