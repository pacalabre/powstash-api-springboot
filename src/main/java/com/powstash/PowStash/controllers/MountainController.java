package com.powstash.PowStash.controllers;

import com.powstash.PowStash.dtos.MountainDto;
import com.powstash.PowStash.entities.Mountain;
import com.powstash.PowStash.services.MountainService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mountains")
@AllArgsConstructor
public class MountainController {
    private final MountainService mountainService;

    @GetMapping
    public List<MountainDto> getAllMountains() {
        return mountainService.findAllMountains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MountainDto> getMountain(@PathVariable int id) {
        var mountain = mountainService.findMountain(id);
        if(mountain == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mountain);
    }
    
    @PostMapping
    public ResponseEntity<MountainDto> createMountain(@RequestBody MountainDto request, UriComponentsBuilder uriBuilder) {
        var mountain = mountainService.createMountain(request);
        var uri = uriBuilder.path("/mountains/{id}").buildAndExpand(mountain.getId()).toUri();
        return ResponseEntity.created(uri).body(mountain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MountainDto> updateMountain(@RequestBody Mountain request,@PathVariable int id ) {
        var response = mountainService.updateMountain(request, id);
        if(response == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMountain(@PathVariable int id) {
        var response = mountainService.deleteMountain(id);
        if(response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
