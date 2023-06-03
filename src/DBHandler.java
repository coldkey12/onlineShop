import java.sql.*;
import java.util.ArrayList;

public class DBHandler {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/online_shop";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,"root","");
        return dbConnection;
    }

    public ArrayList<Item> rqstItems(){
        String insert = "SELECT * FROM " + Const.os_items;
        ArrayList<Item> items = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                items.add(new Item(resultSet.getInt(1),
                        resultSet.getDouble(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return items;
    }

    public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        String insert = "SELECT * FROM " + Const.os_users + " WHERE " + Const.users_username + " =? && " + Const.users_password + " =?";
        boolean flag = false;

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                flag=true;
            }

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    public ArrayList<Integer> rqstUserSIdS() throws SQLException, ClassNotFoundException {
        String insert = "SELECT " + Const.users_id + " FROM " + Const.os_users;
        ArrayList<Integer> nums = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                nums.add(resultSet.getInt(1));
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return nums;
    }

    public void newUser(String username, String password){
        String insert = "INSERT INTO " + Const.os_users + "(" + Const.users_username + "," + Const.users_password + ") VALUES(?,?)";
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public boolean checkByName(String username){
        String insert = "SELECT * FROM " + Const.os_users + " WHERE " + Const.users_username + " =?";
        boolean flag = false;
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                flag = true;
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    public int rqstUserIdlogin(String username,String password) throws SQLException {
        String insert = "SELECT " + Const.users_id + " FROM " + Const.os_users + " WHERE " + Const.users_username + " =? && " + Const.users_password + " =?";
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return resultSet.getInt(1);
    }

    public ArrayList<User> rqstUsers(){
        String insert = "SELECT * FROM " + Const.os_users;
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Item> wishlist = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),wishlist));
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return users;
    }
}
