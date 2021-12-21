package by.mironenko.marketplace.service.coding;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordCoderTest {

    private String hashedPassword = "$2a$10$C.HYOlJZxPL2T7eeQviSruSRcAO8voUkZf7kLbykBbS3GuVuud2z2";

    PasswordCoder passwordCoder = new PasswordCoder();

    @Test
    public void testHashNotNull() {
        String hash = passwordCoder.encrypt("holder");
        Assert.assertNotNull(hash);
    }

    @Test
    public void testIsMatch() {
        passwordCoder.isMatch("holder", hashedPassword);
    }

    @Test
    public void testIsMatchFail() {
        String anotherHash = "sdf32fsdg";
        String hashedPassword = passwordCoder.encrypt("holder");
        boolean actual = passwordCoder.isMatch(anotherHash, hashedPassword);
       Assert.assertFalse(actual);
    }
}
