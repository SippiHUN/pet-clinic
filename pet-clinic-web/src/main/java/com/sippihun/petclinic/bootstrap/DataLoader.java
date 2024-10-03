package com.sippihun.petclinic.bootstrap;

import com.sippihun.petclinic.model.Owner;
import com.sippihun.petclinic.model.Pet;
import com.sippihun.petclinic.model.PetType;
import com.sippihun.petclinic.model.Speciality;
import com.sippihun.petclinic.model.Vet;
import com.sippihun.petclinic.services.OwnerService;
import com.sippihun.petclinic.services.PetTypeService;
import com.sippihun.petclinic.services.SpecialityService;
import com.sippihun.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedRadiology = specialityService.save(radiology);
        Speciality savedSurgery = specialityService.save(surgery);
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Corleone");
        owner1.setAddress("Sandor Palota");
        owner1.setCity("Budapest");
        owner1.setTelephone("107");

        Pet michaelsDog = new Pet();
        michaelsDog.setPetType(savedDog);
        michaelsDog.setName("Rosco");
        michaelsDog.setOwner(owner1);
        michaelsDog.setBirthDate(LocalDate.of(2024, 6, 2));

        owner1.setPets(Set.of(michaelsDog));

        Owner owner2 = new Owner();
        owner2.setFirstName("Lionel");
        owner2.setLastName("Messi");
        owner2.setAddress("Barcelona street 1");
        owner2.setCity("Miami");
        owner2.setTelephone("911");

        Pet messisCat = new Pet();
        messisCat.setPetType(savedCat);
        messisCat.setName("Ceasar");
        messisCat.setOwner(owner2);
        messisCat.setBirthDate(LocalDate.of(2020, 1, 3));
        owner2.setPets(Set.of(messisCat));

        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Son");
        vet1.setLastName("Goku");
        vet1.setSpecialities(Set.of(savedRadiology, savedDentistry));

        Vet vet2 = new Vet();
        vet2.setFirstName("Paul");
        vet2.setLastName("Atreides");
        vet2.setSpecialities(Set.of(savedSurgery));

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Owners and Vets saved");
    }
}
