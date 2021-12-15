package com.yesevi.egitimkadir.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    public static final String EOBS_YONETICISI = "ROLE_EOBS_YONETICISI";
    public static final String EGITMEN = "ROLE_EGITMEN";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
