public class inheritance {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.sound();
        dog.eat();
        // Animal cat = new Cat();
        Cat cat = new Cat();
        cat.sound();
        cat.eat();
        cat.x=2000;
        System.out.println(cat.x);
        Dog dog1 = new Dog();
        System.out.println(dog1.x);
    }
}

abstract class Animal {

    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
class Dog extends Animal {
    static int x=1000;
    //  private Dog() {
    //     System.out.println("Dog constructor");
    //  }

    @Override
    void sound() {
        System.out.println("Bark");
    }
}
class Cat extends Dog {
    @Override
    void sound() {
        System.out.println("Meow");
    }
}
