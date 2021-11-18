package com.pocoda.gateway.helper;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtils {
    public static final String encodePassword(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static final boolean isPasswordMatching(String providedPassword, String storedPassword) {
        return DigestUtils.md5Hex(providedPassword).equals(storedPassword);
    }
}
