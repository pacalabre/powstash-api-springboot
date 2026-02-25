package com.powstash.PowStash.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MountainDto {
    private int id;
    private String name;
    private String description;
    private String address;
    private String longitude;
    private String latitude ;
    private String localKnowledge;
    private int state_id;
    private int pass_id;
}
