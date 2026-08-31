package inheritance;
class Parent {

    static {
        System.out.println("Parent static");
    }
}

class Child extends Parent {

    static {
        System.out.println("Child static");
    }
}

public class inheritance1 {
    public static void main(String[] args) {
        Child c = new Child();
    }
}