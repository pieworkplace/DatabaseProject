package database.classes;

public class User {
    private String username;
    private UserType userType;

    public enum UserType{
        ADMIN, OWNER, VISITOR
    }

    public static UserType stringToUserType(String string){
        String upperCaseString = string.toUpperCase();
        switch (upperCaseString){
            case "ADMIN":
                return UserType.ADMIN;
            case "OWNER":
                return UserType.OWNER;
            case "VISITOR":
                return UserType.VISITOR;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
