package com.powstash.PowStash.repositories;

import com.powstash.PowStash.entities.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MountainRepository extends JpaRepository<Mountain, Integer> {
    Set<Mountain> findByStateId(int state_id);
    Set<Mountain> findByPassId(int pass_id);
}
