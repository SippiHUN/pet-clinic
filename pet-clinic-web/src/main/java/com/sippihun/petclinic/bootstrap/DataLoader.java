package com.sippihun.petclinic.bootstrap;

import com.sippihun.petclinic.model.Owner;
import com.sippihun.petclinic.model.Vet;
import com.sippihun.petclinic.services.OwnerService;
import com.sippihun.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Corleone");

        Owner owner2 = new Owner();
        owner2.setFirstName("Lionel");
        owner2.setLastName("Messi");

        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Son");
        vet1.setLastName("Goku");

        Vet vet2 = new Vet();
        vet2.setFirstName("Paul");
        vet2.setLastName("Atreides");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Owners and Vets saved");

    }
}
