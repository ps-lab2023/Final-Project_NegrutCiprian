package com.deluxeperfumes.perfume.service;

import com.deluxeperfumes.perfume.dto.PerfumeDto;
import com.deluxeperfumes.perfume.exceptions.PerfumeNotFoundException;
import com.deluxeperfumes.perfume.mapper.PerfumeMapper;
import com.deluxeperfumes.perfume.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final PerfumeMapper perfumeMapper;

    public PerfumeDto createPerfume(PerfumeDto perfumeDto) {
        var perfume = perfumeMapper.toEntity(perfumeDto);
        perfume.setCreatedDate(LocalDate.now());
        if(perfumeDto.getIdentifier() == null || perfumeDto.getIdentifier().isEmpty())
            perfume.setIdentifier(perfume.getName().substring(0, 3) + LocalTime.now().getSecond());
        return perfumeMapper.toDto(perfumeRepository.save(perfume));
    }

    public void updatePerfume(PerfumeDto perfumeUpdated, UUID perfumeExternalId) {
        var perfumeToUpdate = perfumeRepository.findPerfumeByExternalId(perfumeExternalId)
                .orElseThrow(() -> new PerfumeNotFoundException(
                        "Could not find perfume with externalId " + perfumeExternalId));
        perfumeToUpdate.setIdentifier(perfumeUpdated.getIdentifier());
        perfumeToUpdate.setName(perfumeUpdated.getName());
        perfumeToUpdate.setUpdatedDate(LocalDate.now());
        perfumeToUpdate.setCategory(perfumeUpdated.getCategory());
        perfumeToUpdate.setDescription(perfumeToUpdate.getDescription());
        perfumeRepository.save(perfumeToUpdate);
    }

    public void deletePerfume(UUID perfumeExternalId) {
        perfumeRepository.deletePerfumeByExternalId(perfumeExternalId);

    }

    public Page<PerfumeDto> findAll(String searchTerm, Pageable pageable) {
        if (searchTerm.isEmpty()) {
            return perfumeRepository.findAll(pageable).map(perfumeMapper::toDto);
        }
        return perfumeRepository.findAllByIdentifierContainingIgnoreCaseOrNameContainingIgnoreCase(
                searchTerm, searchTerm, pageable).map(perfumeMapper::toDto);
    }

    public PerfumeDto getPerfumeByExternalID(UUID perfumeExternalID) {
        return perfumeMapper.toDto(perfumeRepository.findPerfumeByExternalId(perfumeExternalID)
                .orElseThrow(() -> new PerfumeNotFoundException(
                        "Could not find perfume with externalId: " + perfumeExternalID)));
    }

}
