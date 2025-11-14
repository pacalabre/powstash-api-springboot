package com.powstash.PowStash.mappers;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.entities.Mountain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MountainMapper {
    MountainDto toDto(Mountain mountain);
    Mountain toEntity(MountainDto mountainDto);
}
