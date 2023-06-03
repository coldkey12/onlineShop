import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(2424);
            System.out.println("waiting");

            DBHandler dbHandler = new DBHandler();
            ArrayList<Item> items = dbHandler.rqstItems();
            ArrayList<User> users = dbHandler.rqstUsers();

            while (true){
                Socket socket = server.accept();
                System.out.println("client connected+");

                PackageData packageData = new PackageData(items,users);
                ClientHandler clientHandler = new ClientHandler(socket,packageData);
                clientHandler.start();
            }
        } catch (Exception exception){
            System.out.println(exception.fillInStackTrace());
        }
    }
}