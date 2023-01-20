package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {
    VetMapService vetMapService;




    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialtyMapService());

        Vet vet = new Vet();
        vet.setId(1L);
        vetMapService.save(vet);
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetMapService.findAll();

        assertEquals(1,vets.size());


    }

    @Test
    void deleteById() {

        vetMapService.deleteById(1L);

        assertEquals(0,vetMapService.findAll().size());
    }

    @Test
    void delete() {

        for (Vet vet : vetMapService.findAll()) {
            vetMapService.delete(vet);
        }

        assertEquals(0,vetMapService.findAll().size());
    }

    @Test
    void save() {

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vetMapService.save(vet2);

        assertEquals(2, vetMapService.findAll().size());
    }

    @Test
    void findById() {

        Vet vet = vetMapService.findById(1L);

        assertEquals(1L, vet.getId());
    }
}