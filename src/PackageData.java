import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private ArrayList<Item> items;
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public PackageData() {
    }

    public PackageData(ArrayList<Item> items, ArrayList<User> users) {
        this.items = items;
        this.users = users;
    }
}
