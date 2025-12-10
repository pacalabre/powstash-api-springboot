package com.powstash.PowStash.services;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.entities.Mountain;
import java.util.List;

public interface MountainServiceInterface {
    public List<MountainDto> findAllMountains();
    public MountainDto findMountain(int id);
    public MountainDto createMountain(MountainDto request);
    public MountainDto updateMountain(Mountain request, int id);
    public MountainDto deleteMountain(int id);
}
