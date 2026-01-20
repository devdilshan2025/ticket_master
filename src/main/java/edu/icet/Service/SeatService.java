package edu.icet.Service;

import edu.icet.Repository.SeatRepository;
import edu.icet.exception.SeatLockedException;
import edu.icet.model.Dto.Seat;
import edu.icet.model.Entity.SeatStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Transactional
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat holdSeat(Long seatId, Long userId) {

        Seat seat = seatRepository.findSeatForUpdate(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();

        // Case 1: AVAILABLE
        if (seat.getStatus().equals(SeatStatus.AVAILABLE.name())) {
            return lockSeat(seat, userId, now);
        }

        if (seat.getStatus().equals(SeatStatus.HELD.name())
                && seat.getHoldExpiry().isBefore(now)) {

            return lockSeat(seat, userId, now);
        }

        // Case 3: HELD and still active
        if (seat.getStatus().equals(SeatStatus.HELD.name())) {
            long remainingSeconds =
                    Duration.between(now, seat.getHoldExpiry()).getSeconds();
            throw new SeatLockedException(remainingSeconds);
        }

        throw new RuntimeException("Seat already sold");
    }

    private Seat lockSeat(Seat seat, Long userId, LocalDateTime now) {
        seat.setStatus(String.valueOf(SeatStatus.HELD));
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(10));
        return seatRepository.save(seat);
    }
}
