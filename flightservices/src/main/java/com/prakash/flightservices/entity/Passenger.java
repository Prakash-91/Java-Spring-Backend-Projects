package com.prakash.flightservices.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}
