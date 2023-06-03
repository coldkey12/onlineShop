import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private double price;
    private String product;
    private int expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int isExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public Item() {
    }

    public Item(int id, double price, String product, int expired) {
        this.id = id;
        this.price = price;
        this.product = product;
        this.expired = expired;
    }
}
