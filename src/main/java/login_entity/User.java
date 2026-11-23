package login_entity;

/**
 * A simple entity representing a user. Users have a username and password..
 */
public class User {

    private final String name;
    private final String password;

    /**
     * Creates a new user with the given non-empty name and non-empty password.
     * @param name the username
     * @param password the password
     * @throws IllegalArgumentException if the password or name are empty or contain commas
     */


    public User(String name, String password) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (name.contains(",")) {
            throw new IllegalArgumentException("Password cannot contain commas");
        }
        if ("".equals(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.contains(",")) {
            throw new IllegalArgumentException("Password cannot contain commas");
        }

        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
