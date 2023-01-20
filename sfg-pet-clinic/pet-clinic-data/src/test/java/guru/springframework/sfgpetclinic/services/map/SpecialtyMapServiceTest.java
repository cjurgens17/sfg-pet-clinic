package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyMapServiceTest {

    SpecialtyMapService specialtyMapService;

    @BeforeEach
    void setUp() {
        specialtyMapService = new SpecialtyMapService();
        Speciality speciality = new Speciality();
        speciality.setId(1L);
        specialtyMapService.save(speciality);
    }

    @Test
    void findAll() {

        int size = specialtyMapService.findAll().size();

        assertEquals(1,size);
    }

    @Test
    void deleteById() {

        specialtyMapService.deleteById(1L);

        assertEquals(0, specialtyMapService.findAll().size());

    }

    @Test
    void delete() {
        specialtyMapService.findAll().forEach(speciality -> specialtyMapService.delete(speciality));

        assertEquals(0, specialtyMapService.findAll().size());
    }

    @Test
    void save() {

        Speciality specialty = new Speciality();
        specialty.setId(2L);
        specialtyMapService.save(specialty);

        assertEquals(2, specialtyMapService.findAll().size());
    }

    @Test
    void findById() {

        Speciality speciality = specialtyMapService.findById(1L);

        assertEquals(1L, speciality.getId());
    }
}