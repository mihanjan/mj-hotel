package com.mj.visit.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false)
    @NotNull
    private String guestId;

    @Column(updatable = false)
    @NotNull
    private Short roomNumber;

    @Column(updatable = false)
    @NotNull
    private LocalDateTime arrival;

    @Column(updatable = false)
    @NotNull
    private LocalDateTime departure;

    @Column(updatable = false)
    @NotNull
    private double cost;

    @NotNull
    private boolean active;
}
