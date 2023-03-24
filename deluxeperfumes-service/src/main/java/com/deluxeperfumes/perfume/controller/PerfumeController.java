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
}

