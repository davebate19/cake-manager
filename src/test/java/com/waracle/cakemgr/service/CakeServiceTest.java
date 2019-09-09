package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.waracle.cakemgr.utils.TestUtils.getTestCake;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CakeServiceTest {

    @InjectMocks
    private CakeService cakeService;

    @Mock
    private CakeRepository mockCakeRepository;

    @Test
    public void initialiseDatabase_Success() throws Exception {
        cakeService.initialiseDatabase();
        verify(mockCakeRepository, atLeastOnce()).save(any());
    }

    @Test
    public void saveCake_Success() {
        Cake cake = getTestCake();
        cake.setId(null);

        cakeService.saveCake(cake);
        verify(mockCakeRepository, times(1)).save(cake);
    }

    @Test
    public void getAllCakes_Success() {
        List<Cake> cakesExpected = Collections.singletonList(getTestCake());
        when(mockCakeRepository.findAll()).thenReturn(cakesExpected);

        List<Cake> cakes = cakeService.getAllCakes();
        assertEquals(cakesExpected, cakes);
    }

    @Test
    public void getCakeById_Success() {
        Optional<Cake> cake = Optional.of(getTestCake());
        when(mockCakeRepository.findById(1L)).thenReturn(cake);

        Optional<Cake> response = cakeService.getCakeById(1L);
        assertTrue(response.isPresent());
        assertEquals(getTestCake(), response.get());
    }

    @Test
    public void getCakeById_NotFound() {
        Optional<Cake> cake = Optional.empty();
        when(mockCakeRepository.findById(1L)).thenReturn(cake);

        Optional<Cake> response = cakeService.getCakeById(1L);
        assertFalse(response.isPresent());
    }

    @Test
    public void getCakeByTitle_Success() {
        Optional<Cake> cake = Optional.of(getTestCake());
        when(mockCakeRepository.findByTitle("testCake")).thenReturn(cake);

        Optional<Cake> response = cakeService.getCakeByTitle("testCake");
        assertTrue(response.isPresent());
        assertEquals(getTestCake(), response.get());
    }

    @Test
    public void getCakeByTitle_NotFound() {
        Optional<Cake> cake = Optional.empty();
        when(mockCakeRepository.findByTitle("testCake")).thenReturn(cake);

        Optional<Cake> response = cakeService.getCakeByTitle("testCake");
        assertFalse(response.isPresent());
    }
}
