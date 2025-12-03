package com.powstash.PowStash.controllers;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.entities.Mountain;
import com.powstash.PowStash.mappers.MountainMapper;
import com.powstash.PowStash.repositories.MountainRepository;
import com.powstash.PowStash.repositories.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mountains")
@AllArgsConstructor
public class MountainController {
    private MountainRepository mountainRepository;
    private final MountainMapper mountainMapper;
    private final StateRepository stateRepository;

    @GetMapping
    public List<MountainDto> getAllMountains() {
        var response = mountainRepository.findAll();
        return response.stream().map(mountainMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mountain> getMountain(@PathVariable int id) {
        var mountain = mountainRepository.findById(id).orElse(null);
        if(mountain == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = new MountainDto(mountain.getId(), mountain.getName(), mountain.getState().getId());
        return ResponseEntity.ok(mountain);
    }
    
    @PostMapping
    public ResponseEntity<MountainDto> createMountain(@RequestBody MountainDto request, UriComponentsBuilder uriBuilder) {
       // Add lines to a service, create get mountains by state ID endpoint
        var mountain = mountainMapper.toEntity(request);
        var state = stateRepository.findById(request.getState_id()).orElseThrow();
        mountain.setState(state);
        // End add lines to a service
        var response = mountainRepository.save(mountain);
        var mountainDto = mountainMapper.toDto(response);
        var uri = uriBuilder.path("/mountains/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(mountainDto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Mountain> updateMountain(@RequestBody Mountain request,@PathVariable int id ) {
        var response = mountainRepository.findById(id).orElse(null);
        if(response == null) {
            return  ResponseEntity.notFound().build();
        }
        response.setName(request.getName());
        //response.setState_id(request.getState().getId());
        mountainRepository.save(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mountain> deleteMountain(@PathVariable int id) {
        var response = mountainRepository.findById(id).orElse(null);
        if(response != null) {
            mountainRepository.delete(response);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
