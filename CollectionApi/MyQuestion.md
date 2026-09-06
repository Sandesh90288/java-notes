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

# 4.basically comparator is generally used to compare the values based on our logic right?
Exactly. ✅

The simplest way to understand **`Comparator`** is:

> **`Comparator` allows us to define our own logic for deciding how two objects should be ordered.**

For example:

```java
Comparator<Integer> comparator = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
};
```

Here your logic is:

```text
o1 < o2  → negative
o1 > o2  → positive
o1 == o2 → 0
```

So Java's sorting algorithm asks your comparator:

```text
"Between these two objects, which should come first?"
```

and your `compare()` method answers.

---

### For example, descending order

```java
Comparator<Integer> comparator = (o1, o2) -> o2.compareTo(o1);
```

Now your logic is reversed:

```text
90 should come before 50
50 should come before 30
30 should come before 10
```

Result:

```text
[90, 50, 30, 10]
```

---

### And it doesn't have to be simple value comparison

Suppose you have:

```java
class Student {
    String name;
    int age;
}
```

You can say:

**"Sort students based on age."**

```java
Comparator<Student> byAge =
    (s1, s2) -> Integer.compare(s1.age, s2.age);
```

Or:

**"Sort students based on name."**

```java
Comparator<Student> byName =
    (s1, s2) -> s1.name.compareTo(s2.name);
```

Or even:

**"Sort by age descending."**

```java
Comparator<Student> byAgeDescending =
    (s1, s2) -> Integer.compare(s2.age, s1.age);
```

So the key distinction is:

```text
Comparable
    ↓
Class defines its natural/default ordering

Comparator
    ↓
We define an ordering externally
    ↓
Can create different sorting rules
```

And this is why `Comparator` is an **interface** with the important method:

```java
int compare(T o1, T o2);
```

The sorting algorithm doesn't care *what* your logic is. It simply calls `compare()` whenever it needs to determine the ordering.

# 5. sort method uses which algorithm to sort?
If you're asking about **`Collections.sort()` / `List.sort()` in modern Java**, the answer depends slightly on what is being sorted.

### For your case — `ArrayList<Integer>`

```java
Collections.sort(nums);
```

or

```java
nums.sort(comparator);
```

uses **TimSort** for object/reference types.

```text
Collections.sort()
       ↓
List.sort()
       ↓
TimSort
       ↓
Comparator.compare()
       ↓
sorted list
```

### What is TimSort?

TimSort is a **hybrid, stable sorting algorithm** based mainly on:

- **Merge sort** → merges already-sorted portions
- **Insertion sort** → efficiently handles small portions/runs

Its typical complexity is:

| Case | Complexity |
|---|---:|
| Best | O(n) |
| Average | O(n log n) |
| Worst | O(n log n) |

So for your code:

```java
List<Integer> nums = new ArrayList<>();

nums.add(10);
nums.add(20);
nums.add(30);
nums.add(90);
nums.add(50);
nums.add(40);

Collections.sort(nums, comparator);
```

the important answer is:

> **`Collections.sort()` ultimately sorts the object elements using TimSort, and your `Comparator` provides the comparison logic that TimSort uses.**

One subtle point: **primitive arrays** such as `int[]` are different—`Arrays.sort(int[])` uses a different algorithm (Dual-Pivot Quicksort). So don't generalize "Java `.sort()` always uses TimSort."

# 6.can i create multiple comparators in single class?
Yes, **absolutely**. In fact, creating multiple `Comparator`s is one of the main reasons `Comparator` is useful.

You can create different comparison rules for the **same type of object**.

### Example with `Integer`

```java
Comparator<Integer> ascending = (a, b) -> a.compareTo(b);

Comparator<Integer> descending = (a, b) -> b.compareTo(a);
```

Then you can choose which rule to use:

```java
List<Integer> nums = new ArrayList<>();

nums.add(10);
nums.add(50);
nums.add(20);
nums.add(40);
nums.add(30);

Collections.sort(nums, ascending);

System.out.println(nums);
```

