package com.example.bookingdetailsservice.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import com.example.bookingdetailsservice.model.DbSequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public long getSequenceNumber(String sequenceNumber) {
        Query query = new Query(Criteria.where("id").is(sequenceNumber));

        Update update = new Update().inc("seq", 1);

        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
                DbSequence.class);

        return (long) (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }

    public Object generateSequence(String sequenceName) {
        return null;
    }
}