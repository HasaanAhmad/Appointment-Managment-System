import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.Exception;
import java.lang.Thread;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

// Main Class
public class Main {
    public static void main(String[] args) throws Exception {
        menu();
    }

    public static Scanner myObj = new Scanner(System.in);
    static int response;

    public static void menu() throws Exception {
        String months[] = {

                "Jan",
                "Feb",
                "Mar",
                "Apr",

                "May",
                "Jun",
                "Jul",
                "Aug",

                "Sep",
                "Oct",
                "Nov",
                "Dec"
        };

        Calendar calendar = Calendar.getInstance();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m"
                        + "            *** Welcome to Doctor Appointment System. Please Choose An appropriate Option. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Date: " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " "
                + calendar.get(Calendar.YEAR));

        System.out.println("\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("1: For Doctors");
        System.out.println("2: For Patients");
        System.out.println("3: For Admin");
        System.out.println("4: To Exit");
        // Added cases Menu
        while (true) {
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
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(
                                "\n--------------------------------------------------------------------------------");
                        System.out.println(
                                "\u001B[41m"
                                        + "            *** Thanks for choosing us. Your feedback is all we need! ***"
                                        + "\u001B[0m");
                        System.out.println(
                                "--------------------------------------------------------------------------------");
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.exit(response);
                        break;
                    default:
                        System.out.println("Please Enter a valid Choice");
                        Thread.sleep(1000);
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        myObj.nextLine();
                        main(null);
                        break;
                }

            } catch (Exception e) {
                System.out.println("Please enter valid Options");
                Thread.sleep(1000);
                System.out.println("Yes its me");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                myObj.nextLine();
                Main.menu();
            }
        }

    }
}

// Doctor Class
class Doctor {
    public static Scanner myObj = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m" + "            *** Welcome Doctor. Please Choose An appropriate Option. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("1: Login as Doctor");
        System.out.println("2: Register as Doctor");
        System.out.println("3: Back To Main Menu");
        while (true) {
            try {
                switch (myObj.nextInt()) {
                    case 1:
                        login();
                        break;
                    case 2:
                        reg();
                        login();
                        break;
                    case 3:
                        Main.main(args);
                        break;
                    default:
                        System.out.println("Please Enter a valid Choice");
                        Thread.sleep(1000);
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        Doctor.main(null);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please Enter a valid Choice");
                Thread.sleep(1000);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                myObj.nextLine();
                Doctor.main(null);
            }
        }

    }

