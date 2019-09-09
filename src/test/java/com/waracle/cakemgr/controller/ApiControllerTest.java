package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.service.CakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static com.waracle.cakemgr.utils.TestUtils.getTestCake;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

    private static final String JSON_CAKE = "{\"id\":1,\"title\":\"testCake\",\"desc\":\"description\",\"image\":\"imageUrl\"}";

    @InjectMocks
    private ApiController apiController;

    @Mock
    private CakeService mockCakeService;

    @Test
    public void getJsonCakeList_Success() throws Exception {
        when(mockCakeService.getAllCakes()).thenReturn(Collections.singletonList(getTestCake()));
        String cakes = apiController.getJsonCakeList();
        assertEquals("[" + JSON_CAKE + "]", cakes);
    }

    @Test
    public void getJsonCakeList_NoCakes() throws Exception {
        when(mockCakeService.getAllCakes()).thenReturn(null);
        String cakes = apiController.getJsonCakeList();
        assertEquals("No Cakes have been found", cakes);
    }

    @Test
    public void getCakeById_Success() throws Exception {
        when(mockCakeService.getCakeById(1L)).thenReturn(Optional.of(getTestCake()));
        String cake = apiController.getCakeById(1L);
        assertEquals(JSON_CAKE, cake);
    }

    @Test
    public void getCakeById_NotFound() throws Exception {
        when(mockCakeService.getCakeById(1L)).thenReturn(Optional.empty());
        String cake = apiController.getCakeById(1L);
        assertEquals("Cake not found", cake);
    }

    @Test
    public void getCakeByTitle_Success() throws Exception {
        when(mockCakeService.getCakeByTitle("testCake")).thenReturn(Optional.of(getTestCake()));
        String cake = apiController.getCakeByTitle("testCake");
        assertEquals(JSON_CAKE, cake);
    }

    @Test
    public void getCakeByTitle_NotFound() throws Exception {
        when(mockCakeService.getCakeByTitle("testCake")).thenReturn(Optional.empty());
        String cake = apiController.getCakeByTitle("testCake");
        assertEquals("Cake not found", cake);
    }

    @Test
    public void addNewCake_Success() {
        apiController.addNewCake(getTestCake());
        verify(mockCakeService, times(1)).saveCake(getTestCake());
    }

}
