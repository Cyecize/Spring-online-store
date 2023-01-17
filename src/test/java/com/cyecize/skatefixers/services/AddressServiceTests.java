package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.BaseIt;
import com.cyecize.skatefixers.areas.users.bindingModels.AddressBindingModel;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.repositories.AddressRepository;
import com.cyecize.skatefixers.areas.users.repositories.UserRepository;
import com.cyecize.skatefixers.areas.users.services.AddressService;
import com.cyecize.skatefixers.areas.users.services.AddressServiceImpl;
import com.cyecize.skatefixers.exceptions.JsonException;
import com.cyecize.skatefixers.util.ModelMerger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AddressServiceTests extends BaseIt {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    private AddressRepository mockedRepository = Mockito.mock(AddressRepository.class);

    private User user;

    @BeforeEach
    public void onInit() {
        this.addressRepository.deleteAll();
        user = new User();
        user.setPassword("");
        user.setUsername("");
        user.setEmail("");
        this.userRepository.saveAndFlush(user);

    }

    @Test
    public void createAddress_validBindingModel_ShouldCreateAddress() {
        int initialSize = addressRepository.findAll().size();
        AddressBindingModel addressBindingModel = new AddressBindingModel() {{
            setAddress("address");
            setFullName("fn");
            setPhoneNumber("35");
        }};
        this.addressService.createAddress(addressBindingModel, user);
        assertEquals("Address was not added", initialSize + 1, this.addressRepository.findAll().size());
    }

    @Test
    public void findOneById_invalidId_ShouldThrowError() {
        Mockito.when(mockedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        AddressService addressService = new AddressServiceImpl(mockedRepository, new ModelMapper(), new ModelMerger());
        assertThrows(JsonException.class, () -> addressService.findById(21L));
    }

}
