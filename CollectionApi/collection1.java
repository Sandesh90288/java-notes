package CollectionApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
    Collections.sort((ArrayList<Integer>) nums, comparator);
     
  }
}
