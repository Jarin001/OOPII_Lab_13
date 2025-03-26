public class UserAuthManager {

    private static final String[][] adminCredentials = new String[10][2];
    private static int adminCount = 1;

    static {
        adminCredentials[0][0] = "root";
        adminCredentials[0][1] = "root";
    }

    public static int isAdminAuthenticated(String username, String password) {
        for (int i = 0; i < adminCredentials.length; i++) {
            if (username.equals(adminCredentials[i][0]) && password.equals(adminCredentials[i][1])) {
                return i;
            }
        }
        return -1;
    }

    public static boolean registerAdmin(String username, String password) {
        if (isAdminAuthenticated(username, password) != -1) return false;
        adminCredentials[adminCount][0] = username;
        adminCredentials[adminCount][1] = password;
        adminCount++;
        return true;
    }

    public static String isPassengerAuthenticated(String email, String password) {
        for (Customer c : User.getCustomersCollection()) {
            if (email.equals(c.getEmail()) && password.equals(c.getPassword())) {
                return "1-" + c.getUserID();
            }
        }
        return "0";
    }
}
