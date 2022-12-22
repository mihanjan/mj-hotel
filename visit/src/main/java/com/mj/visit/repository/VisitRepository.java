package com.mj.visit.repository;

import com.mj.visit.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    Optional<Visit> findByActiveIsTrueAndRoomNumber(Short roomNumber);

    List<Visit> findAllByActive(boolean active);
}
