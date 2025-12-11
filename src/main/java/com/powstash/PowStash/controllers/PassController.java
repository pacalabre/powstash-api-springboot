package com.powstash.PowStash.controllers;
import com.powstash.PowStash.dtos.PassDto;
import com.powstash.PowStash.entities.Pass;
import com.powstash.PowStash.mappers.PassMapper;
import com.powstash.PowStash.repositories.PassRepository;
import com.powstash.PowStash.services.PassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/passes")
public class PassController {
    private PassRepository passRepository;
    private final PassMapper passMapper;
    private final PassService passService;

    @GetMapping
    private List<PassDto> getAllPasses() {
        return passService.getAllPasses();
    }

    @GetMapping("/{id}")
    private ResponseEntity<PassDto> getPass(@PathVariable int id) {
        var pass = passService.getPass(id);
        if(pass == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pass);
    }

    @PostMapping
    private ResponseEntity<PassDto> createPass(@RequestBody Pass request, UriComponentsBuilder uriBuilder) {
        var newPass = passService.createPass(request);
        var uri = uriBuilder.path("/pass/{id}").buildAndExpand(newPass.getId()).toUri();
        return ResponseEntity.created(uri).body(newPass);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PassDto> updatePass(@PathVariable int id, @RequestBody Pass request) {
        var passToUpdate = passService.updatePass(request, id);
        if(passToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(passToUpdate);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<PassDto> deletePass(@PathVariable int id) {
        var passToDelete = passService.deletePass(id);
        if(passToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passToDelete);
    }
}
