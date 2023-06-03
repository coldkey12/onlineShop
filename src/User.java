import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private ArrayList<Item> wishList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Item> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Item> wishList) {
        this.wishList = wishList;
    }

    public User() {
    }

    public User(int id, String username, String password, ArrayList<Item> wishList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wishList = wishList;
    }
}
