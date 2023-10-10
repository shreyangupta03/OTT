import java.sql.*;
import java.util.Scanner;

public class Owner implements MediaManagementSystem {

    int Oid;
    int age;
    String phoneNumber;

    Connection dbConnection;

    public Owner(Connection dbconn) throws Exception {
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

        // Statement stmt;
        PreparedStatement preparedStatement = null;

        String sql = "insert into media(Tid, title, releaseDate) values (?,?,?)";

        try {
            System.out.println();
            System.out.println("Adding Media item...");

            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, m.getTid());
            preparedStatement.setString(2, m.getTitle());
            preparedStatement.setString(3, m.getreleaseDate());

            preparedStatement.executeUpdate();

            System.out.println("New Media item has been added to record with below details...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Media ID: " + m.getTid());
            System.out.println("Cutomer Title : " + m.getTitle());
            System.out.println("Cutomer Release Date : " + m.getreleaseDate());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Cannot Complete Action...                                        |");
            System.out.println("| ALERT : Media Already Exists With This Media ID In Record...|");
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
    public void update_MediaItem(int ID) {
        Media md = new Media();

        String sql;

        Statement stmt = null;

        try {

            stmt = dbConnection.createStatement();

            sql = "select * from media where Tid =" + ID;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: Extract data from result set

            while (rs.next()) {
                // Retrieve by column name
                int mdTid = rs.getInt("Tid");
                String mdTitle = rs.getString("title");
                String mdReleaseDate = rs.getString("releaseDate");

                md.setTid(mdTid);
                md.setTitle(mdTitle);
                md.setReleaseDate(mdReleaseDate);
            }

            // Add exception handling here if more than one row is returned
        } catch (SQLException ex) {
            // handlling any errors if the medianot found in record
            System.out.println("Error!..No such Media exists in record...");
            return;
            // System.out.println("SQLException: " + ex.getMessage());
            // System.out.println("SQLState: " + ex.getSQLState());
            // System.out.println("VendorError: " + ex.getErrorCode());
        }

        Scanner sc = new Scanner(System.in);
        String cmd = "";

        System.out.println("Changing Media Details...");

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println();
        System.out.print("Do you want to change media title? Y/N : ");
        cmd = sc.nextLine();
        if (cmd.equals("Y")) {

            System.out.print("Enter new title for media: ");
            String str = sc.nextLine();
            md.setTitle(str);
        }

        System.out.println();
        System.out.print("Do you want to change media's release date? Y/N : ");
        cmd = sc.nextLine();
        if (cmd.equals("Y")) {

            System.out.print("Enter new release date for media: ");
            String str = sc.nextLine();
            md.setReleaseDate(str);
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

        PreparedStatement preparedStatement = null;
        sql = "update media set title=?,releaseDate=? where Tid= ?";

        try {

            System.out.println("Updating Customer details....");
            System.out.println();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, md.getTitle());
            preparedStatement.setString(2, md.releaseDate);
            preparedStatement.setInt(3, md.getTid());
            // execute update SQL stetement
            preparedStatement.executeUpdate();
            System.out.println();
            System.out.println("Media has been succesfully updated with below details");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Media ID: " + md.getTid() + " | Media Title: " + md.getTitle()
                    + " | Media Release Date: " + md.getreleaseDate());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete_MediaItem(int ID) {

        PreparedStatement preparedStatement = null;
        String sql;

        sql = "delete from media where Tid = " + ID;

        try {
            System.out.println();
            System.out.println("Deleting Media Item...");
            preparedStatement = dbConnection.prepareStatement(sql);
            // execute delete SQL stetement
            preparedStatement.executeUpdate();
            System.out.println();
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Customer with Tid " + ID + " has been deleted successfully...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("No Such Customer..");
            // System.out.println(e.getMessage());
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void change_DefaultLanguage(int ID) {
        Languages lang = new Languages();

        String sql;

        Statement stmt = null;

        try {

            stmt = dbConnection.createStatement();

            sql = "select * from languages where Tid =" + ID;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: Extract data from result set

            while (rs.next()) {
                // Retrieve by column name
                int langTid = rs.getInt("Tid");
                String langDefaultLanguage = rs.getString("language");

                lang.setTid(langTid);
                lang.setDefLanguage(langDefaultLanguage);
            }

            // Add exception handling here if more than one row is returned
        } catch (SQLException ex) {
            // handlling any errors if the medianot found in record
            System.out.println("Error!..No such Media exists in record...");
            return;
            // System.out.println("SQLException: " + ex.getMessage());
            // System.out.println("SQLState: " + ex.getSQLState());
            // System.out.println("VendorError: " + ex.getErrorCode());
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Changing Media Details...");

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.print("Enter new drfault language for media: ");
        String str = sc.nextLine();
        lang.setDefLanguage(str);

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------");

        PreparedStatement preparedStatement = null;
        sql = "update languages set language=? where Tid= ?";

        try {

            System.out.println("Updating Language details....");
            System.out.println();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, lang.getDefLanguage());
            preparedStatement.setInt(2, lang.getTid());
            // execute update SQL stetement
            preparedStatement.executeUpdate();
            System.out.println();
            System.out.println("Language has been succesfully updated with below details");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Media ID: " + lang.getTid() + " | Media Default Language: " + lang.getDefLanguage());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
            sql = "Select m.Tid, m.title, m.releaseDate, l.language from media m join languages l on m.Tid = l.Tid where language='"+language+"'";
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
        }
        catch (SQLException ex) {
            // handlling any errors
            System.out.println("No Such Language...");
            return;
            // System.out.println("SQLException: " + ex.getMessage());
            // System.out.println("bdgbgdhfj");
            // System.out.println("SQLState: " + ex.getSQLState());
            // System.out.println("VendorError: " + ex.getErrorCode());
        }
        // try {
        //     stmt = dbConnection.createStatement();
        //     sql = "Select * From Media where Tid=" + tid;

        //     ResultSet rs = stmt.executeQuery(sql);

        //     // Extracting data from Album data set

        //     System.out.println(
        //             "---------------------------------------------------------------------------------------------------------------------------------------------------");
        //     while (rs.next()) {
        //         // Retrieve each row
        //         int id = rs.getInt("Tid");
        //         String title = rs.getString("title");
        //         String releaseDate = rs.getString("releaseDate");

        //         System.out.println("Media ID: " + id + " | Media Title: " + title + " |ReleaseDate: " + releaseDate);

        //     }
        //     System.out.println(
        //             "---------------------------------------------------------------------------------------------------------------------------------------------------");

        // } catch (SQLException ex) {
        //     System.out.println("No such Media with  Tid=" + tid);
        // }
    }

    // @Override
    // public void filterMediaByLanguage(String language) {
    // PreparedStatement preparedStatement = null;
    // String sql;

    // Statement stmt = null;

    // try {
    // stmt = dbConnection.createStatement();
    // sql = "Select m.Tid, m.title, m.releaseDate, l.language from media m join
    // languages l on m.Tid = l.Tid where language="+language;
    // ResultSet rs = stmt.executeQuery(sql);

    // // Extracting data from Album data set
    // System.out.println("Media Items of The Chosen Language");
    // System.out.println(
    // "---------------------------------------------------------------------------------------------------------------------------------------------------");
    // while (rs.next()) {
    // // Retrieve each row
    // int tid = rs.getInt("Tid");
    // String title = rs.getString("title");
    // String releaseDate = rs.getString("releaseDate");
    // String lang = rs.getString("language");

    // System.out.println("Media ID: " + tid + " | Media Title: " + title
    // + "| Media Release Date: " + releaseDate + " | Media Language: " + lang);

    // System.out.println(
    // "---------------------------------------------------------------------------------------------------------------------------------------------------");

    // }
    // } catch (SQLException ex) {
    // // handlling any errors
    // System.out.println("SQLException: " + ex.getMessage());
    // System.out.println("bdgbgdhfj");
    // // System.out.println("SQLState: " + ex.getSQLState());
    // // System.out.println("VendorError: " + ex.getErrorCode());
    // }
    // }

    @Override
    public void addRating(Rating r) {

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
