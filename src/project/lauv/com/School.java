package project.lauv.com;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class School{
    private int budget = 0;
    private int moneyEarned;
    private int moneySpent;
    private static String name;
    List<String>listForReplacement=new ArrayList<String>();

    public School() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter school name:");
        name=scanner.nextLine();
        Path path = Paths.get("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\"+name+"\\");

        if (Files.notExists(path)){
            Files.createDirectories(path);
            System.out.println("Checking for school directory...");
            System.out.println("None found.");
            System.out.println("Creating new school directory!");
            File students = new File(path+"\\Students.txt");
            students.createNewFile();
            File teachers = new File(path+"\\Teachers.txt");
            teachers.createNewFile();
        }
        else if(Files.exists(path)){

            System.out.println("Checking for school directory...");
            System.out.println("Directory found!");

            File s = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt");
            if (s.exists()) {
                System.out.println("Student file location found by file reader");
            } else {
                System.out.println("Error: No student file found by filereader\nCreating a new student file!");
                s.createNewFile();
            }
            File t = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt");
            if (t.exists()) {
                System.out.println("Teacher file location found by file reader");
            } else {
                System.out.println("Error: No teacher file found by filereader\nCreating a new teacher file!");
                t.createNewFile();
            }
        }

    }

/***********************************************************************
 *       TEXT BASED MENUS
 **********************************************************************/

    /**
     * Text menu for the Main Menu.
     */
    public void menu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========Main Menu==========");
        System.out.println("1.Student Options");
        System.out.println("2.Teacher Options");
        System.out.println("3.Administration Options");
        System.out.println("===========================");
        System.out.println("Please make a selection");
        int selection = scanner.nextInt();
        if (selection>=4 || selection<=0 ){
            System.out.println("Invalid option.\nPlease try again!\n");
            menu();
        }
        else {
            switch (selection){
                case 1:
                    studentMenu();
                    break;
                case 2:
                    teacherMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
            }
        }
    }
    /**
     * Text menu for the Student Menu.
     */
    public void studentMenu() throws IOException {
        System.out.println("==========Student Menu==========");
        System.out.println("1.Add new student"); // WORKS
        System.out.println("2.Student list"); // WORKS
        System.out.println("3.View students info"); // WORKS
        System.out.println("4.Enroll Course"); // WORKS
        System.out.println("5.Pay Course"); // WORKS
        System.out.println("6.Remove Student");// WORKS
        System.out.println("=====Press Q to return to Main Menu=====");
        System.out.println("Please make a selection");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine().toUpperCase();
        if (selection.equals("Q")){
            menu();
        }
        else if(selection.equals("1")){
            new Student();
            System.out.println("Confirm save?Y/N");
            String a = scanner.nextLine().toUpperCase();
            if (a.equals("Y")){
                String token= Student.toStringr();
                saveStudent(token);
                menu();
            }
            else if(a.equals("N")){
                System.out.println("Canceled!");
                menu();
            }
        }
        else if(selection.equals("2")){
            readStudent();
            studentMenu();
        }
        else if(selection.equals("3")){
            searchID();
            System.out.println("Would you like to do anything else?");
            selection=scanner.nextLine().toUpperCase();
            if (selection.equals("Y")){studentMenu();}
            else if (selection.equals("N")){ studentMenu(); }
        }
        else if(selection.equals("4")){
            searchID();
            enrollStudent();
            studentMenu();
        }
        else if(selection.equals("5")){
            searchID();
            payCoursesStudent();
            studentMenu();
        }
        else if(selection.equals("6")){
            removeStudent();
        }

    }
    /**
     * Text menu for the Teacher Menu.
     */
    public void teacherMenu() throws IOException {
        System.out.println("========Teacher Menu==========");
        System.out.println("1.Add new Teacher");
        System.out.println("2.View own Info");
        System.out.println("3.View Student Info");
        System.out.println("4.View all Teacher Info");
        System.out.println("=====Press Q to return to Main Menu=====");
        System.out.println("Please make a selection");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine().toUpperCase();
        if (selection.equals("Q")){menu();}
        else {
            switch (selection){
                case "1":
                    new Teacher();
                    System.out.println("Would you like to save?");
                    selection=scanner.nextLine().toUpperCase();
                    switch (selection){
                        case"Y":
                            String token=Teacher.toStringr();
                            saveTeacher(token);
                            menu();
                            break;
                        case"N":
                            System.out.println("Canceled!");
                            menu();
                            break;
                    }
                    break;
                case "2":
                    searchNameTeach();
                    break;
                case "3":
                    System.out.println("Please enter Student name:");
                    searchNameStud();
                    break;
                case "4":
                    readTeachers();
                    break;

            }
        }

    }
    public void adminMenu() throws IOException {
        System.out.println("Please enter admin password:");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();
        if (selection.equals("adminpassword")) {
            System.out.println("=====Administration Menu======");
            System.out.println("1.View Teacher Info"); //OK
            System.out.println("2.Edit Teacher Info"); //NOT OK
            System.out.println("3.Remove Teacher"); //OK
            System.out.println("4.View Student Info");//OK
            System.out.println("5.Edit Student Info");
            System.out.println("6.Remove Student");
            System.out.println("7.Pay Teachers");
            System.out.println("=====Press Q to return to Main Menu=====");
            System.out.println("Please make a selection");
            selection = scanner.nextLine();
            if (selection.equals("Q")) {
                menu();
            }
            switch (selection){
                case"1":{
                    searchNameTeach();
                    break;
                }
                case"2":{
                    searchNameTeach();
                    editMenuTeach();
                }
                case"3":{
                    removeTeacher();
                }
                case"4": {
                    searchID();
                    System.out.println("Would you like to do anything else?");
                    selection = scanner.nextLine().toUpperCase();
                    if (selection.equals("Y")) {
                        adminMenu();
                    } else if (selection.equals("N")) {
                        adminMenu();
                    }
                }
                case"5": {
                    searchID();
                    editMenuStudent();
                }
                case"6":{}
                case"7":{}
            }
        }            else if (selection.equals("N")){
            System.out.println("Invalid password");
        }
    }
    public void editMenuStudent() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose what to edit: ");
            System.out.println("======================");
            System.out.println("1.Name:");
            System.out.println("2.Balance:");
            System.out.println("3.Grade");
            System.out.println("====Q for MainMenu====");
            String selection=scanner.nextLine();
            switch (selection) {
                case "1": {
                    Scanner scannerEdit = new Scanner(System.in);
                    System.out.println("Please enter first name:");
                    Student.setFirstName(scannerEdit.nextLine());
                    System.out.println("Please enter last name:");
                    Student.setLastName(scannerEdit.nextLine());
                    editMenuStudent();
                    break;
                }
                case "2": {
                    Scanner scannerEdit = new Scanner(System.in);
                    System.out.println("Please enter new Balance:");
                    Student.setStudentBalance(scannerEdit.nextInt());
                    editMenuStudent();
                    break;
                }
                case "3": {
                    Scanner scannerEdit = new Scanner(System.in);
                    System.out.println("Please enter new Grade:");
                    Student.setGrade(scannerEdit.nextInt());
                    editMenuStudent();
                    break;
                }
                case "Q":
                    break;
            }
            System.out.println("Info updated!");
            System.out.println("New info:");
            System.out.println(Student.toStringr());
            listForReplacement.add(Student.toStringr());
            System.out.println(listForReplacement.toString()+"This is the list ARRAY");
            replaceInfo(listForReplacement.get(0), listForReplacement.get(1));
            adminMenu();
        }
    public void editMenuTeach() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose what to edit: ");
        System.out.println("======================");
        System.out.println("1.Name:");
        System.out.println("2.Salary:");
        System.out.println("=====Q to MainMenu====");
        String selection = scanner.nextLine();
        if (selection.equals("1")) {
            Scanner scannerEdit = new Scanner(System.in);
            System.out.println("Please enter new name:");
            System.out.println("First name:");
            Teacher.setFirstName(scannerEdit.nextLine());
            System.out.println("Last name:");
            Teacher.setLastName(scannerEdit.nextLine());
            editMenuTeach();
        }
        else if (selection.equals("2")) {
            Scanner scannerEdit = new Scanner(System.in);
            System.out.println("Please enter new salary:");
            Teacher.setSalary(scannerEdit.nextDouble());
            editMenuTeach();
        }


        System.out.println("Info updated!");
        listForReplacement.add(Teacher.toStringr());
