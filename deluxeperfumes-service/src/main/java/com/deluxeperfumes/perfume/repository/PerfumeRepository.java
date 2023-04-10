package com.deluxeperfumes.perfume.repository;

import com.deluxeperfumes.perfume.entity.Perfume;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    Optional<Perfume> findPerfumeByExternalId(UUID collaboratorExternalId);

    Optional<Perfume> findPerfumeByName(String name);
    void deletePerfumeByExternalId(UUID externalId);

    Page<Perfume> findAllByIdentifierContainingIgnoreCaseOrNameContainingIgnoreCase(
            String searchString, String searchString1, Pageable pageable);

    Optional<Perfume> findPerfumeByIdentifier(String identifier);
}
