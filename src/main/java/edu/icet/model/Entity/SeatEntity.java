package edu.icet.model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldByUserId;

    private LocalDateTime holdExpiry;

    @Version
    private Long version;
}