Output:

```text
[10, 20, 30, 40, 50]
```

Then:

```java
Collections.sort(nums, descending);

System.out.println(nums);
```

Output:

```text
[50, 40, 30, 20, 10]
```

---

## More useful example: `Student`

Suppose:

```java
class Student {
    String name;
    int age;
    double marks;

    Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
}
```

You could create **three different comparators**:

### 1. Sort by age

```java
Comparator<Student> byAge =
    (s1, s2) -> Integer.compare(s1.age, s2.age);
```

### 2. Sort by marks

```java
Comparator<Student> byMarks =
    (s1, s2) -> Double.compare(s1.marks, s2.marks);
```

### 3. Sort by name

```java
Comparator<Student> byName =
    (s1, s2) -> s1.name.compareTo(s2.name);
```

Now you can choose:

```java
Collections.sort(students, byAge);
```

or:

```java
Collections.sort(students, byMarks);
```

or:

```java
Collections.sort(students, byName);
```

---

## This is the main advantage

Think of it like:

```text
Student
   │
   ├── Comparator 1 → sort by name
   │
   ├── Comparator 2 → sort by age
   │
   ├── Comparator 3 → sort by marks
   │
   └── Comparator 4 → sort by marks descending
```

The **Student class doesn't need to change**.

That's the major difference between `Comparable` and `Comparator`:

```text
Comparable
    ↓
One natural/default ordering
    ↓
Defined inside the class

Comparator
    ↓
Multiple possible orderings
    ↓
Defined outside the class
```

For reference u can check the collection1.java code

# 7.i can store the object in two ways either by creating the comparator and passing it to collection.sort method or by implementing the comparable interface in overriding the compareTo method?

Yes — **your understanding is basically correct**, but there is one terminology correction:

You are not "storing the object in two ways." You are **defining two different ways to compare/order objects for sorting**.

There are two main approaches:

## 1. `Comparable` → `compareTo()`

You make the class itself implement `Comparable`.

```java
class Student implements Comparable<Student> {

    int marks;

    Student(int marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

Now the `Student` class itself says:

> "My natural/default ordering is based on marks."

Then:

```java
Collections.sort(students);
```

Java automatically uses:

```java
student1.compareTo(student2);
```

Conceptually:

```text
Collections.sort(students)
          ↓
      compareTo()
          ↓
    determine order
```

---

# 2. `Comparator` → `compare()`

You don't modify the `Student` class.

Instead, you create an external comparison rule:

```java
Comparator<Student> byMarks =
    (s1, s2) -> Integer.compare(s1.marks, s2.marks);
```

Then:

```java
Collections.sort(students, byMarks);
```

Conceptually:

```text
Collections.sort(students, byMarks)
                 ↓
          Comparator.compare()
                 ↓
           determine order
```

---

# The important difference

Suppose your `Student` has:

```java
String name;
int age;
double marks;
```

With `Comparable`, you generally define **one natural ordering**:

```java
class Student implements Comparable<Student> {

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age);
    }
}
```

Now:

```java
Collections.sort(students);
```

means:

```text
Sort by age
```

But what if sometimes you want:

```text
Sort by name
Sort by marks
Sort by age
Sort by marks descending
```

That's where `Comparator` becomes powerful.

```java
Comparator<Student> byName =
    (s1, s2) -> s1.name.compareTo(s2.name);

Comparator<Student> byAge =
    (s1, s2) -> Integer.compare(s1.age, s2.age);

Comparator<Student> byMarks =
    (s1, s2) -> Double.compare(s1.marks, s2.marks);
