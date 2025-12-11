package com.powstash.PowStash.services;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.dtos.PassDto;
import com.powstash.PowStash.entities.Pass;

import java.util.List;

public interface PassServiceInterface {
    public List<PassDto> getAllPasses ();
    public PassDto getPass(int id);
    public PassDto createPass(Pass request);
    public PassDto updatePass(Pass request, int id);
    public PassDto deletePass(int id);
    public List<MountainDto> getMountainsByPass(int id);
}
