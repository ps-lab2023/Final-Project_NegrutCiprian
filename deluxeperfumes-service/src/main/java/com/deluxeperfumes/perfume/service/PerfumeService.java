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
}
