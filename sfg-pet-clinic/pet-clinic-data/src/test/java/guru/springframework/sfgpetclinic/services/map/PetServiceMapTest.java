package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PetServiceMapTest {

    PetServiceMap petServiceMap;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Daisy");
        petServiceMap.save(pet);
    }

    @Test
    void findAll() {

        Set<Pet> pets = petServiceMap.findAll();

        assertEquals(1, pets.size());

    }

    @Test
    void deleteById() {

        petServiceMap.deleteById(1L);

        assertEquals(0,petServiceMap.findAll().size());


    }

    @Test
    void delete() {

        petServiceMap.findAll().forEach(pet -> { petServiceMap.delete(pet);});

        assertEquals(0, petServiceMap.findAll().size());

    }

    @Test
    void save() {

        Pet anotherPet = new Pet();
        petServiceMap.save(anotherPet);

        assertEquals(2, petServiceMap.findAll().size());
    }

    @Test
    void findById() {

        Pet pet = petServiceMap.findById(1L);

        assertEquals(1L, pet.getId());
    }
}