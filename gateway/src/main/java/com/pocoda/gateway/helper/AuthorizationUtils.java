package com.pocoda.gateway.helper;


import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AuthorizationUtils {

    public static final String JWT_SECRET = "jwt-secret";

    public static final String USER_ID = "userId";

    public static final String IS_ADMIN = "isAdmin";

}
