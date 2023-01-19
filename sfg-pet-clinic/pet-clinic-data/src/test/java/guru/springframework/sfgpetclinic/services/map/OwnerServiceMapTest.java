package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;


    @BeforeEach
    void setUp() {

        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setLastName("Smith");
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {

        Set<Owner> ownerSet= ownerServiceMap.findAll();

        assertEquals(1,ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1L);

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {

        ownerServiceMap.delete(ownerServiceMap.findById(1L));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, owner2.getId());
    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(1L);

        assertEquals(1L,owner.getId());
    }

    @Test
    void findByLastName() {

        Owner smith = ownerServiceMap.findByLastName("Smith");

        assertNotNull(smith.getLastName());

        assertEquals(1L,smith.getId());
    }

    @Test
    void findByLastNameNotFound() {

        Owner smith = ownerServiceMap.findByLastName("foo");

        assertNull(smith);
    }
}