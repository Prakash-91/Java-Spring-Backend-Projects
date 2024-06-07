package com.prakash.flightservices.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private int id;
    private Boolean checkedIn;
    private Integer numberOfBags;
}
