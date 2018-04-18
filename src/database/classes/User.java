package database.classes;

public class User {
    private String username;
    private UserType userType;

    public enum UserType{
        ADMIN, OWNER, VISITOR
    }

    public User(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
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
}
