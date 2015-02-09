/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cova2;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class UpdateCheckIT {

    @Test
    public void testisUpdating() throws IOException {
        File updateZip = new File("update.zip");
        updateZip.createNewFile();
        UpdateCheck updateCheck = new UpdateCheck();
        assertTrue(updateCheck.isUpdating());

    }

    @Test
    public void testCheckUpdateFail() throws IOException {
        File updateFail = new File("updateFail.err");
        updateFail.createNewFile();
        UpdateCheck updateCheck = new UpdateCheck();
        assertTrue(updateCheck.checkUpdateFail());
    }

}
