import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC3 {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/emp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin123";
    String string;
    public static void main(String[] args) {
    	
    	JDBC3 jdbc3= new JDBC3();
    	jdbc3.string="dsjsdf";
        try {
        	
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            Scanner scanner = new Scanner(System.in);
            

           // createTable(connection);

            for (int i = 0; i < 5; i++) {
                System.out.println("Menu:");
                System.out.println("1. Insert employee");
                System.out.println("2. Display all records");
                System.out.println("3. Update employee");
                System.out.println("4. Delete employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertRecords(connection);
                        break;
                    case 2:
                        displayRecords(connection);
                        break;
                    case 3:
                        updateRecords(connection);
                        break;
                    case 4:
                        deleteRecord(connection);
                        break;
                    case 5:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        connection.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	/*
	 * private static void createTable(Connection connection) throws SQLException {
	 * String createTableSQL =
	 * "CREATE TABLE IF NOT EXISTS Employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT, mail VARCHAR(45),mbl VARCHAR(45), city VARCHAR(45) )"
	 * ; try (Statement statement = connection.createStatement()) {
	 * statement.execute(createTableSQL);
	 * System.out.println("Table created or already exists."); } }
	 */

    private static void insertRecords(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO Employee (name, age,mail,mbl,city) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            for (int i = 1; i <= 5; i++) {
                System.out.print("Enter employee name for record " + i + ": ");
                String name = new Scanner(System.in).next();
                System.out.print("Enter employee age for record " + i + ": ");
                int age = new Scanner(System.in).nextInt();
                System.out.print("Enter employee mail for record " + i + ": ");
                String mail = new Scanner(System.in).next();
                System.out.print("Enter employee mbl for record " + i + ": ");
                String mbl = new Scanner(System.in).next();
                System.out.print("Enter employee city for record " + i + ": ");
                String city = new Scanner(System.in).next();

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, mail);
                preparedStatement.setString(4, mbl);
                preparedStatement.setString(5, city);
                preparedStatement.executeUpdate();
            }
            System.out.println("Records inserted successfully.");
        }
    }

    private static void displayRecords(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM Employee";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String mail = resultSet.getString("mail");
                String mbl = resultSet.getString("mbl");
                String city = resultSet.getString("city");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +", mail:" +  mail + ",mbl:" + mbl +", city:" + city);
            }
        }
    }

    private static void updateRecords(Connection connection) throws SQLException {
    	Scanner sc = new Scanner(System.in);
        String updateSQL = "UPDATE Employee SET age = ?, name = ?, mail = ?, mbl = ?, city = ? WHERE id = ?";
        //String updateSQL = "UPDATE Employee SET age = ?, name = ?, mail = ?, mbl = ?, city = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){
            for (int i = 1; i <= 1; i++) {
                System.out.print("Enter the ID of the employee to update for record " + i + ": ");
                int id = sc.nextInt();
                System.out.println("Enter updated age for record " + i + ": ");
                int updatedAge = sc.nextInt();
                System.out.println("Enter updated name for record " + i + ": ");
                String updatedname=sc.next();
                System.out.println("Enter updated mail for record " + i + ": ");
                String updatedmail=sc.next();
                System.out.println("Enter updated mbl for record " + i + ": ");
                String updatedmbl=sc.next();
                System.out.println("Enter updated city for record " + i + " ");
                String updatedcity=sc.next();
                
     
//                
//                "UPDATE Employee SET age = ? Employee SET name = ? Employee SET mail = ? Employee SET mbl = ?  Employee SET city = ?   WHERE id = ?";       
              
                preparedStatement.setInt(1, updatedAge);
                preparedStatement.setString(2, updatedname);
                preparedStatement.setString(3, updatedmail);
                preparedStatement.setString(4, updatedmbl);
                preparedStatement.setString(5, updatedcity);
                preparedStatement.setInt(6, id);
               
                preparedStatement.executeUpdate();
            }
            System.out.println("Records updated successfully.");
        }catch(Exception e)
        {
        	System.err.println(String.valueOf(e));
        }
    }

//    
//    private static void updateRecords(Connection connection) throws SQLException {
//        Scanner sc = new Scanner(System.in);
//        String updateSQL = "UPDATE Employee SET age = ?, name = ?, mail = ?, mbl = ?, city = ? WHERE id = ?";
//        
//        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
//            for (int i = 1; i <= 1; i++) {
//                System.out.print("Enter the ID of the employee to update for record " + i + ": ");
//                int id = sc.nextInt();
//                
//                System.out.print("Enter updated age for record " + i + ": ");
//                int updatedAge = sc.nextInt();
//                
//                System.out.print("Enter updated name for record " + i + ": ");
//                String updatedName = sc.next();
//                
//                System.out.print("Enter updated mail for record " + i + ": ");
//                String updatedMail = sc.next();
//                
//                System.out.print("Enter updated mbl for record " + i + ": ");
//                String updatedMbl = sc.next();
//                
//                System.out.print("Enter updated city for record " + i + ": ");
//                String updatedCity = sc.next();
//                
//                preparedStatement.setInt(1, updatedAge);
//                preparedStatement.setString(2, updatedName);
//                preparedStatement.setString(3, updatedMail);
//                preparedStatement.setString(4, updatedMbl);
//                preparedStatement.setString(5, updatedCity);
//                preparedStatement.setInt(6, id);
//                
//                preparedStatement.executeUpdate();
//            }
//            System.out.println("Records updated successfully.");
//        } catch (SQLException e) {
//            System.err.println("Error updating records: " + e.getMessage());
//        }
//    }

    
    
    private static void deleteRecord(Connection connection) throws SQLException {
        String deleteSQL = "DELETE FROM Employee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            System.out.print("Enter the ID of the employee to delete: ");
            int id = new Scanner(System.in).nextInt();

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Record deleted successfully.");
        }
    }
}
