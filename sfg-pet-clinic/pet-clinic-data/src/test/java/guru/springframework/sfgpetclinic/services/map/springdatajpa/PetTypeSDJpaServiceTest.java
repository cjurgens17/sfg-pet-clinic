package guru.springframework.sfgpetclinic.services.map.springdatajpa;


import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.PetTypeSDJpaService;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;

    PetType returnPetType;

    @BeforeEach
    void setUp() {

        returnPetType = new PetType();
        returnPetType.setId(1L);

    }

    @Test
    void findAll() {

        Set<PetType> returnPetTypes = new HashSet<>();
        PetType petType = new PetType();
        petType.setId(2L);
        PetType petType1 = new PetType();
        petType1.setId(3L);
        returnPetTypes.add(petType);
        returnPetTypes.add(petType1);

        when(petTypeRepository.findAll()).thenReturn(returnPetTypes);
        Set<PetType> petTypes = petTypeSDJpaService.findAll();

        assertNotNull(petTypes);

        assertEquals(2, returnPetTypes.size());
    }

    @Test
    void findById() {

        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        PetType petType = petTypeSDJpaService.findById(1L);

        assertEquals(1L, petType.getId());
    }

    @Test
    void save() {

        PetType savePetType = new PetType();
        savePetType.setId(1L);

        when(petTypeRepository.save(any())).thenReturn(returnPetType);

        PetType savedPet = petTypeSDJpaService.save(savePetType);

        assertNotNull(savedPet);

        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        petTypeSDJpaService.delete(returnPetType);

        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {

        petTypeSDJpaService.deleteById(returnPetType.getId());

        verify(petTypeRepository,times(1)).deleteById(anyLong());
    }
}