package com.cyecize.skatefixers.areas.language.languagePacks;

public interface ErrorDictionary  {

    String usernameIsNull();

    String usernameInvalidFormat();

    String usernameTaken();

    String emailIsNull();

    String emailTaken();

    String passwordLengthIsLessThan();

    String passwordsDoNotMatch();

    String passwordIsIncorrect();

    String productNotFound();

    String categoryWithNameWasNotFound(String catName);

    String pageIsEmpty();

    String brandWithNameDoesNotExist(String brandName);

    String brandNameTaken();

    String fieldCannotBeEmpty();


}
