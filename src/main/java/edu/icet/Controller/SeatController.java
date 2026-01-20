package edu.icet.Controller;

import edu.icet.Service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/{seatId}/hold")
    public ResponseEntity<String> holdSeat(
            @PathVariable Long seatId,
            @RequestParam Long userId) {

        seatService.holdSeat(seatId, userId);
        return ResponseEntity.ok("Seat held successfully");
    }
}
