package util;

/*
 * This class is used to store global variables. It cannot be extended,
 * and all variables are static and final.
 * 
 */

public final class Fields {
    public static final String SERVER_ADDRESS = "localhost";
    public static final int LOGIN_SERVER_PORT = 1000;
    public static final int LOBBY_SERVER_PORT = 2000;
    public static final int GAME_SERVER_PORT = 3000;
    
    public static final int FRONTAL_ATTACK = 0;
    public static final int SEMIAERIAL_ATTACK = 1;
    public static final int AERIAL_ATTACK = 2;
}
