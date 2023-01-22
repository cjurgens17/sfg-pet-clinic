package guru.springframework.sfgpetclinic.services.map.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.SpecialtySDJpaService;
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
class SpecialtySDJpaServiceTest {
    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialtySDJpaService specialtySDJpaService;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {

        returnSpeciality = new Speciality();
        returnSpeciality.setId(1L);
    }

    @Test
    void findAll() {

        Set<Speciality> returnSpecialities = new HashSet<>();
        Speciality sp1 = new Speciality();
        sp1.setId(1L);
        Speciality sp2 = new Speciality();
        sp2.setId(2L);
        returnSpecialities.add(sp1);
        returnSpecialities.add(sp2);

        when(specialityRepository.findAll()).thenReturn(returnSpecialities);
        Set<Speciality> specialities = specialtySDJpaService.findAll();

        assertNotNull(specialities);
        assertEquals(2,returnSpecialities.size());
    }

    @Test
    void findById() {

        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        Speciality speciality = specialtySDJpaService.findById(1L);

        assertEquals(1L, speciality.getId());
    }

    @Test
    void save() {

        Speciality speciality = new Speciality();
        speciality.setId(2L);



        when(specialityRepository.save(any())).thenReturn(returnSpeciality);

        Speciality savedSpeciality = specialtySDJpaService.save(returnSpeciality);

        assertNotNull(savedSpeciality);

        verify(specialityRepository).save(any());
    }

    @Test
    void delete() {

        specialtySDJpaService.delete(returnSpeciality);

        verify(specialityRepository).delete(any());
    }

    @Test
    void deleteById() {

        specialtySDJpaService.deleteById(returnSpeciality.getId());

        verify(specialityRepository,times(1)).deleteById(anyLong());
    }
}