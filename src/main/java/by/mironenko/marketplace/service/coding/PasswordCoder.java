package by.mironenko.marketplace.service.coding;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Pavel Mironenko
 * Class for coding password
 */
public class PasswordCoder {

    public String encrypt(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean isMatch(final String password, final String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
