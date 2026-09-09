Absolutely. Let's start **Java Stream API from the fundamentals and go all the way to advanced concepts**, just like we did with Collections.

# Java Stream API — In Depth

The **Stream API** was introduced in **Java 8** and is primarily used to process collections of data in a declarative and functional style.

We'll build this step-by-step:

### Roadmap

1. What is Stream API?
2. Collection vs Stream
3. Why Streams were introduced
4. How a Stream works internally
5. Creating Streams
6. Stream pipeline
7. Intermediate vs terminal operations
8. `filter()`
9. `map()`
10. `flatMap()`
11. `distinct()`
12. `sorted()`
13. `limit()` and `skip()`
14. `peek()`
15. `mapToInt()`, `mapToLong()`, `mapToDouble()`
16. `reduce()`
17. `collect()`
18. `Collectors`
19. `toList()`, `toSet()`, `toMap()`
20. `groupingBy()`
21. `partitioningBy()`
22. `joining()`
23. `counting()`
24. `summarizingInt()`
25. `min()` / `max()`
26. `findFirst()` / `findAny()`
27. `anyMatch()` / `allMatch()` / `noneMatch()`
28. Optional with Streams
29. Streams with Objects
30. Streams with Maps
31. Parallel Streams
32. Lazy evaluation
33. Short-circuiting
34. Stateful vs stateless operations
35. Ordering
36. Stream reuse
37. Performance
38. Common mistakes
39. Real-world examples
40. Stream API interview questions

---

# 1. What exactly is a Stream?

The first thing you need to understand is:

> **A Stream is NOT a data structure.**

A `Stream` does **not store data**.

Instead, a Stream represents a **sequence of elements that can be processed through a pipeline of operations**.

For example:

```java
List<Integer> numbers = List.of(10, 20, 30, 40, 50);
```

This is a **data structure**.

The list actually stores:

```text
10
20
30
40
50
```

Now:

```java
numbers.stream()
```

creates a Stream over those elements.

Conceptually:

```text
List
 |
 | stream()
 ↓
Stream
 |
 | filter
 ↓
Stream
 |
 | map
 ↓
Stream
 |
 | collect
 ↓
Result
```

The Stream itself isn't storing another copy of the numbers.

---

# 2. Collection vs Stream

This distinction is **extremely important**.

### Collection

A Collection is primarily concerned with:

> **How data is stored and accessed.**

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

### Stream

A Stream is primarily concerned with:

> **How data is processed.**

Example:

```java
numbers.stream()
       .filter(n -> n > 10)
       .forEach(System.out::println);
```

So:

```text
Collection → stores/manages data

Stream     → processes data
```

---

# 3. A simple example

Suppose we have:

```java
List<Integer> numbers =
        List.of(10, 15, 20, 25, 30);
```

We want numbers greater than 20.

Without Stream:

```java
for (Integer number : numbers) {

    if (number > 20) {
        System.out.println(number);
    }
}
```

Output:

```text
25
30
```

Using Stream:

```java
numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

Output:

```text
25
30
```

The Stream version describes **what we want**, rather than explicitly describing every step of the iteration.

---

# 4. Why was Stream API introduced?

Before Java 8, collection processing typically involved loops.

For example:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30, 40, 50);

List<Integer> result = new ArrayList<>();

for (Integer n : numbers) {

    if (n > 20) {
        result.add(n);
    }
}
```

Java 8 introduced functional programming features such as:

- Lambda expressions
- Functional interfaces
- Stream API
- Method references

So we can write:

```java
List<Integer> result =
        numbers.stream()
               .filter(n -> n > 20)
               .collect(Collectors.toList());
```

This gives us a more declarative way of processing data.

---

# 5. Stream API is NOT only for Collections

A common beginner misconception is:

> "Streams are used only with Lists."

No.

Streams can be created from many sources.

For example:

### Collection

```java
list.stream();
```

### Set

```java
set.stream();
```

### Map

