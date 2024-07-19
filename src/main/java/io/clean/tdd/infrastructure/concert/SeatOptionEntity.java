package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.SeatClass;
import io.clean.tdd.domain.concert.model.SeatOption;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SeatOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    SeatClass seatClass;

    @Column(name = "price", nullable = false)
    Long price;

    public SeatOption toModel() {
        return SeatOption.builder()
            .id(id)
            .seatClass(seatClass)
            .price(price)
            .build();
    }
}
