package edu.icet.model.Dto;

import java.time.LocalDateTime;

public class Seat {

    private Long id;
    private String status;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
}