A `Map` itself doesn't directly implement `Collection`, so:

```java
map.stream(); // ❌
```

Instead:

```java
map.entrySet().stream();
```

or:

```java
map.keySet().stream();
```

or:

```java
map.values().stream();
```

### Arrays

```java
Arrays.stream(array);
```

### Individual values

```java
Stream.of(10, 20, 30);
```

### Files

```java
Files.lines(path);
```

There are many possible sources.

---

# 6. How do we create a Stream?

Let's start with the most common one.

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);

Stream<Integer> stream = numbers.stream();
```

You need:

```java
import java.util.stream.Stream;
```

Now `stream` represents the sequence:

```text
10 → 20 → 30 → 40 → 50
```

---

# 7. The Stream Pipeline

This is probably the **most important concept in Stream API**.

A Stream generally follows this structure:

```text
SOURCE
   ↓
INTERMEDIATE OPERATIONS
   ↓
TERMINAL OPERATION
```

For example:

```java
numbers.stream()
       .filter(n -> n > 20)
       .map(n -> n * 2)
       .forEach(System.out::println);
```

Break it down:

```text
numbers
   ↓
stream()
   ↓
filter()
   ↓
map()
   ↓
forEach()
```

### Source

```java
numbers.stream()
```

### Intermediate operations

```java
filter()
map()
```

### Terminal operation

```java
forEach()
```

---

# 8. Intermediate Operations

Intermediate operations transform a Stream into another Stream.

For example:

```java
filter()
map()
sorted()
distinct()
limit()
skip()
flatMap()
```

Example:

```java
Stream<Integer> result =
        numbers.stream()
               .filter(n -> n > 20);
```

Notice the return type:

```java
Stream<Integer>
```

Therefore we can continue:

```java
numbers.stream()
       .filter(n -> n > 20)
       .map(n -> n * 2);
```

And continue again:

```java
numbers.stream()
       .filter(n -> n > 20)
       .map(n -> n * 2)
       .sorted();
```

That's why they are called **intermediate operations**.

They don't finish the Stream.

---

# 9. Terminal Operations

A terminal operation **ends the Stream pipeline**.

Examples:

```java
forEach()
collect()
reduce()
count()
min()
max()
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
```

Example:

```java
numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

After:

```java
forEach()
```

the Stream pipeline is finished.

---

# 10. The most important rule

Remember this:

> **A Stream does nothing until a terminal operation is executed.**

For example:

```java
numbers.stream()
       .filter(n -> n > 20)
       .map(n -> n * 2);
```

You might expect processing to happen.

But it doesn't.

There is no terminal operation.

So the pipeline isn't executed.

Add:

```java
.forEach(System.out::println);
```

Now it executes.

---

# 11. Lazy Evaluation

This leads to one of the most important Stream concepts:

> **Intermediate Stream operations are lazy.**

Example:

```java
numbers.stream()
       .filter(n -> {
           System.out.println("Filtering " + n);
           return n > 20;
       });
```

Nothing gets printed.

Why?

Because `filter()` is an intermediate operation.

Now:

```java
numbers.stream()
       .filter(n -> {
           System.out.println("Filtering " + n);
           return n > 20;
       })
       .forEach(System.out::println);
```

Now the processing happens.

---

# 12. `filter()`

`filter()` is used when you want to **select elements based on a condition**.

Syntax:

```java
stream.filter(predicate)
```

Example:

```java
List<Integer> numbers =
        List.of(10, 15, 20, 25, 30);

numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

Output:

```text
25
30
```

The lambda:

```java
n -> n > 20
```

is a `Predicate<Integer>`.

Remember:

```text
Predicate<T>
    ↓
takes T
returns boolean
```

So:

```java
n -> n > 20
```

means:

```text
input: Integer
output: boolean
```

---

# 13. `map()`

`map()` is used to **transform each element**.

For example:

```java
List<Integer> numbers =
        List.of(1, 2, 3, 4, 5);
