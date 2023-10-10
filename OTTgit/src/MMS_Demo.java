
//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;

public class MMS_Demo {
    public static MMS_Factory mmsFactory;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            mmsFactory = new MMS_Factory();

            while (true) {

                System.out.println(
                        "***************************************************************************************************************************************************");
                if (mmsFactory.CurrentUser()) {
                    System.out.println(
                            " |Media Management System| |Logged As: Owner|");
                } else {
                    System.out.println(
                            " |Media Management System| |Logged As: User|");
                }

                System.out.println(
                        "***************************************************************************************************************************************************");
                System.out.println(
                        " |Main Menu|-  ");
                System.out.println(
                        "***************************************************************************************************************************************************");
                System.out.println("| Use Cases|                                |  |To Select|");
                System.out.println(" *********************************************************");
                System.out.println("| To Add a Media Item (O)                   |  | Enter 1 |");
                System.out.println("| To Update a Media Item (O)                |  | Enter 2 |");
                System.out.println("| To Delete a Media Item (O)                |  | Enter 3 |");
                System.out.println("| To Change the Default Language (O)        |  | Enter 4 |");
                System.out.println("| To Search a Media by ID (O/U)             |  | Enter 5 |");
                System.out.println("| To Search a Media by Language (O/U)       |  | Enter 6 |");//sh
                System.out.println("| To Add A Rating For a Media Item (O/U)    |  | Enter 7 |");
                System.out.println("| To Add Preferred Genres (O/U)             |  | Enter 8 |");//sh
                System.out.println("| To Change To Owner/User (O/U)             |  | Enter 9 |");
                System.out.println("| To Exit (O/U)                             |  | Enter 10 |");

                System.out.println(
                        "***************************************************************************************************************************************************");
                System.out.print("Please Select Your Choice: ");
                int option = sc.nextInt();

                if (option == 1)
                    add_media();
                else if (option == 2)
                    update_media();
                else if (option == 3)
                    delete_media();
                else if (option == 4)
                    change_language();
                else if (option == 5)
                    search_Media();
                else if (option == 6)
                    search_MediaByLang();
                else if (option == 7)
                    add_rating();
                else if (option == 8)
                    add_genres();
                else if (option == 9)
                    ChangeUser();
                else if (option == 10) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("You Need to Select Atleast One Option From Above");
                }
            }

        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void ChangeUser() {
        if (mmsFactory.CurrentUser()) {
            while (true) {
                System.out.println("UserName: User");
                System.out.println("------------------");
                System.out.print("Enter Password: ");
                // sc.next();
                String inp = sc.next();
                if (inp.equals("User")) {
                    System.out.println("Switching User From Owner To User...");
                    mmsFactory.setUser();
                    break;
                } else {
                    System.out.println("Invalid Password!, Please Try Again");
                    System.out.print("Quit (Y/N): ");
                    String inpY = sc.next();
                    if (inpY.equals("Y")) {
                        break;
                    }
                }

            }

        } else {

            while (true) {
                System.out.println("UserName: Owner");
                System.out.println("------------------");
                System.out.print("Enter Password: ");
                // sc.next();
                String inp = sc.next();
                if (inp.equals("Owner")) {
                    System.out.println("Switching User From User To Owner...");
                    mmsFactory.setOwner();
                    break;
                } else {
                    System.out.println("Invalid Password!, Please Try Again");
                    System.out.print("Quit (Y/N): ");
                    String inpY = sc.next();
                    if (inpY.equals("Y")) {
                        break;
                    }
                }
            }
        }
    }

    public static void add_media() {
        if (!mmsFactory.CurrentUser()) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Error!..Only Owner Can Perform This Operation.");
            System.out.println("You Can Switch From Onwer To User To Perfrom This Task...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        int intInp;
        String strInp;

        Media md = new Media();

        System.out.println("Enter Media ID(int): ");
        intInp = sc.nextInt();
        md.setTid(intInp);

        System.out.println("Enter Media Title(string): ");
        strInp = sc.next();
        md.setTitle(strInp);

        System.out.println("Enter Media Release Date(string): ");
        strInp = sc.next();
        md.setReleaseDate(strInp);

        try {
            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();

            // MediaManagementSystem mms = MMS_Factory.getMMS();
            mms.add_MediaItem(md);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void update_media() {
        if (!mmsFactory.CurrentUser()) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Error!..Only Owner Can Perform This Operation.");
            System.out.println("You Can Switch From Onwer To User To Perfrom This Task...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        int intInp;

        System.out.println("Enter Media ID(int): ");
        intInp = sc.nextInt();

        try {
            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();

            mms.update_MediaItem(intInp);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void delete_media() {
        if (!mmsFactory.CurrentUser()) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Error!..Only Owner Can Perform This Operation.");
            System.out.println("You Can Switch From Onwer To User To Perfrom This Task...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        int intInp;

        System.out.println("Enter Media ID(int): ");
        intInp = sc.nextInt();

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.delete_MediaItem(intInp);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void search_Media() {
        System.out.print("Enter Id Of Media Your Want To Search.");
        int id = sc.nextInt();

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.searchMediaByTitle(id);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void change_language() {

        if (!mmsFactory.CurrentUser()) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Error!..Only Owner Can Perform This Operation.");
            System.out.println("You Can Switch From Onwer To User To Perfrom This Task...");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        int intInp;

        System.out.println("Enter Media ID(int): ");
        intInp = sc.nextInt();

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.change_DefaultLanguage(intInp);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void search_MediaByLang() {
        System.out.print("Enter Language Of Media Your Want To Search: ");
        String lang = sc.next();

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.filterMediaByLanguage(lang);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void add_rating() {
        int intInp;

        Rating rat = new Rating();

        System.out.println("Enter Media ID(int): ");
        intInp = sc.nextInt();
        rat.setTid(intInp);

        System.out.println("Enter User ID(int): ");
        intInp = sc.nextInt();
        rat.setUid(intInp);

        System.out.println("Enter Rating(int): ");
        intInp = sc.nextInt();
        rat.setRating(intInp);

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.addRating(rat);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

    public static void add_genres() {
        int intInp;
        String strInp;

        Genre gen = new Genre();

        System.out.println("Enter USer ID(int): ");
        intInp = sc.nextInt();
        gen.setUid(intInp);

        System.out.println("Enter Preferred Genre 1 (Out of Comedy,Thriller,Romance,Mystery,Adventure): ");
        strInp = sc.next();
        gen.setGenre1(strInp);

        System.out.println("Enter Preferred Genre 2 (Out of Comedy,Thriller,Romance,Mystery,Adventure): ");
        strInp = sc.next();
        gen.setGenre2(strInp);

        System.out.println("Enter Preferred Genre 3 (Out of Comedy,Thriller,Romance,Mystery,Adventure): ");
        strInp = sc.next();
        gen.setGenre3(strInp);

        try {

            mmsFactory.activateConnection();

            MediaManagementSystem mms = mmsFactory.getMMS();
            mms.addPreferredGenres(gen);
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.COMMIT);
        } catch (Exception e) {
            mmsFactory.deactivateConnection(MMS_Factory.TXN_STATUS.ROLLBACK);
            e.printStackTrace();
        }
    }

}
