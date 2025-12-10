package com.powstash.PowStash.controllers;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.dtos.StateDto;
import com.powstash.PowStash.entities.State;
import com.powstash.PowStash.mappers.StateMapper;
import com.powstash.PowStash.repositories.MountainRepository;
import com.powstash.PowStash.repositories.StateRepository;
import com.powstash.PowStash.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {
    private StateRepository stateRepository;
    private MountainRepository mountainRepository;
    private final StateMapper stateMapper;
    private final StateService stateService;

    @GetMapping
    private List<StateDto> getStates() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<StateDto> getState(@PathVariable int id) {
        var response = stateService.findState(id);
        if(response == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/mountains")
    private List<MountainDto> getMountainsByState(@PathVariable int id) {
        return stateService.getMountainsByState(id);
    }

    @PostMapping
    private ResponseEntity<StateDto> createState(@RequestBody State request, UriComponentsBuilder uriBuilder) {
        var newState = stateService.createState(request);
        var uri = uriBuilder.path("/states/{id}").buildAndExpand(newState.getId()).toUri();
        return ResponseEntity.created(uri).body(newState);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StateDto> updateState(@RequestBody State request,@PathVariable int id) {
        var response = stateService.updateState(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private  ResponseEntity<StateDto> deleteState(@PathVariable int id) {
        var response = stateService.deleteState(id);
        return  ResponseEntity.ok(response);
    }

}
