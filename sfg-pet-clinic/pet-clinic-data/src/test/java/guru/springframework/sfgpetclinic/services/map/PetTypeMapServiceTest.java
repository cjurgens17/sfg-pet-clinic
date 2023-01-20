package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        PetType petType = new PetType();
        petType.setId(1L);
        petTypeMapService.save(petType);

    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();

        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {

        petTypeMapService.deleteById(1L);

        assertEquals(0,petTypeMapService.findAll().size());
    }

    @Test
    void delete() {

        petTypeMapService.findAll().forEach(petType -> petTypeMapService.delete(petType));

        assertEquals(0,petTypeMapService.findAll().size(),"deleting all pets");
    }

    @Test
    void save() {

        PetType petType2 = new PetType();
        petType2.setId(2L);
        petTypeMapService.save(petType2);

        assertEquals(2,petTypeMapService.findAll().size(),"Adding another pettype to map");
    }

    @Test
    void findById() {

        PetType petType = petTypeMapService.findById(1L);

        assertEquals(1L, petType.getId());
    }
}