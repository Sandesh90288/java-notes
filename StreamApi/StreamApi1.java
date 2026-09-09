package StreamApi;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamApi1 {
    public static void main(String[] args) {
        ArrayList<Integer> nums= new ArrayList<Integer>();
        nums.add(10);
        nums.add(20);
        nums.add(30); 
        nums.add(90);
        nums.add(50);
        nums.add(40);
        //we can print the elements using for loop or using forEach() method of stream api. 
        // forEach() method is a terminal operation which is used to perform an action for each element of the stream.
        System.out.println("Collection: using normal forloop " + nums); 
        for(Integer num: nums){
            System.out.println(num);
        }
        System.out.println("Collection: using stream api " + nums);
        nums.stream().forEach(num -> System.out.println(num));
        // we can also use method reference to print the elements of the collection.
        System.out.println("Collection: using method reference " + nums);
        nums.forEach(System.out::println); // method reference

        // Stream<Integer> stream = nums.stream();//exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        // stream.forEach(n->System.out.println(n));
        // stream.forEach(System.out::println);
    
    }  

}
