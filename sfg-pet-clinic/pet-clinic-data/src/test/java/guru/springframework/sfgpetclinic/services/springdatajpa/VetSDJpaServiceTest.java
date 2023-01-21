package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetSDJpaService;

    Vet vet;

    @BeforeEach
    void setUp(){
        vet = new Vet();
        vet.setId(1L);
    }

    @Test
    void findAll() {

        Set<Vet> vets = vetSDJpaService.findAll();
        vets.add(new Vet());
        vets.add(new Vet());

        when(vetRepository.findAll()).thenReturn(vets);
        Set<Vet> vetSet = vetSDJpaService.findAll();

        assertEquals(2, vets.size());
        assertNotNull(vetSet);
    }

    @Test
    void findById() {

        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));

        Vet vet = vetSDJpaService.findById(1L);

        assertEquals(1L, vet.getId());


    }

    @Test
    void save() {

        Vet vet1 = new Vet();
        vet1.setId(2L);

        when(vetRepository.save(any())).thenReturn(vet);

        Vet savedVet = vetSDJpaService.save(vet);

        assertNotNull(savedVet);

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {

        vetSDJpaService.delete(vet);

        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {

        vetSDJpaService.deleteById(vet.getId());

        verify(vetRepository,times(1)).deleteById(anyLong());

    }
}