package com.powstash.PowStash.controllers;

import com.powstash.PowStash.dtos.StateDto;
import com.powstash.PowStash.entities.State;
import com.powstash.PowStash.mappers.StateMapper;
import com.powstash.PowStash.repositories.StateRepository;
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
    private final StateMapper stateMapper;

    @GetMapping
    private List<StateDto> getStates() {
        var response = stateRepository.findAll();
        System.out.println(response);
        return response.stream().map(stateMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    private ResponseEntity<StateDto> getState(@PathVariable int id) {
        var response = stateRepository.findById(id).orElse(null);
        if(response == null) {
            ResponseEntity.notFound().build();
        }
        var responseDto = stateMapper.toDto(response);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    private ResponseEntity<StateDto> createState(@RequestBody State request, UriComponentsBuilder uriBuilder) {
        var newState = stateRepository.save(request);
        var stateDTO = stateMapper.toDto(newState);
        // make URI along with Pass Post
       // return ResponseEntity.ok(stateDTO);
        var uri = uriBuilder.path("/states/{id}").buildAndExpand(newState.getId()).toUri();
        return ResponseEntity.created(uri).body(stateDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StateDto> updateState(@RequestBody State request,@PathVariable int id) {
        var response = stateRepository.findById(id).orElse(null);
        if(response == null) {
            return ResponseEntity.notFound().build();
        }
        response.setName(request.getName());
        stateRepository.save(response);
        return ResponseEntity.ok(stateMapper.toDto(response));
    }

    @DeleteMapping("/{id}")
    private  ResponseEntity<StateDto> deleteState(@PathVariable int id) {
        var response = stateRepository.findById(id).orElse(null);
        if(response == null) {
            return ResponseEntity.notFound().build();
        }
        stateRepository.delete(response);
        return  ResponseEntity.ok(stateMapper.toDto(response));
    }


}