//            System.out.println(listForReplacement.get(0));
//            System.out.println(listForReplacement.get(1));
        replaceInfoTeacher(listForReplacement.get(0),listForReplacement.get(1));
    }



/***********************************************************************
 *      STUDENT METHODS
 **********************************************************************/

    public void saveStudent(String token){
        try (FileWriter fw = new FileWriter("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt",true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
        {
            out.println(token);
        }
        catch (IOException e ){
            System.out.println("Exepction E ");
        }
    }
    public void readStudent() throws FileNotFoundException {
        FileReader fr = new FileReader("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt");
        BufferedReader br = new BufferedReader(fr);
        try{
            String strCurrentLine;
            while((strCurrentLine = br.readLine())!=null){
                System.out.println(strCurrentLine);
            }
        }
        catch (IOException e){e.printStackTrace();}
        finally {
            try {
                br.close();
            }catch (IOException ex){ex.printStackTrace();}
        }
    }
    public void searchID(){
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt");
        System.out.println("Please enter ID:");
        String idCheck= scanner.nextLine();
        try{
            Scanner id = new Scanner(file);
            while(id.hasNextLine()){
                String line = id.nextLine();
                if (line.contains(idCheck)){
                    System.out.println("Valid ID number.");
                    System.out.println("Initializing student!");
                    String[] student=line.split("ID:|FirstName:|LastName:|Courses:|Grade:|Balance:");
                        //student[1]=ID
                        //student[2]=Name{Separate it in 2 substrings}
                        //student[4]=Courses
                        //student[5]=Grade
                        //student[6]=Balance
                   String idGen =student[1];
                   String firstNameGen = student[2];
                   String lastNameGen = student[3];
                   String coursGen = student[4];
                   int gradeGen =Integer.parseInt(student[5].replace(" ",""));
                   int balanceGen =Integer.parseInt(student[6].replace(" ",""));
                   new Student(idGen,lastNameGen,firstNameGen,coursGen,gradeGen,balanceGen);
                    listForReplacement.add(line);
                    return;
                }
                if (!id.hasNextLine()){
                    System.out.println("ID does not exist.");
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Invalid ID number entered.");
        }
    }
    public void enrollStudent() throws IOException {
        Student.enrollCourse();
        listForReplacement.add(Student.toStringr());
        replaceInfo(listForReplacement.get(0),listForReplacement.get(1));
    }
    public void payCoursesStudent() throws IOException {
        Student.payCourses();
        listForReplacement.add(Student.toStringr());
        replaceInfo(listForReplacement.get(0),listForReplacement.get(1));
    }
    public void replaceInfo(String oldString,String token1) throws IOException {
        String filePath = "C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt";
        Scanner scanner = new Scanner(new File(filePath));
        StringBuilder buffer = new StringBuilder();
        while (scanner.hasNextLine()){
            buffer.append(scanner.nextLine()).append(System.lineSeparator());
        }
        String fileContents = buffer.toString();
        scanner.close();
        String newLine = token1.replace("  ","");
        fileContents=fileContents.replaceAll(oldString,newLine);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
    public void searchNameStud(){
            Scanner scanner = new Scanner(System.in);
            File file = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt");
            System.out.println("Please enter Name/ID:");
            String idCheck= scanner.nextLine();
            try{
                Scanner id = new Scanner(file);
                while(id.hasNextLine()){
                    String line = id.nextLine();
                    if (line.contains(idCheck)){
                        System.out.println("Valid Name.");
                        System.out.println("Initializing student!");
                        String[] student=line.split("ID:|FirstName:|LastName:|Courses:|Grade:|Balance:");
                        //student[1]=ID
                        //student[2]=Name{Separate it in 2 substrings}
                        //student[4]=Courses
                        //student[5]=Grade
                        //student[6]=Balance
                        String idGen =student[1];
                        String firstNameGen = student[2];
                        String lastNameGen = student[3];
                        String coursGen = student[4];
                        int gradeGen =Integer.parseInt(student[5].replace(" ",""));
                        int balanceGen =Integer.parseInt(student[6].replace(" ",""));
                        new Student(idGen,lastNameGen,firstNameGen,coursGen,gradeGen,balanceGen);
                        listForReplacement.add(line);
                        return;
                    }
                    if (!id.hasNextLine()){
                        System.out.println("ID/Name does not exist.");
                    }
                }
            }catch (FileNotFoundException e){
                System.out.println("Invalid ID/Name entered.");
            }
     }
    public void removeStudent() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Students.txt");
        File tempFile = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\tempFileStudents.txt");
        System.out.println("Please enter Student ID/Name");
        String removeText = scanner.nextLine();
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNextLine()){
            String lineText=fileScanner.nextLine();
            if (lineText.contains(removeText)){
                removeText=lineText;
            }
        }
        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        while ((currentLine=reader.readLine())!=null){
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(removeText))continue;
            writer.write(currentLine + System.lineSeparator());
        }
        writer.close();
        reader.close();
        fileScanner.close();
        boolean fileDelete=file.delete();
        boolean fileRename=tempFile.renameTo(file);

    }

    /***********************************************************************
     *     TEACHER METHODS
     **********************************************************************/
    public void readTeachers() throws FileNotFoundException {
        FileReader fr = new FileReader("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt");
        BufferedReader br = new BufferedReader(fr);
        try{
            String strCurrentLine;
            while((strCurrentLine = br.readLine())!=null){
                System.out.println(strCurrentLine);
            }
        }
        catch (IOException e){e.printStackTrace();}
        finally {
            try {
                br.close();
            }catch (IOException ex){ex.printStackTrace();}
        }
    }
    public void searchNameTeach(){
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt");
        System.out.println("Please enter Name/ID:");
        String idCheck= scanner.nextLine();
        try{
            Scanner id = new Scanner(file);
            while(id.hasNextLine()){
                String line = id.nextLine();
                if (line.contains(idCheck)){
                    System.out.println("Valid Name.");
                    System.out.println("Initializing teacher!");
                    String[] teacher=line.split("ID:|LastName:|FirstName:|EmploymentDate:|Salary:");
                    System.out.println(Arrays.toString(teacher));
                    System.out.println(teacher[0]+"==teacher 0");
                    System.out.println(teacher[1]+"==teacher 1");
                    System.out.println(teacher[2]+"==teacher 2");
                    System.out.println(teacher[3]+"==teacher 3");
                    System.out.println(teacher[4]+"==teacher 4");

                    String idGen =teacher[1];
                    String firstNameGen = teacher[3];
                    String lastNameGen = teacher[2];
                    String dateOfEmploymentGen = teacher[4];
                    double salaryGen = Double.parseDouble(teacher[5]);
                    new Teacher(idGen,lastNameGen,firstNameGen,salaryGen,dateOfEmploymentGen);
//                    System.out.println("The new Teacher object is beeing initiated using the custom fields");
                    listForReplacement.add(line);
//                    System.out.println("Teacher object is transformed to string and is imported in the String list");
//                    System.out.println("This will be the object that will be replaced in the text file.");
                    System.out.println(line);
                    return;
                }
                if (!id.hasNextLine()){
                    System.out.println("ID/Name does not exist.");
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Invalid ID/Name entered.");
        }
    }
    public void saveTeacher(String token){
        try (FileWriter fw = new FileWriter("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt",true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
        {
            out.println(token);
        }
        catch (IOException e ){
            System.out.println("Exepction E ");
        }
    }
    public void replaceInfoTeacher(String oldString,String token1) throws IOException {
        String filePath = "C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt";
        Scanner scanner = new Scanner(new File(filePath));
        StringBuilder buffer = new StringBuilder();
        while (scanner.hasNextLine()){
            buffer.append(scanner.nextLine()).append(System.lineSeparator());
        }
        String fileContents = buffer.toString();
        scanner.close();
        String newLine = token1.replace("  ","");
        fileContents=fileContents.replaceAll(oldString,newLine);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
        System.out.println("Replacement Succesfull");
    }
    public void removeTeacher() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\Teachers.txt");
        File tempFile = new File("C:\\Users\\ilies\\IdeaProjects\\studentteacherproj\\" + School.getName() + "\\tempFileTeachers.txt");
        System.out.println("Please enter Teacher ID/Name");
        String removeText = scanner.nextLine();
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNextLine()){
            String lineText=fileScanner.nextLine();
            if (lineText.contains(removeText)){
                removeText=lineText;
            }
        }
        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        while ((currentLine=reader.readLine())!=null){
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(removeText))continue;
            writer.write(currentLine + System.lineSeparator());
        }
        writer.close();
        reader.close();
        fileScanner.close();
        boolean fileDelete=file.delete();
        boolean fileRename=tempFile.renameTo(file);

    }

    /***********************************************************************
     *       GETTERS AND SETTERS
     **********************************************************************/
    public static String getName(){return name;}





    }