```

Then:

```java
Collections.sort(students, byName);
```

or:

```java
Collections.sort(students, byAge);
```

or:

```java
Collections.sort(students, byMarks);
```

---

# One very important distinction

| `Comparable` | `Comparator` |
|---|---|
| `java.lang.Comparable` | `java.util.Comparator` |
| Method: `compareTo()` | Method: `compare()` |
| Comparison logic inside the class | Comparison logic outside the class |
| Defines natural ordering | Defines custom ordering |
| Usually one primary ordering | Can have many different orderings |
| `Collections.sort(list)` | `Collections.sort(list, comparator)` |

### Easy way to remember

```text
Comparable
    ↓
"I know how to compare myself."

Comparator
    ↓
"I know how to compare these two objects."
```

For example:

```java
student1.compareTo(student2);
```

vs.

```java
comparator.compare(student1, student2);
```

And one more thing: **`Comparator` is especially useful when you don't own the class**, or when you need multiple sorting strategies.
refer the collection1.java 

# 7. comparable vs comparator?
 # Comparable vs Comparator in Java

This is one of the most important topics in the **Java Collections API**, especially for sorting.

The simplest distinction is:

> **`Comparable` defines the natural/default ordering of a class.**  
> **`Comparator` defines an external/custom ordering of objects.**

---

## 7.1. `Comparable`

`Comparable` is an interface in:

```java
java.lang.Comparable
```

It has one important method:

```java
int compareTo(T o);
```

You implement it **inside the class whose objects you want to compare**.

### Example

```java
class Student implements Comparable<Student> {

    int marks;

    Student(int marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

Now `Student` has a natural ordering based on `marks`.

You can do:

```java
Collections.sort(students);
```

Internally, sorting can compare objects using:

```java
student1.compareTo(student2);
```

---

# 7.2. `Comparator`

`Comparator` is an interface in:

```java
java.util.Comparator
```

Its main method is:

```java
int compare(T o1, T o2);
```

The comparison logic is **outside the class**.

For example:

```java
Comparator<Student> byMarks =
    (s1, s2) -> Integer.compare(s1.marks, s2.marks);
```

Then:

```java
Collections.sort(students, byMarks);
```

The sorting algorithm uses:

```java
byMarks.compare(student1, student2);
```

---

# 7.3. The biggest difference

Imagine:

```java
class Student {
    String name;
    int age;
    double marks;
}
```

You might want to sort students in different ways.

```text
             Student
                │
       ┌────────┼─────────┐
       ↓        ↓         ↓
     name      age      marks
       │        │         │
       ↓        ↓         ↓
 Comparator Comparator Comparator
```

With `Comparator`, you can create all of these:

```java
Comparator<Student> byName =
    (s1, s2) -> s1.name.compareTo(s2.name);

Comparator<Student> byAge =
    (s1, s2) -> Integer.compare(s1.age, s2.age);

Comparator<Student> byMarks =
    (s1, s2) -> Double.compare(s1.marks, s2.marks);
```

Then:

```java
Collections.sort(students, byName);
```

or:

```java
Collections.sort(students, byAge);
```

or:

```java
Collections.sort(students, byMarks);
```

That's why `Comparator` is extremely useful when you have **multiple possible sorting rules**.

---

# 7.4. Side-by-side example

### Comparable

```java
class Student implements Comparable<Student> {

    int marks;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

Sorting:

```java
Collections.sort(students);
```

Relationship:

```text
Student
   │
   └── implements Comparable
             │
             └── compareTo()
```

---

### Comparator

```java
class Student {
    int marks;
}
```

No `Comparable`.

Create a separate comparator:

```java
Comparator<Student> comparator =
    (s1, s2) -> Integer.compare(s1.marks, s2.marks);
```

Sorting:

```java
Collections.sort(students, comparator);
```

Relationship:

```text
Student              Comparator
   │                     │
   │                     │
   └───────────────→ compare()
```

---

# 7.5. Comparison table

| Feature | `Comparable` | `Comparator` |
|---|---|---|
| Package | `java.lang` | `java.util` |
| Method | `compareTo()` | `compare()` |
| Logic location | Inside the class | Outside the class |
| Purpose | Natural/default ordering | Custom ordering |
| Number of orderings | Usually one natural ordering | Multiple possible orderings |
| Modify original class? | Yes | No |
| Sorting call | `Collections.sort(list)` | `Collections.sort(list, comparator)` |
| Lambda-friendly? | Not normally how it's written | Yes |
| Useful for third-party classes? | Difficult/impossible if you can't modify class | Yes |

---

# 7.6. The return value is important

Both ultimately use the same comparison concept:

```text
negative → first object comes before second
zero     → considered equal for ordering
positive → first object comes after second
```

For `Comparable`:

```java
a.compareTo(b)
```

For `Comparator`:

```java
comparator.compare(a, b)
```

For example:

```java
Integer a = 10;
Integer b = 20;

a.compareTo(b);
```

returns a negative value because:

```text
10 < 20
```

And:

```java
Integer.compare(20, 10);
```

returns a positive value because:

```text
20 > 10
```

---

# 7.7. Why do we need both?

Consider Java's `String`.

`String` already implements `Comparable<String>`.

Therefore:

```java
List<String> names = new ArrayList<>();

names.add("Zebra");
names.add("Apple");
names.add("Mango");

Collections.sort(names);
```

gives:

```text
[Apple, Mango, Zebra]
```

That's its **natural ordering**.

But suppose you want reverse order:

```java
Collections.sort(names, Comparator.reverseOrder());
```

Now:

```text
[Zebra, Mango, Apple]
```

You didn't modify `String`.

You simply supplied another ordering rule.

That's the power of `Comparator`.

---

# 7.8. Real-world analogy

Think of students.

### Comparable

The `Student` class itself says:

> "My default ranking is based on marks."

```java
class Student implements Comparable<Student>
```

That's **Comparable**.

---

### Comparator

Someone else says:

> "Today I want students sorted by name."

```java
Comparator<Student> byName
```

Another person says:

> "I want them sorted by age."

```java
Comparator<Student> byAge
```

Another says:

> "I want highest marks first."

```java
Comparator<Student> byMarksDescending
```

That's **Comparator**.

---

# 7.9. Modern Java makes Comparator even easier

Instead of writing:

```java
Comparator<Student> byMarks = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.marks, s2.marks);
    }
};
```

you can use a lambda:

```java
Comparator<Student> byMarks =
    (s1, s2) -> Integer.compare(s1.marks, s2.marks);
