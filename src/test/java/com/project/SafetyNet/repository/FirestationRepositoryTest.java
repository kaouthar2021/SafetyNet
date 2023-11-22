package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Data;
import com.project.SafetyNet.model.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FirestationRepositoryTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private FirestationRepository firestationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindAllFirestation() {
        // Données de test
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("123 Main St", "1"));
        firestations.add(new Firestation("456 Oak St", "2"));

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(new Data());
        Data data = dataRepository.getData();
        data.setFirestations(firestations);

        // Appel de la méthode à tester
        List<Firestation> result = firestationRepository.findAllFirestation();

        // Vérification du résultat
        assertEquals(firestations.size(), result.size());

    }

    @Test
    void testAddFirestation() {
        // Données de test
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("789 Pine St", "3"));

        // Configuration du mock
        Data data = new Data();
        data.setFirestations(firestations);

        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        Firestation firestationToAdd = new Firestation("789 Pine St", "2");
        Firestation result = firestationRepository.addFirestation(firestationToAdd);

        // Vérification du résultat
        assertEquals(firestationToAdd, result);

        // Vérification que la méthode du mock a été appelée correctement
        verify(dataRepository, times(1)).getData();

    }
    @Test
    void testUpdateFirestation() {
        // Créez une nouvelle caserne de pompiers
        Firestation newFirestation = new Firestation("123 Main St", "3");

        // Initialisez l'objet Data et la liste firestations
        Data data = new Data();
        data.setFirestations(new ArrayList<>());
        data.getFirestations().add(newFirestation);

        // Configurez le mock pour renvoyer l'objet Data initialisé
        when(dataRepository.getData()).thenReturn(data);

        // Mettez à jour la caserne de pompiers avec une nouvelle station
        Firestation updatedFirestation = firestationRepository.updateFirestation("123 Main St", new Firestation("123 Main St", "5"));

        // Vérifiez si la station a été mise à jour correctement
        assertEquals("5", updatedFirestation.getStation());

        // Vérifiez que la méthode du mock a été appelée correctement
        verify(dataRepository, times(1)).getData();
    }

    @Test
    void testDeleteFirestation() {
        Firestation newFirestation = new Firestation("456 Oak St", "2");

        // Initialisez l'objet Data et la liste firestations
        Data data = new Data();
        data.setFirestations(new ArrayList<>());
        data.getFirestations().add(newFirestation);

        // Configurez le mock pour renvoyer l'objet Data initialisé
        when(dataRepository.getData()).thenReturn(data);

        // Supprimez la caserne de pompiers
        firestationRepository.deleteFirestation("456 Oak St");

        // Vérifiez si la caserne de pompiers a été supprimée
        assertTrue(dataRepository.getData().getFirestations().isEmpty());

        // Vérifiez que la méthode du mock a été appelée correctement
        verify(dataRepository, times(3)).getData();
    }
}

