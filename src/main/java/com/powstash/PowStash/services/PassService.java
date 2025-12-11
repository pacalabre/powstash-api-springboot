package com.powstash.PowStash.services;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.dtos.PassDto;
import com.powstash.PowStash.entities.Pass;
import com.powstash.PowStash.mappers.PassMapper;
import com.powstash.PowStash.repositories.MountainRepository;
import com.powstash.PowStash.repositories.PassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PassService implements PassServiceInterface {
    private final PassRepository passRepository;
    private final PassMapper passMapper;
    private final MountainRepository mountainRepository;

    public List<PassDto> getAllPasses () {
        var passes = passRepository.findAll();
        var passDtos = passes.stream().map(passMapper::toDto);
        return passDtos.toList();
    }

    public PassDto getPass(int id) {
        var pass = passRepository.findById(id).orElse(null);
        if(pass == null) {
            return null;
        }
        return passMapper.toDto(pass);
    }

    public List<MountainDto> getMountainsByPass(int id) {
        var mountains = mountainRepository.findByPassId(id);
        return mountains.stream().map(mountain-> new MountainDto(mountain.getId(),mountain.getName(),mountain.getState().getId(),mountain.getPass().getId())).toList();
    }

    public PassDto createPass(Pass request) {
        var newPass = passRepository.save(request);
        return passMapper.toDto(newPass);
    }

    public PassDto updatePass(Pass request, int id) {
        var passToUpdate = passRepository.findById(id).orElse(null);
        if(passToUpdate == null) {
            return null;
        }
        passToUpdate.setName(request.getName());
        passRepository.save(passToUpdate);
        return  passMapper.toDto(passToUpdate);
    }

    public PassDto deletePass(int id) {
        var passToDelete = passRepository.findById(id).orElse(null);
        if(passToDelete == null) {
            return null;
        }
        passRepository.delete(passToDelete);
        return passMapper.toDto(passToDelete);
    }
}


