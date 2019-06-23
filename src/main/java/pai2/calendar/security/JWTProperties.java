package pai2.calendar.security;

public class JWTProperties {
    public static final String SECRET = "pai2Calendar";
    public static final int EXPIRATION_TIME = 864_000_000; // 30 minutes
    public static final String TOKEN_PREFIX = "Bearer "; //TODO change ??
    public static final String HEADER_STRING = "Authorization"; // change ?
}
