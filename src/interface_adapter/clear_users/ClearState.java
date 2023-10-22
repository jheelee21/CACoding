package interface_adapter.clear_users;

public class ClearState {

    private String users = "";

    public ClearState(ClearState copy) {
        users = copy.users;
    }

    public ClearState() {
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
