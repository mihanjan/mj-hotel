package com.mj.room.repository;

import com.mj.room.model.Room;
import com.mj.room.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findRoomsByOccupiedIsFalseAndMaxPersonsGreaterThanEqualAndRoomTypeInOrderByNumber(
            Byte maxPersons, List<RoomType> roomTypes);

    Optional<Room> findByNumber(Short number);

    @Transactional
    @Modifying
    @Query("update Room r set r.occupied = :occupied where r.number = :room_number")
    int updateOccupied(@Param("room_number") Short roomNumber, @Param("occupied") Boolean occupied);
}
