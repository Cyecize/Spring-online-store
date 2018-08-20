package com.cyecize.skatefixers.areas.users.services;

import com.cyecize.skatefixers.areas.users.bindingModels.AddressBindingModel;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;

import java.util.List;

public interface AddressService {

    void createAddress(AddressBindingModel bindingModel, User user);

    void edit(Address address, AddressBindingModel bindingModel);

    void removeAddress(Address address);

    Address findById(Long id);

    List<Address> findAllAddresses(User user);

}
