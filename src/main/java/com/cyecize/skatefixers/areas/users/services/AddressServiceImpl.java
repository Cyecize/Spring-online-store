package com.cyecize.skatefixers.areas.users.services;

import com.cyecize.skatefixers.areas.users.bindingModels.AddressBindingModel;
import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.repositories.AddressRepository;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.util.ModelMerger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    private final ModelMerger modelMerger;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, ModelMerger modelMerger) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.modelMerger = modelMerger;
    }


    @Override
    public void createAddress(AddressBindingModel bindingModel, User user) {
        Address address = this.modelMapper.map(bindingModel, Address.class);
        address.setUser(user);
        this.addressRepository.saveAndFlush(address);
    }

    @Override
    public void edit(Address address, AddressBindingModel bindingModel) {
        address = this.modelMerger.merge(bindingModel, address);
        this.addressRepository.saveAndFlush(address);
    }

    @Override
    public void removeAddress(Address address) {
        address.setActive(false);
        this.addressRepository.saveAndFlush(address);
    }

    @Override
    public Address findById(Long id) {
        if(!this.addressRepository.findById(id).isPresent())
            throw new JsonException("Address not found!");
        return this.addressRepository.findById(id).get();
    }

    @Override
    public List<Address> findAllAddresses(User user) {
        return this.addressRepository.findByUser(user);
    }
}
