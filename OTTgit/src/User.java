import java.sql.*;
import java.util.Scanner;

public class User implements MediaManagementSystem {

    int Oid;
    int age;
    String phoneNumber;

    Connection dbConnection;

    public User(Connection dbconn) throws Exception {
        // JDBC driver name and database URL
        // Class.forName("com.mysql.jdbc.Driver");
        // dbconn=DriverManager.getConnection(
        // "jdbc:mysql://localhost:3306/daoproject?characterEncoding=latin1&useConfigs=maxPerformance",
        // "root",
        // "203102789");
        // // Database credentials
        dbConnection = dbconn;
    }

    @Override
    public void add_MediaItem(Media m) {

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Error!..Only Owner Can Perform This Operation.");
        System.out.println("You Can Switch From User To Owner To Perfrom This Task...");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void update_MediaItem(int ID) {

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Error!..Only Owner Can Perform This Operation.");
        System.out.println("You Can Switch From User To Owner To Perfrom This Task...");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void delete_MediaItem(int ID) {

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Error!..Only Owner Can Perform This Operation.");
        System.out.println("You Can Switch From User To Owner To Perfrom This Task...");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

    }


    @Override
    public void change_DefaultLanguage(int ID) {

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Error!..Only Owner Can Perform This Operation.");
        System.out.println("You Can Switch From User To Owner To Perfrom This Task...");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void searchMediaByTitle(int id) {

        PreparedStatement preparedStatement = null;
        String sql;

        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            sql = "Select * from Media where Tid=" + id;
            ResultSet rs = stmt.executeQuery(sql);

            // Extracting data from Album data set

            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                // Retrieve each row
                int tid = rs.getInt("Tid");
                String title = rs.getString("title");
                String releaseDate = rs.getString("releaseDate");

                System.out.println("Media ID: " + tid + " | Media Title: " + title + " |ReleaseDate: " + releaseDate);

            }
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException ex) {
            System.out.println("No such Media Exists...");
        }

    }

    @Override
    public void filterMediaByLanguage(String language) {
        PreparedStatement preparedStatement = null;
        String sql;

        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            sql = "Select m.Tid, m.title, m.releaseDate, l.language from media m join languages l on m.Tid = l.Tid where language="+language;
            ResultSet rs = stmt.executeQuery(sql);

            // Extracting data from Album data set
            System.out.println("Media Items of The Chosen Language");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                // Retrieve each row
                int tid = rs.getInt("Tid");
                String title = rs.getString("title");
                String releaseDate = rs.getString("releaseDate");
                String lang = rs.getString("language");

                System.out.println("Media ID: " + tid + " | Media Title: " + title
                        + "| Media Release Date: " + releaseDate + " | Media Language: " + lang);

                System.out.println(
                        "---------------------------------------------------------------------------------------------------------------------------------------------------");

            }
        } catch (SQLException ex) {
            // handlling any errors
            System.out.println("SQLException: " + ex.getMessage());
            // System.out.println("SQLState: " + ex.getSQLState());
            // System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void addRating(Rating r){
        
        // Statement stmt;
        PreparedStatement preparedStatement = null;

        String sql = "insert into ratings(Tid, Uid, rating) values (?,?,?)";

        try {
            System.out.println();
            System.out.println("Adding rating...");

            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, r.getTid());
            preparedStatement.setInt(2, r.getUid());
            preparedStatement.setInt(3, r.getRating());

            preparedStatement.executeUpdate();

            System.out.println("New rating has been added to record with below details...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Media ID: " + r.getTid());
            System.out.println("User ID : " + r.getUid());
            System.out.println("Rating : " + r.getRating());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Cannot Complete Action...                                        |");
            System.out.println("| ALERT :Rating Already Exists With This ID In Record...|");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addPreferredGenres(Genre g) {
        // Statement stmt;
        PreparedStatement preparedStatement = null;

        String sql = "insert into genre(Uid, preferredGenre1, preferredGenre2, preferredGenre3) values (?,?,?,?)";

        try {
            System.out.println();
            System.out.println("Adding genres...");

            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, g.getUid());
            preparedStatement.setString(2, g.getGenre1());
            preparedStatement.setString(3, g.getGenre2());
            preparedStatement.setString(4, g.getGenre3());

            preparedStatement.executeUpdate();

            System.out.println("New rpreferred genre has been added to record with below details...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("User ID: " + g.getUid());
            System.out.println("Preferred Genre 1 : " + g.getGenre1());
            System.out.println("Preferred Genre 2 : " + g.getGenre2());
            System.out.println("Preferred Genre 3 : " + g.getGenre3());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Cannot Complete Action...                                        |");
            System.out.println("| ALERT :Genre Already Exists With This ID In Record...|");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

}
