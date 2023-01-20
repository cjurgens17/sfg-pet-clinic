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
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }
}