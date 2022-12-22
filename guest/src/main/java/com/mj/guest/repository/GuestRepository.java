package com.mj.guest.repository;

import com.mj.guest.model.Guest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends MongoRepository<Guest, ObjectId> {

    Optional<Guest> findByPassport(String passport);
}
