package StreamApi;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

//lets say i have a list of mixed number i need to get all odd numbers from the list and store it 
//map the odd numbers with its square and the store in a new list and print the list 
public class streamApi {
 public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    Stream<Integer> stream = list.stream();
    // List<Integer> oddNumbers=stream.filter(n->
    //     if(n%2==0){
    //         return n;
    //     }
    // ).map(n->n*n).forEach(n->return n);
    //the above commented code will not working reason is mentioned in myQuestion.md file.

    // List<Integer> oddNumbers = 
    stream.filter(n -> n % 2 != 0)
            .map(n -> n * n)
            .forEach(n-> System.out.println(n));
    // System.out.println(oddNumbers);
 }
}
