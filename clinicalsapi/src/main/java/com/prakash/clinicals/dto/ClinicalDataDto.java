package com.prakash.clinicals.dto;

import lombok.*;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalDataDto {
    private String componentName;
    private String componentValue;
    private int patientId;
}
