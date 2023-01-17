package com.cyecize.skatefixers.utils;

import com.cyecize.skatefixers.areas.googleDrive.util.MultipartToFileConverter;
import com.cyecize.skatefixers.exceptions.JsonException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MultipartToFIleConverterTests {

    @Test
    public void convertTest_NullValue_ThrowJsonException(){
        assertThrows(JsonException.class, () -> MultipartToFileConverter.convert(null));
    }
}
