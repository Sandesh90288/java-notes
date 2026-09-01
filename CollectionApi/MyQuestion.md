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

# 3. just give me most frequently used methods for each collection

### List — `ArrayList`, `LinkedList`

```java
list.add("A");          // add element
list.get(0);            // get by index
list.set(0, "B");       // replace by index
list.remove(0);         // remove by index
list.remove("A");       // remove by value
list.contains("A");     // check existence
list.size();            // number of elements
list.isEmpty();         // check empty
list.clear();           // remove all
list.indexOf("A");      // find index
```

### Set — `HashSet`, `LinkedHashSet`, `TreeSet`

```java
set.add("A");           // add unique element
set.remove("A");        // remove element
set.contains("A");      // check existence
set.size();             // number of elements
set.isEmpty();          // check empty
set.clear();            // remove all
```

A `Set` does not support `get(index)` because it is not index-based.

### Map — `HashMap`, `LinkedHashMap`, `TreeMap`

```java
map.put(1, "Aman");             // add/update key-value pair
map.get(1);                     // get value using key
map.remove(1);                  // remove using key
map.containsKey(1);             // check key
map.containsValue("Aman");      // check value
map.size();                     // number of pairs
map.isEmpty();                  // check empty
map.clear();                    // remove all
map.keySet();                   // get all keys
map.values();                   // get all values
map.entrySet();                 // get all key-value pairs
```

### Queue — `PriorityQueue`, `ArrayDeque`

```java
queue.offer(10);        // add element
queue.poll();           // remove and return front element
queue.peek();           // view front element, do not remove
queue.isEmpty();        // check empty
queue.size();           // number of elements
queue.clear();          // remove all
```

For a `Deque` such as `ArrayDeque`, you can also use both ends:

```java
deque.addFirst(10);
deque.addLast(20);
deque.removeFirst();
deque.removeLast();
deque.peekFirst();
deque.peekLast();
```

Note: `Map` belongs to the Java Collections Framework, but it does not extend the `Collection` interface.