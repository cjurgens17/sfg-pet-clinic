package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    VisitMapService visitMapService;

    @BeforeEach
    void setUp(){
    visitMapService = new VisitMapService();

        Visit visit = new Visit();
        visit.setId(1L);
        Pet pet = new Pet();
        pet.setId(1L);
        Owner owner = new Owner();
        owner.setId(1L);
        pet.setOwner(owner);
        visit.setPet(pet);
        visitMapService.save(visit);
    }

    @Test
    void findAll() {

        Set<Visit> visits  = visitMapService.findAll();

        assertEquals(1, visits.size());
    }

    @Test
    void deleteById() {

        visitMapService.deleteById(1L);

        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void delete() {

        for(Visit visit: visitMapService.findAll()){
            visitMapService.delete(visit);
        }

        assertEquals(0,visitMapService.findAll().size());
    }

    @Test
    void save() {

       Visit visit = new Visit();
       Pet pet = new Pet();
       Owner owner = new Owner();

       visit.setId(2L);
       pet.setId(2L);
       owner.setId(2L);
       pet.setOwner(owner);
       visit.setPet(pet);
       visitMapService.save(visit);


        assertEquals(2,visitMapService.findAll().size());
    }

    @Test
    void findById() {

        Visit visit = visitMapService.findById(1L);

        assertEquals(1L, visit.getId());
    }
}