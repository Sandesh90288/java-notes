package StreamApi;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Random;
public class StreamApi3 {
    public static void main(String[] args) {
         //here will be dicussing about parallel stream in detail with example
         int size=10_000;
    Random random = new Random();
    random.nextInt(100); // this will generate a random number between 0 to 99
    ArrayList<Integer> nums1=new ArrayList<>(size);
    for(int i=0;i<size;i++){
        nums1.add(random.nextInt(100)); // this will generate a random number between 0 to 99
    }
    long startTime=System.currentTimeMillis();
    int sum1=nums1.stream().map(n->n*2).reduce(0,(a,b)->a+b);
    long endTime=System.currentTimeMillis();

    long start1Time=System.currentTimeMillis();
    //either u can do this 
    // int sum2=nums1.parallelStream().map(n->n*2).reduce(0,(a,b)->a+b);
    //or u can do this
    int sum2=nums1.stream().mapToInt(n->n*2).sum(); // this will give the sum of all the elements in the list after multiplying each element by 2
    long end1Time=System.currentTimeMillis();
    System.out.println("sum1-> " + sum1);
    System.out.println("sum2-> " + sum2);
    System.out.println("Time taken by normal stream: " + (endTime-startTime) + " ms");
    System.out.println("Time taken by parallel stream: " + (end1Time-start1Time) + " ms");
   }
}
