package com.deluxeperfumes.perfume;

import com.deluxeperfumes.perfume.dto.PerfumeDto;
import com.deluxeperfumes.perfume.entity.Perfume;
import com.deluxeperfumes.perfume.mapper.PerfumeMapper;
import com.deluxeperfumes.perfume.repository.PerfumeRepository;
import com.deluxeperfumes.perfume.service.PerfumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PerfumeServiceUnitTest {

    private final PerfumeMapper perfumeMapper = Mappers.getMapper(PerfumeMapper.class);

    @Mock
    private PerfumeRepository perfumeRepository;

    private PerfumeService perfumeService;

    @BeforeEach
    public void setup() {
        perfumeService = new PerfumeService(
                perfumeRepository, perfumeMapper);
    }

    @Test
    void when_create_perfume_return_perfume() {

        PerfumeDto perfumeDto = new PerfumeDto();
        perfumeDto.setName("Dior");
        perfumeDto.setExternalId(UUID.randomUUID());

        Perfume perfume = new Perfume();
        perfume.setName("Dior");
        perfume.setExternalId(perfumeDto.getExternalId());

        lenient().when(perfumeRepository.findPerfumeByName(any(String.class)))
                .thenReturn(Optional.of(perfume));
        lenient().when(perfumeRepository.save(any(Perfume.class)))
                .thenReturn(perfume);

        PerfumeDto result = perfumeService.createPerfume(perfumeDto);

        assertEquals(perfumeDto.getExternalId(), result.getExternalId());
    }


}
