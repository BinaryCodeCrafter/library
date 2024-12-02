package com.hamada.library.controllers;



import com.hamada.library.domain.PatronEntity;
import com.hamada.library.services.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    public PatronControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatron() {
        PatronEntity patron = new PatronEntity();
        patron.setName("John Doe");
        patron.setEmail("john.doe@gmail.com");
        patron.setContactInformation("123-456-7890");

        when(patronService.create(any(PatronEntity.class))).thenReturn(patron);

        ResponseEntity<PatronEntity> response = patronController.create(patron);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("john.doe@gmail.com", response.getBody().getEmail());
        assertEquals("123-456-7890", response.getBody().getContactInformation());
        verify(patronService, times(1)).create(patron);
    }

    @Test
    void testFindPatronById_Found() {
        PatronEntity patron = new PatronEntity();
        patron.setName("John Doe");
        patron.setEmail("john.doe@gmail.com");
        patron.setContactInformation("123-456-7890");

        when(patronService.find(1L)).thenReturn(Optional.of(patron));

        ResponseEntity<PatronEntity> response = patronController.find(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("john.doe@gmail.com", response.getBody().getEmail());
        assertEquals("123-456-7890", response.getBody().getContactInformation());
        verify(patronService, times(1)).find(1L);
    }

    @Test
    void testFindPatronById_NotFound() {
        when(patronService.find(1L)).thenReturn(Optional.empty());

        ResponseEntity<PatronEntity> response = patronController.find(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patronService, times(1)).find(1L);
    }

    @Test
    void testListPatrons() {
        PatronEntity patron1 = new PatronEntity();
        patron1.setName("John Doe");
        patron1.setEmail("john.doe@gmail.com");
        patron1.setContactInformation("123-456-7890");

        PatronEntity patron2 = new PatronEntity();
        patron2.setName("Jane Doe");
        patron2.setEmail("jane.doe@gmail.com");
        patron2.setContactInformation("987-654-3210");

        List<PatronEntity> patrons = Arrays.asList(patron1, patron2);
        when(patronService.listPatrons()).thenReturn(patrons);

        ResponseEntity<List<PatronEntity>> response = patronController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(patronService, times(1)).listPatrons();
    }

    @Test
    void testUpdatePatron_Found() {
        PatronEntity patron = new PatronEntity();
        patron.setName("Updated Name");
        when(patronService.isPatronExists(1L)).thenReturn(true);
        when(patronService.create(any(PatronEntity.class))).thenReturn(patron);

        ResponseEntity<PatronEntity> response = patronController.update(patron, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Name", response.getBody().getName());
        verify(patronService, times(1)).isPatronExists(1L);
        verify(patronService, times(1)).create(patron);
    }

    @Test
    void testUpdatePatron_NotFound() {
        PatronEntity patron = new PatronEntity();
        when(patronService.isPatronExists(1L)).thenReturn(false);

        ResponseEntity<PatronEntity> response = patronController.update(patron, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patronService, times(1)).isPatronExists(1L);
        verify(patronService, never()).create(any(PatronEntity.class));
    }

    @Test
    void testDeletePatron_Found() {
        when(patronService.isPatronExists(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = patronController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(patronService, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePatron_NotFound() {
        when(patronService.isPatronExists(1L)).thenReturn(false);

        ResponseEntity<Boolean> response = patronController.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(false, response.getBody());
        verify(patronService, never()).deleteById(1L);
    }
}
