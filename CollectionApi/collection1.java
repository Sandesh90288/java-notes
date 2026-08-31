package CollectionApi;
import java.util.ArrayList;
import java.util.Collection;

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
  }
}
