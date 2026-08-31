public class wrapperclasses {
    public static void main(String[] args) {
        Integer i = 10;
        Double d = 10.5;
        Character c = 'A';
        Boolean b = true;

        System.out.println("Integer: " + i);
        System.out.println("Double: " + d);
        System.out.println("Character: " + c);
        System.out.println("Boolean: " + b);
        System.out.println("type of integer"+ i.getClass().getName());
    }
}