package com.cyecize.skatefixers.areas.users.services;

import com.cyecize.skatefixers.areas.users.bindingModels.UserRegisterBindingModel;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.entities.UserRole;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void createUser(UserRegisterBindingModel bindingModel, BindingResult result) {

//        if (userRepository.findUserByUsername(bindingModel.getUsername()) != null)
//            throw new UserBindingException("User already Xizt", "username", bindingModel,result);
//        if (this.userRepository.findUserByUsernameOrEmail(bindingModel.getUsername(), bindingModel.getEmail()) != null)
//            throw new UserBindingException("Email already exists", "email", bindingModel,result);
//        if (userRepository.findUserByUcn(bindingModel.getUcn()) != null)
//            throw new UserBindingException("Person with that ucn already exists", "ucn", bindingModel,result);

        User user = this.modelMapper.map(bindingModel, User.class);
        if (this.userRepository.findAll().size() < 1) {
            List<UserRole> roles = this.roleService.findAllRoles();
            roles.forEach(user::addRole);
        } else {
            UserRole role = this.roleService.findRoleByName("ROLE_USER");
            user.addRole(role);
        }
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findOneById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findOneByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public void disableUser(User user) {
        user.setEnabled(false);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void changeUserRole(User user, UserRoleType roleType) {
//        switch (roleType) {
//            case ROLE_USER:
//                user.setRoles(new ArrayList<UserRole>() {{
//                    add(roleService.findRoleByName("ROLE"));
//                }});
//                break;
//            case ROLE_ADMIN:
//                user.setRoles(new ArrayList<>());
//                user.addRole(this.roleService.findRoleByName("ADMIN"));
//                user.addRole(this.roleService.findRoleByName("ADMIN"));
//                user.addRole(this.roleService.findRoleByName("USER"));
//                break;
//        }
//        this.userRepository.saveAndFlush(user); TODO totally refactor this shit
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsernameOrEmail(s, s);
        if (user == null)
            throw new UsernameNotFoundException("Username was not found");
        return user;
    }
}
