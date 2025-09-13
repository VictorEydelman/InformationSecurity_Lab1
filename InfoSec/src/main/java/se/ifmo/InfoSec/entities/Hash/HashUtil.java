package se.ifmo.InfoSec.entities.Hash;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String password, String hashedPassword) {
        System.out.println(password+" "+hashedPassword);
        return BCrypt.checkpw(password, hashedPassword);
    }
}
