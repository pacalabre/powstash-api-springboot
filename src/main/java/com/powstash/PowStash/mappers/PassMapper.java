package com.powstash.PowStash.mappers;

import com.powstash.PowStash.dtos.PassDto;
import com.powstash.PowStash.entities.Pass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassMapper {
    PassDto toDto(Pass pass);
    Pass    toEntity(PassDto passDto);
}
