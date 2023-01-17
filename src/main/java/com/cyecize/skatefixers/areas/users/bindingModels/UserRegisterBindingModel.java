package com.cyecize.skatefixers.areas.users.bindingModels;


import com.cyecize.skatefixers.areas.language.annotations.LocalLang;
import com.cyecize.skatefixers.areas.language.languagePacks.DictionaryEnImpl;
import com.cyecize.skatefixers.areas.language.services.LocalLanguage;
import com.cyecize.skatefixers.areas.users.bindingModels.validator.FieldMatch;
import com.cyecize.skatefixers.areas.users.bindingModels.validator.UniqueEmail;
import com.cyecize.skatefixers.areas.users.bindingModels.validator.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "passwordConfirm", message = "passwordsDoNotMatch")
public class UserRegisterBindingModel {


    @NotNull(message = "usernameIsNull")
    @NotEmpty(message = "usernameIsNull")
    @Pattern(regexp = "^[^\\s]{3,}$", message = "usernameInvalidFormat")
    @UniqueUsername(message = "usernameTaken")
    private String username;

    @NotNull(message = "emailIsNull")
    @NotEmpty(message = "emailIsNull")
    @UniqueEmail(message = "emailTaken")
    private String email;

    @NotNull(message = "passwordLengthIsLessThan")
    @NotEmpty(message = "passwordLengthIsLessThan" )
    @Size(min = 6, message = "passwordLengthIsLessThan")
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
