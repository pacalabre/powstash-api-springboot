package com.powstash.PowStash.mappers;

import com.powstash.PowStash.dtos.StateDto;
import com.powstash.PowStash.entities.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateDto toDto(State state);
    State    toEntity(StateDto stateDto);

}

