package project.lauv.com;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Teacher {
    private static String lastName,firstName;
    private static int idT= 100;
    private static double salary;
    private static String id;
    private static String dateofEmployment;

    /***********************************************************************
     *       METHODS
     **********************************************************************/

    public Teacher(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter teacher last name:");
        lastName = scanner.nextLine();
        System.out.print("Please enter teacher first name:");
        firstName=scanner.nextLine();
        System.out.print("Please enter salary:");
        salary=scanner.nextInt();
        Date date =new Date();//Contains the current data value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        dateofEmployment=formatter.format(date);
        generateID();
        viewInfo();
    }
    public Teacher(String id,String lastName,String firstName,double salary,String dateofEmployment){
        Teacher.id = id;
        Teacher.lastName=lastName;
        Teacher.firstName=firstName;
        Teacher.salary=salary;
        Teacher.dateofEmployment=dateofEmployment;
        System.out.println("ID:"+id+"LastName:"+lastName+"FirstName:"+firstName+"EmploymentDate:"+dateofEmployment+"Salary:"+salary);

    }
    public void generateID(){
        int lastInt=100+lastName.length();
        int firstInt=200+firstName.length();
        ++idT;
        String cur = Integer.toOctalString(idT);
        id = cur+""+lastInt +""+ firstInt;
    }
    public void viewInfo(){
        System.out.println("======ID:"+id+"======");
        System.out.println("Teacher: "+lastName+" "+firstName);
        System.out.println("Teacher has a salary per month if: $"+salary);
        System.out.println("Date of employment: "+dateofEmployment);
    }
    public static String toStringr() {
        return "ID:"+id+"LastName:"+lastName+"FirstName:"+firstName+"EmploymentDate:"+dateofEmployment+"Salary:"+salary;
    }

    /***********************************************************************
     *       SETTERS
     **********************************************************************/

    public static void setLastName(String lastName) {
        Teacher.lastName = lastName;
    }
    public static void setFirstName(String firstName) {
        Teacher.firstName = firstName;
    }
    public static void setSalary(double salary) {
        Teacher.salary = salary;
    }
}
