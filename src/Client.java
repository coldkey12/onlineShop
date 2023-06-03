import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        DBHandler dbHandler = new DBHandler();
        ArrayList<Item> listedItems = new ArrayList<>();
        ArrayList<Item> wishlist = new ArrayList<>();
        try {

            Socket socket = new Socket("127.0.0.1", 2424);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println("Type login,signup or exit");

                String command = input.next();
                if (command.equalsIgnoreCase("login")) {

                    System.out.println("username");
                    String username = input.next();
                    System.out.println("password");
                    String password = input.next();

                    if (dbHandler.login(username, password)) {
                        while(true) {
                            PackageData packageData = null;
                            System.out.println("Please use following commands only: listAll/addToWishlist/removeFromWishlist/back");
                            String command2 = input.next();
                            outputStream.writeObject(command2);


                            if (command2.equalsIgnoreCase("listAll")) {
                                if ((packageData = (PackageData) inputStream.readObject()) != null) {
                                    listedItems = packageData.getItems();
                                    for (Item item : listedItems) {
                                        System.out.println(item.getProduct() + " : " + item.getPrice());
                                    }
                                }
                            } else if (command2.equalsIgnoreCase("back")) {
                                break;
                            } else if (command2.equalsIgnoreCase("addToWishlist")) {
                                if ((packageData = (PackageData) inputStream.readObject()) != null){
                                    listedItems = packageData.getItems();
                                    System.out.println("Please insert item ID to add it to your wishlist");
                                    for (Item item : listedItems) {
                                        System.out.println(item.getProduct() + " : " + item.getPrice() + " : ID[" + item.getId() + "]");
                                    }
                                    int choosen = input.nextInt();
                                    int userId = dbHandler.rqstUserIdlogin(username,password);
                                    TransferAssistant transferAssistant = new TransferAssistant(userId,wishlist,choosen);
                                    outputStream.writeObject(transferAssistant);
                                    TransferAssistant transferAssistant1 = (TransferAssistant) inputStream.readObject();
                                    wishlist = transferAssistant1.getWishlist();
                                    for (Item item : wishlist){
                                        System.out.println(item.getProduct() + " added to your wish list.");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Try again");
                    }
                } else if (command.equalsIgnoreCase("signup")) {

                    System.out.println("new username");
                    String username1 = input.next();
                    System.out.println("make password");
                    String password1 = input.next();

                    if (!dbHandler.checkByName(username1)){
                        dbHandler.newUser(username1,password1);
                    } else {
                        System.out.println("Username " + username1 + " is taken already");
                    }
                } else {
                    break;
                }
            }
        } catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
        }
    }
}
