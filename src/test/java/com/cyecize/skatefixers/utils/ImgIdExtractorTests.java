package com.cyecize.skatefixers.utils;

import com.cyecize.skatefixers.areas.googleDrive.util.ImageIdExtractor;
import org.junit.Assert;
import org.junit.Test;

public class ImgIdExtractorTests {

    private static final String VALID_URL = "https://drive.google.com/uc?id=1DM-O-aFWedDbLu_OPWMtZfsbpTr3fVYN&";

    private static final String ID_FROM_VALID_URL = "1DM-O-aFWedDbLu_OPWMtZfsbpTr3fVYN";

    private static final String INVALID_URL = "https://images.all-free-download.com/images/graphiclarge/ag_consulting_0_75015.jpg";

    @Test
    public void testExtractId_ValidUrl_ShouldExtractId(){
        String id = ImageIdExtractor.extractId(VALID_URL);
        Assert.assertEquals("id mismatch", ID_FROM_VALID_URL, id);
    }

    @Test
    public void testExtractId_InvalidUrl_ShouldReturnEmptyStr(){
        Assert.assertEquals("non empty string returned", "", ImageIdExtractor.extractId(INVALID_URL));
    }
}
