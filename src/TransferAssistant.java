import java.io.Serializable;
import java.util.ArrayList;

public class TransferAssistant implements Serializable {
    private int userId;
    private ArrayList<Item> wishlist;
    private int itemId;

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Item> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<Item> wishlist) {
        this.wishlist = wishlist;
    }

    public TransferAssistant() {
    }

    public TransferAssistant(int userId, ArrayList<Item> wishlist, int itemId) {
        this.userId = userId;
        this.wishlist = wishlist;
        this.itemId = itemId;
    }
}
