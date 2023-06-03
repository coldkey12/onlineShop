import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private Socket socket;
    private PackageData packageData;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PackageData getPackageData() {
        return packageData;
    }

    public void setPackageData(PackageData packageData) {
        this.packageData = packageData;
    }

    public ClientHandler() {
    }

    public ClientHandler(Socket socket, PackageData packageData) {
        this.socket = socket;
        this.packageData = packageData;
    }
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            String command = "";
            while ((command = (String) inputStream.readObject()) != null) {

                if (command.equalsIgnoreCase("listAll")) {

                    outputStream.writeObject(packageData.getUsers());

                } else if (command.equalsIgnoreCase("addToWishlist")) {
                    outputStream.writeObject(packageData);

                    TransferAssistant transferAssistant = (TransferAssistant) inputStream.readObject();
                    for (User user : packageData.getUsers()) {
                        if (transferAssistant.getUserId() == user.getId()) {

                            for (Item item : packageData.getItems()){
                                if(transferAssistant.getItemId() == item.getId()){
                                    ArrayList<Item> tempItems = new ArrayList<>();
                                    tempItems.add(item);
                                    transferAssistant.setWishlist(tempItems);
                                }
                            }

                        }
                    }
                    outputStream.writeObject(transferAssistant);
                }

            }
        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }
}
