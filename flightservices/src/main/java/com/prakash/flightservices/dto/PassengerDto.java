package com.prakash.flightservices.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerDto {
    private int flightId;
    private String passengerFirstName;
    private String passengerMiddleName;
    private String passengerLastName;
    private String passengerPhone;
    private String passengerEmail;
    private String cardNumber;
    private String expirationDate;
    private String securityCode;
}