```

We want:

```text
1 → 10
2 → 20
3 → 30
4 → 40
5 → 50
```

Use:

```java
numbers.stream()
       .map(n -> n * 10)
       .forEach(System.out::println);
```

Output:

```text
10
20
30
40
50
```

The key idea:

```text
filter → selects

map → transforms
```

---

# 14. `filter()` + `map()`

Now combine them.

```java
List<Integer> numbers =
        List.of(10, 15, 20, 25, 30);
```

Requirement:

> Find numbers greater than 20 and multiply them by 2.

```java
numbers.stream()
       .filter(n -> n > 20)
       .map(n -> n * 2)
       .forEach(System.out::println);
```

Processing:

```text
10 → filter ❌
15 → filter ❌
20 → filter ❌
25 → filter ✅ → 50
30 → filter ✅ → 60
```

Output:

```text
50
60
```

---

# 15. Important: Stream doesn't modify the original Collection

Suppose:

```java
List<Integer> numbers =
        new ArrayList<>(List.of(10, 20, 30));
```

Then:

```java
numbers.stream()
       .map(n -> n * 2)
       .forEach(System.out::println);
```

Output:

```text
20
40
60
```

But:

```java
System.out.println(numbers);
```

still gives:

```text
[10, 20, 30]
```

The Stream operation didn't modify the original list.

Unless your operation explicitly mutates an object or collection—which is generally something to avoid in Stream pipelines.

---

# 16. Stream does not necessarily process the entire collection

This becomes very interesting when we learn **short-circuiting**.

For example:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

```java
numbers.stream()
       .filter(n -> n > 20)
       .findFirst();
```

The Stream doesn't necessarily need to process everything.

It can stop once it finds:

```text
30
```

This is called **short-circuiting**.

We'll go deeply into this later.

---

# 17. One more important concept: Stream can be consumed only once

This is a common interview question.

```java
Stream<Integer> stream =
        numbers.stream();

stream.forEach(System.out::println);

stream.forEach(System.out::println); // ❌
```

The second operation throws:

```text
IllegalStateException
```

because:

> **A Stream cannot be reused after a terminal operation.**

If you need another pipeline:

```java
numbers.stream()
       .forEach(System.out::println);

numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

Create a **new Stream** from the source.

---

# 18. The mental model you should remember

Think of Stream API like a **data-processing pipeline**:

```text
                 STREAM PIPELINE

       SOURCE
          │
          ▼
     ┌─────────┐
     │ filter  │
     └─────────┘
          │
          ▼
     ┌─────────┐
     │   map   │
     └─────────┘
          │
          ▼
     ┌─────────┐
     │ sorted  │
     └─────────┘
          │
          ▼
     ┌──────────┐
     │ collect  │
     └──────────┘
          │
          ▼
        RESULT
```

For example:

```java
List<String> names =
        List.of("Sandesh", "Rahul", "Amit", "Suresh");

List<String> result =
        names.stream()
             .filter(name -> name.length() > 5)
             .map(String::toUpperCase)
             .sorted()
             .collect(Collectors.toList());
```

Conceptually:

```text
names
  ↓
filter length > 5
  ↓
transform to uppercase
  ↓
sort
  ↓
collect into List
```

---

## One distinction to lock in now

| Concept | Purpose |
|---|---|
| `Collection` | Store/manage data |
| `Stream` | Process data |
| `filter()` | Select elements |
| `map()` | Transform elements |
| Intermediate operation | Builds/changes pipeline |
| Terminal operation | Executes and closes pipeline |
| Lazy operation | Doesn't execute immediately |
| Stream | Usually consumed only once |

### The most important formula

```text
Stream =
Source
+
Intermediate Operations
+
Terminal Operation
```

For example:

```java
numbers.stream()              // Source
       .filter(n -> n > 10)   // Intermediate
       .map(n -> n * 2)       // Intermediate
       .sorted()              // Intermediate
       .collect(Collectors.toList()); // Terminal
```