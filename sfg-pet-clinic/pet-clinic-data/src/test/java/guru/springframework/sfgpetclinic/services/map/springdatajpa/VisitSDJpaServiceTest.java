package guru.springframework.sfgpetclinic.services.map.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.VisitSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    Visit visit;

    Pet pet;

    Owner owner;

    @BeforeEach
    void setUp(){
        visit = new Visit();
        visit.setId(1L);
        pet = new Pet();
        owner = new Owner();

        pet.setId(1L);
        owner.setId(1L);

        pet.setOwner(owner);
        visit.setPet(pet);
    }

    @Test
    void findAll() {

        Set<Visit> visits = visitSDJpaService.findAll();
        visits.add(new Visit());
        visits.add(new Visit());


        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> savedVisits = visitSDJpaService.findAll();

        assertEquals(2, visits.size());
        assertNotNull(savedVisits);
    }

    @Test
    void findById() {


            when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

            Visit visit = visitSDJpaService.findById(1L);

            assertEquals(1L, visit.getId());
    }

    @Test
    void save() {

        Visit visit1 = new Visit();
        visit1.setId(2L);

        when(visitRepository.save(any())).thenReturn(visit);

        Visit savedVisit = visitSDJpaService.save(visit);

        assertNotNull(savedVisit);

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {

        visitSDJpaService.delete(visit);

        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {

        visitSDJpaService.deleteById(anyLong());

        verify(visitRepository).deleteById(anyLong());
    }
}