    public static void login() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m" + "            *** Welcome to Login. Please Enter Correct Credentials. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();

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
            }
        }
        if (found) {
            doctorLoginMenu(uName);
        }
        if (!found) {
            System.out.println("User Not found");
            Thread.sleep(1000);
            main(null);
        }
    }

    public static void doctorLoginMenu(String uName) throws Exception {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m"
                        + "            *** Welcome to Doctor's Menu. Please Choose An appropriate Option. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
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
                Thread.sleep(1000);
                doctorLoginMenu(uName);
                break;
            case 2:
                bookedAppointments(uName);
                Thread.sleep(1000);
                doctorLoginMenu(uName); // Booked Appointments
                break;
            case 3:
                Patient.allPatients();
                Thread.sleep(1000);
                doctorLoginMenu(uName);

                break;
            case 4:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                Main.main(null);
                break;
            default:
                break;
        }
    }

    // Validate User
    public static boolean validate(String Name) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("DoctorsDB.txt"));
        boolean found = false; // added this variable
        while (fileScan.hasNextLine()) {
            String input = fileScan.nextLine();
            String Username = input.substring(0, input.indexOf(' '));
            Username = Username.toUpperCase();
            Name = Name.toUpperCase();

            if (Username == "") {
                break;
            }

            else if ((Username.equals(Name))) {
                found = true; // added this to set found
            }
        }
        if (found) {
            System.out.println("User already Registered. Please try with some other Username.Thanks");
            reg();

        }
        if (!found) {
            System.out.print("Registering User \n");
        }
        return found;
    }

    // method for validating Strings from Doctors
    public static boolean stringValid(String input) {
        return Pattern.matches("[a-zA-Z\s]+", input);
    }

    // method for validating numbers from Doctors
    public static boolean numValid(String input) {
        try {

            int n = Integer.parseInt(input);
            if (n > 0 && n < 100)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }

    }

    // Method to register Doctor

    public static void reg() throws FileNotFoundException {
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter your name:");
        String fullName = obj.nextLine();
        while (!stringValid(fullName) || fullName == "") {
            System.out.println("Enter valid name please");
            fullName = obj.nextLine();
        }

        System.out.println("Enter your Age:");
        String Age = obj.nextLine();
        while (!numValid(Age) || Age == "") {
            System.out.println("Enter valid age");
            Age = obj.nextLine();
        }

        System.out.println("Enter your Designation:");
        String Designation = obj.nextLine();
        while (!stringValid(Designation) || Designation == "") {
            System.out.println("Enter valid Designation please");
            Designation = obj.nextLine();
        }

        System.out.println("Enter your Specialization:");
        String Specialization = obj.nextLine();
        while (!stringValid(Specialization) || Specialization == "") {
            System.out.println("Enter valid Specialization please");
            Specialization = obj.nextLine();
        }

        System.out.println();
        System.out.println(
                "Choose Your Username. Note: Don't use spaces or symbols. It will be used as login. And its Case sensitive");
        String Uname = obj.nextLine();
        System.out
                .println("Enter Your Password. Note: Use easy password as currently we don't have any recovery option");
        String pass = obj.nextLine();

        String strForDb = Uname + " " + pass + "\n";
        String strForList = fullName.replace(" ", "_") + " " + Age + " " + Designation + " " + Specialization + "\n";

        if (!validate(Uname)) {
            try {
                try (FileWriter fwForList = new FileWriter("DoctorsList.txt", true)) {
                    // attach a file to FileWriter
                    FileWriter fw = new FileWriter("DoctorsDB.txt", true);

                    // read each character from string and write
                    // into FileWriter

                    for (int i = 0; i < strForDb.length(); i++)
                        fw.write(strForDb.charAt(i));
                    for (int i = 0; i < strForList.length(); i++)
                        fwForList.write(strForList.charAt(i));

                    System.out.println("Successfully Registered. Now Please Use Right Information to login.");
                    // close the file
                    fw.close();
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.getStackTrace();
            }

        }
    }

    static void bookedAppointments(String searched) {
        String filePath = "Appointments.txt";

        // Replace "search_string" with the string you want to search for
        String searchString = searched;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // If the line contains the search string, print it
                if (line.contains(searchString)) {
                    System.out.println(line);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view list of all Doctors
    public static void allDocs() {
        try {
            File myObj = new File("DoctorsList.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                String[] name = data.split(" ");
                System.out.println();
                System.out.println();
                for (String i : name) {
                    // System.out.println();
                    System.out.print(String.format("%20s", i));
                }
            }
            System.out.println();
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
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

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m"
                        + "            *** Welcome to Patient's Portal. Please Choose An appropriate Option. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("1: Login as Patient");
        System.out.println("2: Register as Patient");
        System.out.println("3: Back to Main Menu");
        try {
            switch (myObj.nextInt()) {
                case 1:
                    login();
                    break;
                case 2:
                    reg();
                    login();
                    break;
                case 3:
                    Main.main(args);
                    break;
                default:
                    System.out.println("Please Enter a valid Choice");
                    Thread.sleep(1000);
                    Patient.main(null);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please Enter a valid Choice");
            Thread.sleep(1000);
            myObj.nextLine();
            Patient.main(null);
        }
    }

    public static void login() throws Exception {
        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m"
                        + "            *** Please Login With Correct Credentials. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
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
            }
        }
        if (found) {
            patientMenu(uName);
        }
        if (!found) {
            System.out.println("User Not found");
            Thread.sleep(1000);
            main(null);

        }
    }

    public static void patientMenu(String uName) throws Exception {

        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m"
                        + "            *** Welcome To Patient's Menu. Please Choose Appropriate Options. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println(
                "Welcome " + uName.substring(0, 1).toUpperCase() + uName.substring(1) + " Choose your Option");
        System.out.println("1: Book An Appointment");
        System.out.println("2: Cancel Your Appointment ");
        System.out.println("3: To Exit");
        int response = myObj.nextInt();
        switch (response) {
            case 1:
                bookAppointment(uName);
                patientMenu(uName);
                break;
            case 2:
                // cancel Appointment
                break;
            case 3:
                Main.main(null);
                break;
            default:
                break;
        }
    }

    // Booking Appointment
    public static void bookAppointment(String Name) {
        System.out.println("1: View Registered Doctors");
        System.out.println("2: Select Doctor and Date of Appointment ");
        System.out.println("3: To Exit");
        while (true) {

            try {
                int response = myObj.nextInt();
                switch (response) {
                    case 1:
                        Doctor.allDocs();
                        bookAppointment(Name);
                        break;
                    case 2:
                        appointment(Name);
                        patientMenu(Name);
                        break;
                    case 3:

                    default:
                        System.out.println("Please Enter a valid Choice");
                        break;
                }

            } catch (

            Exception e) {
                System.out.println("Please enter valid Options");
                myObj.nextLine();
            }
        }
    }

    public static void appointment(String Name) {
        Scanner obj = new Scanner(System.in);
        System.out.println(
                "Enter the name of the doctor whose appointment you want");
        String doctor = obj.nextLine();

        System.out.println(
                "Enter the date in the following format DD/MM/YYYY");

        String date = obj.nextLine();
        while (!(validateJavaDate(date))) {
            System.out.println("Please enter Valid Date in the following format DD/MM/YYYY");
            date = obj.nextLine();
        }
        System.out.println(
                "1: For 9:00Am \n2: For 9:30Am\n3: For 10:00Am\n4: For 10:30Am\n5: For 11:00Am\n6: For 11:30Am\n7: For 12:00Pm");

        // For picking the time

        String time = "";
        boolean ifTrue = true;
        String response;

        while (ifTrue) {
            try {
                response = myObj.nextLine();
                switch (response) {
                    case "1":
                        time = "9:00 AM";
                        ifTrue = false;
                        break;
                    case "2":
                        time = "9:30 AM";
                        ifTrue = false;
                        break;
                    case "3":
                        time = "10:00 AM";
                        ifTrue = false;
                        break;
                    case "4":
                        time = "10:30 AM";
                        ifTrue = false;
                        break;
                    case "5":
                        time = "11:00 AM";
                        ifTrue = false;
                        break;
                    case "6":
                        time = "11:30 AM";
                        ifTrue = false;
                        break;
                    case "7":
                        time = "11:00 AM";
                        ifTrue = false;
                        break;
                    default:
                        System.out.println("Please Enter a valid Choice");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter valid choice.");
                response = myObj.nextLine();
                break;
            }
        }
        System.out.println("Booked at " + time);

        System.out.println("Enter Card for Payment");
        getCCandReg(Name, doctor, date, time);

    }

    public static long getCCandReg(String Name, String doctor, String date, String time) {
        long CreditCard = -1;
        while (true) {
            try {
                CreditCard = myObj.nextLong();
                break;
            } catch (Exception e) {
                System.out.println("Credit Card Not Valid. (XXXXXXXXXXXXXXX) Please Do not use dashes or spaces.");
                myObj.nextLine();
                getCCandReg(Name, doctor, date, time);
            }
        }

        while (true) {
            if (validCc(CreditCard)) {

                String str = Name + " " + doctor.replace(" ", "_") + " " + date + " " + time + "\n";
                try {

                    // attach a file to FileWriter
                    FileWriter fw = new FileWriter("Appointments.txt", true);

                    // read each character from string and write
                    // into FileWriter

                    for (int i = 0; i < str.length(); i++)
                        fw.write(str.charAt(i));

                    System.out.println("Successfully Booked");
                    Thread.sleep(1000);
                    // close the file
                    fw.close();
                    Patient.main(null);
                    break;
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("Credit Card Not Valid. (XXXXXXXXXXXXXXX) Please Do not use dashes or spaces.");
                getCCandReg(Name, doctor, date, time);
            }
        }
        return CreditCard;
    }

    // Date Validation
    public static boolean validateJavaDate(String strDate) {
        /* Check if date is 'null' */
        if (strDate.trim().equals("")) {
            return true;
        }
        /* Date is not 'null' */
        else {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
             */
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            /*
             * Create Date object
             * parse the string into date
             */
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e) {
                System.out.println(strDate + " is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    // Credit Card Validation
    // Return true if the card number is valid
    public static boolean validCc(long cNumber) {
        return (theSize(cNumber) >= 13 && theSize(cNumber) <= 16) && (prefixMatch(cNumber, 4)
                || prefixMatch(cNumber, 5) || prefixMatch(cNumber, 37) || prefixMatch(cNumber, 6))
                && ((sumDoubleEven(cNumber) + sumOdd(cNumber)) % 10 == 0);
    }

    // Get the result from Step 2
    public static int sumDoubleEven(long cNumber) {
        int sum = 0;
        String num = cNumber + "";
        for (int i = theSize(cNumber) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        return sum;
    }

    // Return this cNumber if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int cNumber) {
        if (cNumber < 9)
            return cNumber;
        return cNumber / 10 + cNumber % 10;
    }

    // Return sum of odd-place digits in cNumber
    public static int sumOdd(long cNumber) {
        int sum = 0;
        String num = cNumber + "";
        for (int i = theSize(cNumber) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for cNumber
    public static boolean prefixMatch(long cNumber, int d) {
        return getPrefix(cNumber, theSize(d)) == d;
    }

    // Return the number of digits in d
    public static int theSize(long d) {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long cNumber, int k) {
        if (theSize(cNumber) > k) {
            String num = cNumber + "";
            return Long.parseLong(num.substring(0, k));
        }
        return cNumber;
    }

    // method for validting Strings from Patients
    public static boolean stringValid(String input) {
        return Pattern.matches("[a-zA-Z\s]+", input);
    }

    // method for validating age from Patients
    public static boolean numValid(String input) {
        try {
            int n = Integer.parseInt(input);
            if (n > 0 && n < 100)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    // Method for validating contact number
    public static boolean contactValid(String input) {

        try {
            Pattern p = Pattern.compile("^\\d{10}$");
            Matcher m = p.matcher(input);
            return (m.matches());

        } catch (Exception e) {
            return false;
        }
    }

    // Method to register Patient
    public static void reg() throws FileNotFoundException {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter your name:");
        String fullName = obj.nextLine();
        while (!stringValid(fullName) || fullName == "") {
            System.out.println("Enter valid name please");
            fullName = obj.nextLine();
        }
        System.out.println("Enter your Age:");
        String Age = obj.nextLine();
        while (!numValid(Age) || Age == "") {
            System.out.println("Enter valid age");
            Age = obj.nextLine();
        }
        System.out.println("Enter your gender:");
        String gender = obj.nextLine();
        while (!stringValid(gender) || gender == "") {
            System.out.println("Enter valid gender please");
            gender = obj.nextLine();
        }
        System.out.println("Enter your Contact:");
        String Contact = obj.nextLine();
        while (!contactValid(Contact) || Contact == "") {
            System.out.println("Enter valid contact number of containing 10 digits: ");
            Contact = obj.next();
        }

        System.out.println();

        System.out.println(
                "Choose Your Username. Note: Don't use spaces or symbols. It will be used as login. And its Case sensitive");
        String Uname = obj.nextLine();

        System.out
                .println("Enter Your Password. Note: Use easy password as currently we don't have any recovery option");
        String pass = obj.nextLine();

        String strForDb = Uname + " " + pass + "\n";
        String strForList = fullName.replace(" ", "_") + " " + Age + " " + gender + " " + Contact + "\n";

        if (!validate(Uname)) {
            try {
                try (FileWriter fwForList = new FileWriter("PatientsList.txt", true)) {
                    // attach a file to FileWriter
                    FileWriter fw = new FileWriter("PatientsDB.txt", true);

                    // read each character from string and write
                    // into FileWriter

                    for (int i = 0; i < strForDb.length(); i++)
                        fw.write(strForDb.charAt(i));
                    for (int i = 0; i < strForList.length(); i++)
                        fwForList.write(strForList.charAt(i));

                    System.out.println("Successfully written");
                    // close the file
                    Thread.sleep(1000);
                    fw.close();

                }

            } catch (Exception e) {
                e.getStackTrace();

            }

        }
    }

    // Method to register Patient
    public static boolean validate(String Name) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("PatientDB.txt"));
        boolean found = false; // added this variable
        while (fileScan.hasNextLine()) {
            String input = fileScan.nextLine();
            String Username = input.substring(0, input.indexOf(' '));
            Username = Username.toUpperCase();
            Name = Name.toUpperCase();

            if (Username == "") {
                break;
            }

            else if ((Username.equals(Name))) {
                found = true; // added this to set found
            }
        }
        if (found) {
            System.out.println("User already Registered. Please try with some other Username.Thanks");
            reg();

        }
        if (!found) {
            System.out.print("Registering User \n");
        }
        return found;
    }

    // Method to view list of all Patients
    public static void allPatients() {
        try {
            File myObj = new File("PatientsList.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                String[] name = data.split(" ");
                System.out.println();
                System.out.println();
                for (String i : name) {
                    // System.out.println();
                    System.out.print(String.format("%20s", i));
                }
            }
            System.out.println();
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

// Admin Class
class Admin {
    public static void main(String[] args) throws Exception {
        login();
    }

    public static void login() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(
                "\u001B[41m" + "            *** Welcome Admin. Please Login with your credentials. ***"
                        + "\u001B[0m");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Your UserName: ");
        String uName = obj.nextLine();
        System.out.println("Enter Your Password: ");
        String pass = obj.nextLine();
        boolean found = false; // added this variable

        if ((uName.equals("admin")) && (pass.equals("admin"))) {
            found = true; // added this to set found
        }
        if (found) {
            adminMenu();
        }
        if (!found) {
            System.out.println("User Not found");
            System.out.println("Try Again");
            Thread.sleep(1000);
            Main.main(null);
        }
    }

    public static Scanner myObj = new Scanner(System.in);
    static int response;

    static void adminMenu() throws Exception {

        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("        Welcome Admin.Please Choose appropriate Option.");
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("1: View Registered Doctors: ");
        System.out.println("2: View Registered Patients");
        System.out.println("3: View Appointments");
        System.out.println("4: Remove Doctors");
        System.out.println("5: Remove Patients");
        System.out.println("6: Remove Appointments");
        System.out.println("7: Go to Main Menu");

        // Added cases Menu
        try {
            int response = myObj.nextInt();
            switch (response) {
                case 1:
                    Doctor.allDocs();// View Registered Doctors
                    adminMenu();
                    break;
                case 2:
                    Patient.allPatients();// View Registered Patients
                    adminMenu();
                    break;
                case 3:
                    viewAppointments();// View Booked Appointments
                    adminMenu();
                    break;
                case 4:
                    Scanner newObj = new Scanner(System.in);
                    System.out.println("Enter the name of the doctor you want to remove");
                    String doctor = newObj.nextLine();
                    remove(doctor);
                    remove1(doctor);
                    adminMenu();
                    break;
                case 5:
                    System.out.println("Enter the name of the Patient you want to remove");
                    myObj.next();
                    String patient = myObj.nextLine();
                    try {
                        removePatient(patient);
                        removePatientsList(patient);
                    } catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                    adminMenu();
                    break;
                case 6:
                    System.out.println("Enter the Appointment info you want to remove");
                    myObj.next();
                    String appointmentInfo = myObj.nextLine();
                    try {
                        removeAppointment(appointmentInfo);
                    } catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                    adminMenu();
                    break;

                case 7:
                    System.out.println("Going Back To main menu.");
                    Main.menu();
                    break;
                default:
                    System.out.println("Please Enter a valid Choice");
                    Thread.sleep(1000);
                    adminMenu();
                    break;
            }
        } catch (

        Exception e) {
            System.out.println("Please enter valid Options");
            Thread.sleep(1000);
            myObj.next();
            adminMenu();
        }
    }

    static void remove(String lineContent) {
        File originalFile = new File("DoctorsDB.txt");
        File tempFile = new File("tempDoctorsDB.txt");

        // Open the original text file and the temporary text file
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read the contents of the original text file and remove the lines that contain
            // the specific string
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineContent)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close both the original text file and the temporary text file

        // Delete the original text file using the delete() method
        if (originalFile.delete()) {
            System.out.println("Original file deleted successfully");
        } else {
            System.out.println("Failed to delete the original file");
        }

        // Rename the temporary text file to the name of the original text file using
        // the renameTo() method
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Temp file renamed successfully");
        } else {
            System.out.println("Failed to rename the temp file");
        }
    }

    static void remove1(String lineContent) {
        File originalFile = new File("DoctorsList.txt");
        File tempFile = new File("tempDoctorsListDB.txt");

        // Open the original text file and the temporary text file
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read the contents of the original text file and remove the lines that contain
            // the specific string
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineContent)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close both the original text file and the temporary text file

        // Delete the original text file using the delete() method
        if (originalFile.delete()) {
            System.out.println("Original file deleted successfully");
        } else {
            System.out.println("Failed to delete the original file");
        }

        // Rename the temporary text file to the name of the original text file using
        // the renameTo() method
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Temp file renamed successfully");
        } else {
            System.out.println("Failed to rename the temp file");
        }
    }

    public static void removePatient(String lineContent) throws IOException {
        File originalFile = new File("PatientsDB.txt");
        File tempFile = new File("Patientstemp.txt");

        // Open the original text file and the temporary text file
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read the contents of the original text file and remove the specific string
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineContent)) {
                    writer.write(line);
                } else {
                    System.out.println("Record not found.");
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close both the original text file and the temporary text file

        // Delete the original text file using the delete() method
        if (originalFile.delete()) {
            // System.out.println("Original file deleted successfully");
        } else {
            // System.out.println("Failed to delete the original file");
        }

        // Rename the temporary text file to the name of the original text file using
        // the renameTo() method
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Removed Successfully");
        } else {
            // System.out.println("Failed to rename the temp file");
        }
    }

    public static void removePatientsList(String lineContent) throws IOException {
        File originalFile = new File("PatientsList.txt");
        File tempFile = new File("PatientsListtemp.txt");

        // Open the original text file and the temporary text file
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read the contents of the original text file and remove the specific string
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineContent)) {
                    writer.write(line);
                } else {
                    System.out.println("Record not found.");
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close both the original text file and the temporary text file

        // Delete the original text file using the delete() method
        if (originalFile.delete()) {
            // System.out.println("Original file deleted successfully");
        } else {
            // System.out.println("Failed to delete the original file");
        }

        // Rename the temporary text file to the name of the original text file using
        // the renameTo() method
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Removed Successfully");
        } else {
            // System.out.println("Failed to rename the temp file");
        }
    }

    public static void removeAppointment(String lineContent) throws IOException {
        File originalFile = new File("Appointments.txt");
        File tempFile = new File("Appointmentstemp.txt");

        // Open the original text file and the temporary text file
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read the contents of the original text file and remove the specific string
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineContent)) {
                    writer.write(line);
                } else {
                    System.out.println("Record not found.");
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close both the original text file and the temporary text file

        // Delete the original text file using the delete() method
        if (originalFile.delete()) {
            // System.out.println("Original file deleted successfully");
        } else {
            // System.out.println("Failed to delete the original file");
        }

        // Rename the temporary text file to the name of the original text file using
        // the renameTo() method
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Removed Successfully");
        } else {
            // System.out.println("Failed to rename the temp file");
        }
    }

    static void viewAppointments() {
        try {
            File myObj = new File("Appointments.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                String[] name = data.split(" ");
                System.out.println();
                System.out.println();
                for (String i : name) {
                    // System.out.println();
                    System.out.print(String.format("%20s", i));
                }
            }
            System.out.println();
            System.out.println(
                    "______________________________________________________________________________________________");
            System.out.println(
                    "______________________________________________________________________________________________");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
