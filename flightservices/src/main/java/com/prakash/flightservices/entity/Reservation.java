package com.prakash.flightservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Reservation extends AbstractEntity {

    private Boolean checkedIn;
    private Integer numberOfBags;
    @OneToOne
    private Flight flight;
    @OneToOne
    private Passenger passenger;

}
