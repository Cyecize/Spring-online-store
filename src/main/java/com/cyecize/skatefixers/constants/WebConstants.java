package com.cyecize.skatefixers.constants;

import com.cyecize.skatefixers.SkateFixersApplication;

public class WebConstants {

    public static final String COOKIE_LANG_NAME = "lang";

    public static final String TWIG_BINDING_RESULT_OBJ_NAME = "errorList";

    public static final String SERVER_ROOT_FOLDER_PATH =
            SkateFixersApplication
                    .class
                    .getResource("")
                    .toString()
                    .replace("file:/", "")
                    .replace("com/cyecize/skatefixers", "");

    public static final String APPLICATION_NAME = "Skate Fixers";
}
