package com.powstash.PowStash.services;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.dtos.StateDto;
import com.powstash.PowStash.entities.State;
import com.powstash.PowStash.mappers.StateMapper;
import com.powstash.PowStash.repositories.MountainRepository;
import com.powstash.PowStash.repositories.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class StateService implements StateServiceInterface {
    private StateRepository stateRepository;
    private MountainRepository mountainRepository;
    private StateMapper stateMapper;

    public List<StateDto> findAll() {
        var response = stateRepository.findAll();
        return response.stream().map(stateMapper::toDto).toList();
    }

    public StateDto findState(int id) {
        var response = stateRepository.findById(id).orElse(null);
        return stateMapper.toDto(response);
    }

    public List<MountainDto> getMountainsByState(int id) {
        var list = mountainRepository.findByStateId(id);
        return list.stream().map(mountain -> new MountainDto(mountain.getId(), mountain.getName(), mountain.getState().getId())).toList();
    }

    public StateDto createState(State request) {
        var newState = stateRepository.save(request);
        return stateMapper.toDto(newState);
    }

    public StateDto updateState(State request, int id) {
        var response = stateRepository.findById(id).orElse(null);
        if(response == null) {
            return null;
        }
        response.setName(request.getName());
        stateRepository.save(response);
        return stateMapper.toDto(response);
    }

    public StateDto deleteState(int id) {
        var response = stateRepository.findById(id).orElse(null);
        if(response == null) {
            return null;
        }
        stateRepository.delete(response);
        return stateMapper.toDto(response);
    }
}
