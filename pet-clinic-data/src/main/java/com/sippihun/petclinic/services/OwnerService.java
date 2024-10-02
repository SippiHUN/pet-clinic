package com.sippihun.petclinic.services;

import com.sippihun.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
