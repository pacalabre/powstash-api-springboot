package com.powstash.PowStash.services;


import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.dtos.StateDto;
import com.powstash.PowStash.entities.State;

import java.util.List;

public interface StateServiceInterface {
    public List<StateDto> findAll();
    public StateDto findState(int id);
    public List<MountainDto> getMountainsByState(int id);
    public StateDto createState(State request);
    public StateDto updateState(State request, int id);
    public StateDto deleteState(int id);
}
