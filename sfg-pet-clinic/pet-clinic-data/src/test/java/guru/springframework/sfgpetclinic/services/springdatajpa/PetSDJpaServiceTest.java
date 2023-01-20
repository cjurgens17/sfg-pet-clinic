package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petSDJpaService;

    Pet returnPet;

    @BeforeEach
    void setUp() {

        returnPet = new Pet();

        returnPet.setId(1L);
    }

    @Test
    void findAll() {
        Pet pet1 = new Pet();
        pet1.setId(2L);
        Pet pet2 =new Pet();
        pet2.setId(3L);
        Set<Pet> returnPetsSet = new HashSet<>();
        returnPetsSet.add(pet1);
        returnPetsSet.add(pet2);

        when(petRepository.findAll()).thenReturn(returnPetsSet);

        Set<Pet> pets = petSDJpaService.findAll();

        assertNotNull(pets);

        assertEquals(2, returnPetsSet.size());
    }

    @Test
    void findById() {

        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = petSDJpaService.findById(1L);

        assertEquals(1L, pet.getId());
    }

    @Test
    void save() {

        Pet pet = new Pet();
        pet.setId(2L);


        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = petSDJpaService.save(pet);



        assertNotNull(savedPet);

        verify(petRepository).save(any());
    }

    @Test
    void delete() {

        petSDJpaService.delete(returnPet);

        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {

        petSDJpaService.deleteById(returnPet.getId());

        //Make sure only one
        verify(petRepository, times(1)).deleteById(anyLong());
    }
}