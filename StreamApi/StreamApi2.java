package StreamApi;

import java.util.ArrayList;
import java.util.stream.Stream;//remember this import

public class StreamApi2 {
public static void main(String[] args) {
     //i need to print the list of even numbers squares such that u are give unsorted list of numbers;
        ArrayList<Integer> nums1=new ArrayList<>();
        nums1.add(20);
        nums1.add(5);
        nums1.add(80);
        nums1.add(40);
        nums1.add(90);
        nums1.add(7);
        nums1.add(3);
        Stream<Integer> sortedStream=nums1.stream().filter(n->n%2==0)
                       .map(n->n*n).sorted(); // this will sort the stream of even numbers squares in ascending order.
        sortedStream.forEach(n->System.out.print(n+" ")); // this will print the

        //now u need to the sum of squares of odd numbers then u can does in this way
        System.out.println();
        int sumOfOddSquares=nums1.stream().filter(n->n%2!=0)
                       .map(n->n*n)
                       .reduce(0,(a,b)-> a+b);
                       System.out.println("sum of odd squares is: "+sumOfOddSquares);
        //now lets work with parallelstreams.when to use it 
        //lets say u have a list of 1000000 numbers and u want to find the sum of squares of 
        // odd numbers then u can use parallel stream to do it in parallel and it will be faster than normal stream.
        int sumOfOddSquaresParallel=nums1.parallelStream().filter(n->n%2!=0)
                       .map(n->n*n)
                       .reduce(0,(a,b)-> a+b);
                       System.out.println("sum of odd squares using parallel stream is: "+sumOfOddSquaresParallel);
        //lets work with parallel stream in streamapi3 in detail and see how it works and when to use it.

}
}
