package CollectionApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
class Student implements Comparable<Student> {
    int age;
    String name;
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int compareTo(Student other) {
        if (this.age > other.age) {
            return 1;
        } else if (this.age < other.age) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class collection1 {
    // lets work with collection interface.Collection is a root interface in
    // collection framework which is present in java.util package and it has many
    // subinterfaces like list,set,queue etc. and these subinterfaces have many
    // implementing classes like arraylist,linkedlist,hashset,treeset etc.
  public static void main(String[] args) {
    Collection<Integer> nums= new ArrayList<Integer>();
    nums.add(10);
    nums.add(20);
    nums.add(30); 
    nums.add(90);
    nums.add(50);
    nums.add(40);
    System.out.println("Collection: " + nums); 
    for(Integer num: nums){
        System.out.println(num);
    }
    //you can also sort the collection directly using Collections.sort() method
    //Collections.sort((ArrayList<Integer>) nums);
    //comparator is an interface which is present in java.util package and it has a method compare() which is used to compare two objects. It is used to sort the collection in ascending or descending order.
    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        //if o1>o2 then return 1, if o1<o2 then return -1, if o1=o2 then return 0
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };
    Comparator<Student> Comparator = new Comparator<Student>() {
        @Override
        //if o1>o2 then return 1, if o1<o2 then return -1, if o1=o2 then return 0
        public int compare(Student o1, Student o2) {
            if(o1.age>o2.age){
                return 1;
            } else if(o1.age<o2.age){
                return -1;
            } else {
                return 0;
            }
        }
    };
    Collections.sort((ArrayList<Integer>) nums, comparator);
    System.out.println("Sorted Collection: " + nums);
   
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student(20, "John"));
    students.add(new Student(22, "Alice"));
    students.add(new Student(19, "Bob"));
    students.add(new Student(21, "David"));
    //u can aslo implement sort the object like this by creating comparator object and passing it to the sort method of Collections class.
    // Collections.sort(students, Comparator);
    // System.out.println("Sorted Students: ");
    // for (Student s: students) {
    //     System.out.println("Age: " + s.age + " Name: " + s.name);
    // }

    //Or by implementing Comparable interface in the Student class and overriding the compareTo() method.
    Collections.sort(students);
    System.out.println("Sorted Students: ");
    for (Student s: students) {
        System.out.println("Age: " + s.age + " Name: " + s.name);
    }
  }
}
