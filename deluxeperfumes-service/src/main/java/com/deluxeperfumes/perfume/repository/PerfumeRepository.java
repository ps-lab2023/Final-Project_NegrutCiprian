package com.deluxeperfumes.perfume.repository;

import com.deluxeperfumes.perfume.entity.Perfume;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    Optional<Perfume> findPerfumeByName(String name);
}
