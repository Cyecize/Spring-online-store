package com.cyecize.skatefixers.areas.home.bindingModel;

import com.cyecize.skatefixers.areas.language.languagePacks.Dictionary;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactUsBindingModel {

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty" )
    private String fullName;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty" )
    private String phoneNumber;

    @NotNull(message = "fieldCannotBeEmpty")
    @NotEmpty(message = "fieldCannotBeEmpty" )
    private String message;

    public ContactUsBindingModel(){

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
