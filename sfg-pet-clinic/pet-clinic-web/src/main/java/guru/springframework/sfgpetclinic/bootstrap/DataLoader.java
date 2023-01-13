package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }


    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedradiology = specialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialitiesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialitiesService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Chris");
        owner1.setLastName("Jurgens");
        owner1.setAddress("123 Crayon Rd");
        owner1.setCity("Birmingham");
        owner1.setTelephone("1231234567");

        Pet chrisPet = new Pet();
        chrisPet.setPetType(savedDogPetType);
        chrisPet.setOwner(owner1);
        chrisPet.setBirthDate(LocalDate.now());
        chrisPet.setName("daisy");
        owner1.getPets().add(chrisPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mickey");
        owner2.setLastName("Mouse");
        owner2.setAddress("123 Crayon Rd");
        owner2.setCity("Birmingham");
        owner2.setTelephone("1231234567");

        Pet mickeysCat = new Pet();
        mickeysCat.setName("Wally");
        mickeysCat.setOwner(owner2);
        mickeysCat.setBirthDate(LocalDate.now());
        mickeysCat.setPetType(savedCatPetType);
        owner2.getPets().add(mickeysCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Harris");
        vet1.getSpecialities().add(savedradiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Rock");
        vet2.setLastName("Johnson");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