```

Or, even better, use the comparator factory methods:

```java
Comparator<Student> byMarks =
    Comparator.comparingInt(s -> s.marks);
```

And:

```java
Comparator<Student> byName =
    Comparator.comparing(s -> s.name);
```

Descending:

```java
Comparator<Student> byMarksDescending =
    Comparator.comparingInt((Student s) -> s.marks).reversed();
```

We'll cover these **modern Comparator APIs** in detail later.

---

# 7.10. Interview answer

If an interviewer asks:

> **What is the difference between Comparable and Comparator?**

A strong answer is:

> **Comparable is used to define the natural ordering of objects within the class itself through the `compareTo()` method. Comparator is used to define custom or multiple sorting orders externally through the `compare()` method. Comparable is in `java.lang`, while Comparator is in `java.util`.**

And if they ask:

> **Why would you use Comparator instead of Comparable?**

Say:

> **When I need multiple sorting strategies, or when I cannot modify the class whose objects I need to sort, I use Comparator.**

---

## One picture to remember

```text
                 SORTING OBJECTS
                       │
             ┌─────────┴─────────┐
             │                   │
        Comparable          Comparator
             │                   │
       compareTo()            compare()
             │                   │
       Inside the class     Outside the class
             │                   │
       Natural ordering      Custom ordering
             │                   │
       Usually one          Multiple possible
       ordering             orderings
```

**The key mental model:**

```text
Comparable  → "How should objects of THIS class naturally be ordered?"

Comparator  → "How do I want to order THESE objects right now?"
```

