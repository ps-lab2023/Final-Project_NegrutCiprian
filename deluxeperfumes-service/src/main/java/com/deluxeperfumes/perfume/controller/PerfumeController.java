package com.deluxeperfumes.perfume.controller;

import com.deluxeperfumes.perfume.dto.PerfumeDto;
import com.deluxeperfumes.perfume.endpoint.PerfumeEndpoint;

import java.util.UUID;

import com.deluxeperfumes.perfume.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeEndpoint {

    private final PerfumeService perfumeService;

    @Override
    public PerfumeDto createPerfume(PerfumeDto perfumeDto) {
        return perfumeService.createPerfume(perfumeDto);
    }

    @Override
    public void updatePerfume(PerfumeDto perfumeDto, UUID perfumeExternalId) {
        perfumeService.updatePerfume(perfumeDto, perfumeExternalId);
    }

    @Override
    public void deletePerfume(UUID perfumeExternalId) {
        perfumeService.deletePerfume(perfumeExternalId);
    }

    @Override
    public Page<PerfumeDto> getPerfumes(String searchString, Pageable pageable) {
        return perfumeService.findAll(searchString, pageable);
    }

    @Override
    public PerfumeDto getPerfumeByExternalId(UUID perfumeExternalId) {
        return perfumeService.getPerfumeByExternalID(perfumeExternalId);
    }
}

