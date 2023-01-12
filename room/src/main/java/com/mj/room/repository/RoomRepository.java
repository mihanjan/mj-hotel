package com.mj.room.repository;

import com.mj.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

//    List<Room> findRoomsByOccupiedIsFalseAndMaxPersonsGreaterThanEqualAndRoomTypeInOrderByNumber(
//            Byte maxPersons, List<RoomType> roomTypes);

    Optional<Room> findByNumber(Short number);

    List<Room> findAllByOrderByNumber();
}
