package project.lauv.com;

import java.time.LocalDateTime;
import java.util.Scanner;

public  class Student{
        private static String firstName;
        private static String lastName;
        private static String courses="";
        private static int studentBalance=0;
        private static int grade;
        private static int id = 1000;
        private static String studentID;
/***********************************************************************
 *       CONSTRUCTORS
 **********************************************************************/

    /**
         * Constructor:Promt user to enter student full name and grade;
          */
     public Student(){
         Scanner scanner = new Scanner(System.in);
         System.out.print("Please enter student first name:");
         firstName = scanner.nextLine();
         System.out.print("Please enter student last name:");
         lastName = scanner.nextLine();
         System.out.println("Choose one of the following options :\n------------------------\n  1.First class.\n  2.Second class\n  " +
                 "3.Third class\n------------------------\n");
         grade = scanner.nextInt();
         idGenerator();
         viewInfo();
     }
    /**
     * Custom constructor for initializing already existing student
     */
     public Student(String studentID,String lastName,String firstName,String courses,int grade,int studentBalance){
         System.out.println("Student file has been accesed");
         Student.studentID =studentID;
         Student.lastName =lastName;
         Student.firstName =firstName;
         Student.courses =courses;
         Student.grade =grade;
         Student.studentBalance =studentBalance;
         System.out.println("ID:"+studentID+" LastName:"+lastName+" First Name:"+firstName+" Courses:"+courses+" Grade:"+grade+" Balance:"+studentBalance);
     }

/***********************************************************************
 *       METHODS
 **********************************************************************/

     /**
        *   This method generates a student id based on the grade and general id field.
        */
    public void idGenerator(){
        String currTime = LocalDateTime.now().getHour()+""+LocalDateTime.now().getMinute();
        int currYear = LocalDateTime.now().getYear()+LocalDateTime.now().getHour()+LocalDateTime.now().getDayOfMonth()+LocalDateTime.now().getMinute()+LocalDateTime.now().getSecond();
         studentID =grade+""+currTime+currYear;
     }
     /**
        *   User gets inside a loop.The user picks his classes and when done , the press of "Q" takes him out of the loop.
        */
    public static void enrollCourse(){
        do{
         System.out.println("-----------Class options:----------\n1.Math 101\n2.Science 101\n" +
                 "3.History 101\n4.English 101\n5.Bio 101\n-------Press Q to quit-------\n");
         Scanner scanner = new Scanner(System.in);
         String selection = scanner.nextLine();
         selection=selection.replace(" ","").toUpperCase();
         if (!selection.equals("Q")) {
             if(courses==""){
                 courses=selection;
             } else {courses+=selection+",";}
             int costPerCourse = 600;
             studentBalance = studentBalance + costPerCourse;
             }
         else {break;}
         } while (1 == 1);
        System.out.println("Enrolled in :\n-----------------------" +courses+"\n-----------------------\n");
     }
     /**
     *      Displays the ammount to be paid by the student.
     */
    public static void viewBalance(){System.out.println("The remaining balance is: "+ studentBalance+"$");
    }
     /**
     *      The method asks the user the ammount to be paid , then updates the balance with the difference.
     */
    public static void payCourses(){

        Scanner scanner=new Scanner(System.in);
        viewBalance();
        System.out.print("Please enter ammount to be paid:");
        int a=scanner.nextInt();
        studentBalance-=a;
        if(studentBalance<0){
            System.out.println("You have overpaid.\nTranzaction canceled!\nPlease try again!");
            studentBalance+=a;
        }
            else{
            System.out.println("Thank you for your payment!");
        }
        viewBalance();
    }
     /**
     * Print all info of the student
     */
    public void viewInfo(){
        System.out.println("======ID:"+studentID+"======");
        System.out.println("Student: "+lastName+" "+firstName);
        System.out.println("Student is enrolled in: "+courses);
        System.out.println("Student is in the "+grade +" grade.");
    }
    public static String toStringr() {
        return "ID:"+studentID+" LastName:"+lastName+" FirstName:"+firstName+" Courses:"+courses+" Grade:"+grade+" Balance:"+studentBalance;
    }

    /***********************************************************************
     *       SETTERS
     **********************************************************************/

    public static void setFirstName(String firstName) {
        Student.firstName = firstName;
    }
    public static void setLastName(String lastName) {
        Student.lastName = lastName;
    }
    public static void setCourses(String courses) {
        Student.courses = courses;
    }
    public static void setStudentBalance(int studentBalance) {
        Student.studentBalance = studentBalance;
    }
    public static void setGrade(int grade) {
        Student.grade = grade;
    }
}
