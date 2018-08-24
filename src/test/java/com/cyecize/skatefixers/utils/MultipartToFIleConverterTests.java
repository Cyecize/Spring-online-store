package com.cyecize.skatefixers.utils;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.exceptions.JsonException;
import org.junit.Test;

import java.io.File;

public class MultipartToFIleConverterTests {


    @Test(expected = JsonException.class)
    public void convertTest_NullValue_ThrowJsonException(){
        File file = MultipartToFileConverter.convert(null);
    }
}
