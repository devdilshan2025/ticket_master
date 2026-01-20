package edu.icet.model.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seat {

    private Long id;
    private String status;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
}
