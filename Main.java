import java.util.InputMismatchException;
import java.util.Scanner;
import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.Exception;

// Main Class
public class Main {
    public static void main(String[] args) throws Exception {
        menu();
    }

    public static Scanner myObj = new Scanner(System.in);
    static int response;

    public static void menu() throws Exception {
        System.out.println("Welcome To Hospital. Choose your valid Option: ");
        System.out.println("1: For Doctors");
        System.out.println("2: For Patients");
        System.out.println("3: For Admin");
        System.out.println("4: To Exit");
        // Added cases Menu
        try {
            int response = myObj.nextInt();
            switch (response) {
                case 1:
                    Doctor.main(null);
                    break;
                case 2:
                    Patient.main(null);
                    break;
                case 3:
                    Admin.main(null);
                    break;
                case 4:
                    System.out.println("Thanks For Choosing Us. See You Around");
                    break;
                default:
                    System.out.println("Please Enter a valid Choice");
                    break;
            }

        } catch (

        Exception e) {
            System.out.println("Please enter valid Options");
        }

    }
}

// Doctor Class
class Doctor {
    public static Scanner myObj = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1: Login as Doctor");
        System.out.println("2: Register as Doctor");
        try {
            switch (myObj.nextInt()) {
                case 1:
                    login();
                    break;
                case 2:
                    reg();
                    login();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please Enter a valid Choice");
        }
    }

    public static void login() throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("DoctorsDB.txt"));
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Your UserName: ");
        String uName = obj.nextLine();
        System.out.println("Enter Your Password: ");
        String pass = obj.nextLine();
        boolean found = false; // added this variable
        while (fileScan.hasNextLine()) {
            String input = fileScan.nextLine();
            String Username = input.substring(0, input.indexOf(' '));
            String Password = input.substring(input.indexOf(' ') + 1, input.length());

            if ((Username.equals(uName)) && (Password.equals(pass))) {
                found = true; // added this to set found
                System.out.println("Welcome " + Username.toUpperCase());
            }
        }
        if (found) {
            System.out.println(
                    "Welcome " + uName.substring(0, 1).toUpperCase() + uName.substring(1) + " Choose your Option");
            System.out.println("1: For All Doctors List");
            System.out.println("2: For All Booked appointments");
            System.out.println("3: For all Patients Info ");
            System.out.println("4: To Exit");
            int response = myObj.nextInt();
            switch (response) {
                case 1:
                    allDocs();
                    break;
                case 2:
                    // Booked Appointments
                    break;
                case 3:
                    // All patients Info
                    break;
                case 4:
                    // Exit
                    break;
                default:
                    break;
            }
        }
        if (!found) {
            System.out.println("User Not found");
        }
    }

    // Method to register Doctor
    public static void reg() {
        Scanner obj = new Scanner(System.in);
        System.out.println(
                "Enter Your Username. Note: Don't use spaces or symbols. It will be used as login. And its Case sensitive");
        String Uname = obj.nextLine();
        System.out
                .println("Enter Your Password. Note: Use easy password as currently we don't have any recovery option");

        String pass = obj.nextLine();

        String str = Uname + " " + pass + "\n";
        try {

            // attach a file to FileWriter
            FileWriter fw = new FileWriter("DoctorsDB.txt", true);

            // read each character from string and write
            // into FileWriter
            for (int i = 0; i < str.length(); i++)
                fw.write(str.charAt(i));

            System.out.println("Successfully written");

            // close the file
            fw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Method to view list of all Doctors
    public static void allDocs() {
        try {
            File myObj = new File("DoctorsList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

// Patient Class
class Patient {
    static Scanner myObj = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1: Login as Patient");
        System.out.println("2: Register as Patient");
        try {
            switch (myObj.nextInt()) {
                case 1:
                    login();
                    break;
                case 2:
                    reg();
                    login();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please Enter a valid Choice");
            System.out.print(e);
        }
    }

    public static void login() throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("PatientsDB.txt"));
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Your UserName: ");
        String uName = obj.nextLine();
        System.out.println("Enter Your Password: ");
        String pass = obj.nextLine();
        boolean found = false; // added this variable
        while (fileScan.hasNextLine()) {
            String input = fileScan.nextLine();
            String Username = input.substring(0, input.indexOf(' '));
            String Password = input.substring(input.indexOf(' ') + 1, input.length());

            if ((Username.equals(uName)) && (Password.equals(pass))) {
                found = true; // added this to set found
                System.out.println("Welcome " + Username.toUpperCase());
            }
        }
        if (found) {
            System.out.println(
                    "Welcome " + uName.substring(0, 1).toUpperCase() + uName.substring(1) + " Choose your Option");
            System.out.println("1: For All Doctors List");
            System.out.println("2: For All Booked appointments");
            System.out.println("3: For all Patients Info ");
            System.out.println("4: To Exit");
            int response = myObj.nextInt();
            switch (response) {
                case 1:
                    allDocs();
                    break;
                case 2:
                    // Booked Appointments
                    break;
                case 3:
                    // All patients Info
                    break;
                case 4:
                    // Exit
                    break;
                default:
                    break;
            }
        }
        if (!found) {
            System.out.println("User Not found");
        }
    }

    // Method to register Doctor
    public static void reg() {
        Scanner obj = new Scanner(System.in);
        System.out.println(
                "Enter Your Username. Note: Don't use spaces or symbols. It will be used as login. And its Case sensitive");
        String Uname = obj.nextLine();
        System.out
                .println("Enter Your Password. Note: Use easy password as currently we don't have any recovery option");

        String pass = obj.nextLine();

        String str = Uname + " " + pass + "\n";
        try {

            // attach a file to FileWriter
            FileWriter fw = new FileWriter("PatientsDB.txt", true);

            // read each character from string and write
            // into FileWriter
            for (int i = 0; i < str.length(); i++)
                fw.write(str.charAt(i));

            System.out.println("Successfully written");

            // close the file
            fw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Method to view list of all Patients
    public static void allDocs() {
        try {
            File myObj = new File("PatientsDB.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

// Admin Class
class Admin {
    public static void main(String[] args) {
        adminMenu();
    }

    public static Scanner myObj = new Scanner(System.in);
    static int response;

    static void adminMenu() {
        System.out.println("Welcome To Admin Panel. Choose your valid Option: ");
        System.out.println("1: View Registered Doctors: ");
        System.out.println("2: View Registered Patients");
        System.out.println("3: View Appointments");
        System.out.println("4: To Exit");
        // Added cases Menu
        try {
            int response = myObj.nextInt();
            switch (response) {
                case 1:
                    viewDoctors();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                    System.out.println("Going Back To main menu.");
                    Main.menu();
                    break;
                default:
                    System.out.println("Please Enter a valid Choice");
                    break;
            }

        } catch (

        Exception e) {
            System.out.println("Please enter valid Options");
        }

    }

    static void viewDoctors() {
        System.out.println("Viewing Doctors");
        try {
            File myObj = new File("DoctorsDB.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void viewPatients() {
        System.out.println("Viewing Patients");
        try {
            File myObj = new File("PatientsDB.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    static void viewAppointments() {
        System.out.print("Viewing Appointments");

    }
}