package database.classes;

public class Visit {
    private String Username;
    private String Email;
    private int Logged_visit;

    public Visit(String Username, String Email, int Logger_visit){
        this.Username = Username;
        this.Email = Email;
        this.Logged_visit = Logger_visit;
    }

    public String getUsername(){
        return this.Username;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getEmail(){
        return this.Email;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public int getLogged_visit(){
        return this.Logged_visit;
    }

    public void setLogged_visit(int Logged_visit){
        this.Logged_visit = Logged_visit;
    }

}
