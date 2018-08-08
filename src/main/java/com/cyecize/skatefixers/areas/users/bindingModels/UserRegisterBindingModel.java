package com.cyecize.skatefixers.areas.users.bindingModels;


import com.cyecize.skatefixers.areas.users.bindingModels.validator.FieldMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@FieldMatch(first = "password", second = "passwordConfirm", message = "Passwords Did not Match")
public class UserRegisterBindingModel {

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @Length(min = 3, message = "Username len should be 3 or more")
    @Pattern(regexp = "^[a-zA-Z0-9\\-_.*~]{3,}$", message = "Skapan ivalid, napishi edno ime kato orata")
    private String username;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password cannot be null or empty")
    @NotEmpty(message = "Password cannot be null or empty" )
    @Length(min = 6, message = "pass Len cannot be less than 6")
    private String password;

    private String passwordConfirm;

    public UserRegisterBindingModel(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
