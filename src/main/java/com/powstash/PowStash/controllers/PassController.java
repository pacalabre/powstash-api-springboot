package com.powstash.PowStash.controllers;
import com.powstash.PowStash.dtos.PassDto;
import com.powstash.PowStash.entities.Pass;
import com.powstash.PowStash.mappers.PassMapper;
import com.powstash.PowStash.repositories.PassRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/passes")
public class PassController {
    private PassRepository passRepository;
    private final PassMapper passMapper;

    @GetMapping
    private List<PassDto> getAllPasses() {
        var passes = passRepository.findAll();
        var passDtos = passes.stream().map(passMapper::toDto);
        return passDtos.toList();
    }

    @GetMapping("/{id}")
    private ResponseEntity<PassDto> getPass(@PathVariable int id) {
        var pass = passRepository.findById(id).orElse(null);
        if(pass == null) {
            return ResponseEntity.notFound().build();
        }
        var passDto = passMapper.toDto(pass);
        return ResponseEntity.ok(passDto);
    }

    @PostMapping
    private ResponseEntity<PassDto> createPass(@RequestBody Pass request) {
        var newPass = passRepository.save(request);
        var passDto = passMapper.toDto(newPass);
        return ResponseEntity.ok(passDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Pass> updatePass(@PathVariable int id, @RequestBody Pass request) {
        var passToUpdate = passRepository.findById(id).orElse(null);
        if(passToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        passToUpdate.setName(request.getName());
        passRepository.save(passToUpdate);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Pass> deletePass(@PathVariable int id) {
        var passToDelete = passRepository.findById(id).orElse(null);
        if(passToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        passRepository.delete(passToDelete);
        return ResponseEntity.ok().build();
    }
}
