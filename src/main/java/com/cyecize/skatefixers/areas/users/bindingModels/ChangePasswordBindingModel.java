package com.cyecize.skatefixers.areas.users.bindingModels;

import com.cyecize.skatefixers.areas.users.bindingModels.validator.FieldMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@FieldMatch(first = "password", second = "passwordConfirm", message = "passwordsDoNotMatch")
public class ChangePasswordBindingModel {

    @NotNull(message = "passwordLengthIsLessThan")
    @NotEmpty(message = "passwordLengthIsLessThan")
    private String oldPassword;

    @NotNull(message = "passwordLengthIsLessThan")
    @NotEmpty(message = "passwordLengthIsLessThan" )
    @Length(min = 6, message = "passwordLengthIsLessThan")
    private String password;

    @NotNull(message = "passwordLengthIsLessThan")
    @NotEmpty(message = "passwordLengthIsLessThan" )
    private String passwordConfirm;

    public ChangePasswordBindingModel(){

    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
