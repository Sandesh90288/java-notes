# 1. i have one doubt this lists,set,queue are interface or actual class and does this all extends collection or implements it?
`List`, `Set`, and `Queue` are **interfaces**, not actual classes. They all extend the `Collection` interface.

```text
Iterable
   ↑
Collection
   ↑
├── List
├── Set
└── Queue
     ↑
    Deque
```

Because they are interfaces, Java uses `extends`:

```java
public interface List<E> extends Collection<E> { }
public interface Set<E> extends Collection<E> { }
public interface Queue<E> extends Collection<E> { }
```

Actual classes implement these interfaces:

```java
List<Integer> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Queue<Integer> queue = new LinkedList<>();
```

Here:

- `ArrayList` is a class that implements `List`
- `HashSet` is a class that implements `Set`
- `LinkedList` is a class that implements `List` and `Deque` (and therefore can be used as a `Queue`)

Remember the rule:

```text
Interface extends interface
Class implements interface
Class extends class
```

One important exception: `Map` is also an interface, but it does **not** extend `Collection`, because it stores key–value pairs rather than individual elements.

# 2. You will face one warning --Type safety: The method add(Object) belongs to the raw type Collection. References to generic type Collection\<E> should be parameterizedJava(16777747)..what is mean by this..what is mean by this

This warning means you used `Collection` without specifying the type of elements it should store.

For example:

```java
Collection c = new ArrayList(); // raw type
c.add("Hello");
c.add(10); // allowed, but risky
```

Here, `Collection` is a **raw type**. Java does not know whether it should contain `String`, `Integer`, or some other type. That is why it warns you.

Use a generic type instead:

```java
Collection<String> c = new ArrayList<>();

c.add("Hello");
c.add(10); // compile-time error
```

`<String>` tells Java: “This collection can contain only `String` objects.” This is called **type safety**, because Java catches wrong values before the program runs.

So change:

```java
Collection c
```

to something like:

```java
Collection<String> c
```

or:

```java
Collection<Integer> c
```

depending on what you want to store.