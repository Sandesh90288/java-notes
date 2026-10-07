package Learn_the_Basics.DataTypes;

public class DataType {
    public static void main(String[] args) {
        // Example of different data types
        int age = 25;
        double height = 5.9;
        char grade = 'a';
        boolean isStudent = true;

        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Grade: " + (int)grade);
        System.out.println("Is Student: " + isStudent);

        byte a = 10;
        byte b = 20;
        var result = a + b;
        System.out.println("Result: " + result);

        // byte xyz = 10;
        // xyz = xyz + 1; 
        // compiler error: cannot convert from int to byte   
        //reason: In Java, when you perform arithmetic operations on 
         // byte, short, or char types, they are promoted to int
         // before the operation. Therefore, the result of the expression `xyz + 1` is of type int, 
         // which cannot be directly assigned back to a byte variable without explicit casting.
        // System.out.println("Updated value of age: " + age); 
        // but

        byte xyz = 10;
        xyz++;
        System.out.println("Updated value of xyz: " + xyz);//this is possible because the 
        // increment operator (++) is a special case in Java. 
        // It can be used with byte, short, and char types without causing a type promotion to int.
        //  The increment operator directly modifies the value of the variable in place, 
        // so it doesn't require an explicit cast.
    }
}