package io.clean.tdd.infrastructure.concert.entity;

import io.clean.tdd.domain.concert.model.Concert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "concert")
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "occasion", nullable = false)
    public LocalDateTime occasion;

    @Column(name = "location", nullable = false)
    public String location;

    @Column(name = "seat_capacity", nullable = false)
    public Integer seatCapacity;

    @Column(name = "reserved_seat_count", nullable = false)
    public Integer reservedSeatCount;

    @ManyToOne
    @JoinColumn(name = "concert_detail_id", nullable = false)
    public ConcertDetailEntity concertDetailEntity;

    public static ConcertEntity from(Concert concert, ConcertDetailEntity concertDetailEntity) {
        ConcertEntity concertEntity = new ConcertEntity();
        concertEntity.id = concert.id();
        concertEntity.occasion = concert.occasion();
        concertEntity.location = concert.location();
        concertEntity.seatCapacity = concert.seatCapacity();
        concertEntity.reservedSeatCount = concert.reservedSeatCount();
        concertEntity.concertDetailEntity = concertDetailEntity;

        return concertEntity;
    }
}
