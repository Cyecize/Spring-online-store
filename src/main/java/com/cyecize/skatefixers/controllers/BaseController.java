package com.cyecize.skatefixers.controllers;

import com.cyecize.skatefixers.areas.language.services.LocalLanguage;

public abstract class BaseController {

    protected final LocalLanguage language;

    protected BaseController(LocalLanguage language) {
        this.language = language;
    }

    //TODO model And View view / redirect
}
