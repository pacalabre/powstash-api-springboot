package com.powstash.PowStash.services;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.entities.Mountain;
import com.powstash.PowStash.mappers.MountainMapper;
import com.powstash.PowStash.repositories.MountainRepository;
import com.powstash.PowStash.repositories.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MountainService implements MountainServiceInterface {
    private MountainRepository mountainRepository;
    private final MountainMapper mountainMapper;
    private final StateRepository stateRepository;


    public List<MountainDto> findAllMountains() {
        var mountain =  mountainRepository.findAll();
        return mountain.stream().map(mountainMapper::toDto).toList();
    }

    public MountainDto findMountain(int id) {
        var mountain =  mountainRepository.findById(id).orElse(null);
        return mountainMapper.toDto(mountain);
    }

    public MountainDto createMountain(MountainDto request) {
        var mountain = mountainMapper.toEntity(request);
        var state = stateRepository.findById(request.getState_id()).orElseThrow();
        mountain.setState(state);
        var response = mountainRepository.save(mountain);
        return mountainMapper.toDto(response);
    }

    public MountainDto updateMountain(Mountain request, int id) {
        var response = mountainRepository.findById(id).orElse(null);
        if(response == null) {
            return null;
        }
        response.setName(request.getName());
        mountainRepository.save(response);
        return mountainMapper.toDto(response);
    }

    public MountainDto deleteMountain(int id) {
        var response = mountainRepository.findById(id).orElse(null);
        if(response == null) {
            return null;
        }
        mountainRepository.delete(response);
        return mountainMapper.toDto((response));
    }
}


