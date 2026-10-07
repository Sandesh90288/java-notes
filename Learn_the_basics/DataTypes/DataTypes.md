# Module 1 — Learn the Basics
## Section 3 — Data Types

Your roadmap places **Data Types immediately after Basic Syntax** and before Conditionals, Arrays, and Loops. :chatgpt-content-reference{index="0"}

Since you already know Java at an intermediate level, we'll focus on **complete revision + deeper JVM understanding + modern Java + interview relevance**.

---

# 1. 🟢 What Is a Data Type?

A data type tells Java:

- what kind of value a variable can hold
- what operations are valid on that value
- how the value participates in type checking
- for primitives, the value's basic representation/range

Example:

```java
int age = 23;
String name = "Sandesh";
boolean active = true;
```

Conceptually:

```text
age    → int
name   → String
active → boolean
```

Java is **statically typed**.

That means the type of a variable/expression is determined at compile time.

```java
int age = 23;

// ❌ Compile-time error
age = "hello";
```

---

# 2. 🔵 Java's Main Type Categories

Java types can broadly be divided into:

```text
Java Types
│
├── Primitive Types
│
└── Reference Types
```

### Primitive

```text
byte
short
int
long
float
double
char
boolean
```

### Reference

Examples:

```text
String
Arrays
Classes
Interfaces
Enums
Records
Objects
```

This distinction is **fundamental to Java**.

---

# 3. Primitive Data Types

Java has **8 primitive types**.

| Type | Typical size | Example |
|---|---:|---|
| `byte` | 8-bit | `byte x = 10;` |
| `short` | 16-bit | `short x = 100;` |
| `int` | 32-bit | `int x = 1000;` |
| `long` | 64-bit | `long x = 1000L;` |
| `float` | 32-bit | `float x = 10.5f;` |
| `double` | 64-bit | `double x = 10.5;` |
| `char` | 16-bit | `char c = 'A';` |
| `boolean` | JVM-dependent representation | `boolean b = true;` |

### Important correction to a common misconception

For `boolean`, don't memorize:

> "`boolean` is exactly 1 byte."

Java specifies the **type and its values**, but does not require a particular storage size for `boolean` variables in memory. JVM implementation details can differ.

---

# 4. Integer Types

Java has four integer primitive types:

```text
byte
short
int
long
```

---

## `byte`

```java
byte age = 23;
```

Range:

```text
-128 to 127
```

Because it is signed 8-bit two's-complement.

Useful when working with:

- binary data
- network data
- files
- memory-sensitive structures

But don't automatically use `byte` just because a number is small.

For ordinary integer calculations, `int` is generally the natural choice.

---

# 5. `short`

```java
short temperature = 250;
```

Range:

```text
-32,768 to 32,767
```

It's less commonly used in normal application code.

---

# 6. `int`

This is the **default integer type** in Java.

```java
int age = 23;
int salary = 25000;
```

Range:

```text
-2,147,483,648
to
2,147,483,647
```

Unless you specifically need another integer type, you'll normally use:

```java
int
```

---

# 7. `long`

For larger integer values:

```java
long population = 1_400_000_000L;
```

Notice the:

```java
L
```

suffix.

Without it:

```java
long value = 10000000000;
```

can fail because the integer literal itself is initially treated as an `int` literal when it doesn't fit.

Use:

```java
long value = 10_000_000_000L;
```

### Backend example

Database IDs, timestamps, counts, etc. often use:

```java
long id;
```

or:

```java
Long id;
```

The distinction between `long` and `Long` becomes important when working with JPA, collections, and nullable database values.

---

# 8. Numeric Literals

Java allows readable numeric literals using `_`.

```java
int population = 1_400_000_000;
long distance = 9_000_000_000L;
```

These are equivalent to:

```java
1400000000
9000000000L
```

The underscores are only for readability.

You can also use different bases:

```java
int decimal = 100;

int binary = 0b1100100;

int octal = 0144;

int hexadecimal = 0x64;
```

All represent:

```text
100
```

---

# 9. Floating-Point Types

Java has:

```text
float
double
```

---

## `double`

```java
double price = 99.99;
```

A decimal literal such as:

```java
99.99
```

is normally a `double`.

Therefore:

```java
double price = 99.99;
```

works.

---

## `float`

```java
float temperature = 36.5f;
```

Notice:

```java
f
```

Without it:

```java
float temperature = 36.5;
```

❌ Compilation error.

Because `36.5` is a `double` literal.

---

# 10. ⚠️ Floating-Point Precision

This is extremely important for backend development.

Try:

```java
System.out.println(0.1 + 0.2);
```

You may get:

```text
0.30000000000000004
```

Why?

Because binary floating-point representation cannot represent many decimal fractions exactly.

Therefore:

### Don't use `double` blindly for money.

For financial calculations, Java commonly uses:

```java
BigDecimal
```

Example:

```java
BigDecimal price = new BigDecimal("99.99");
```

rather than:

```java
double price = 99.99;
```

We'll revisit this under **Math Operations** and backend development.

---

# 11. `char`

```java
char grade = 'A';
```

`char` represents a **UTF-16 code unit**, not simply "an ASCII character."

It is 16 bits.

Examples:

```java
char a = 'A';
char digit = '7';
char symbol = '@';
```

You can also use Unicode:

```java
char c = '\u20B9';
```

which represents:

```text
₹
```

---

# 12. `char` and Numbers

A `char` has an integer numeric value associated with its UTF-16 code unit.

Example:

```java
char c = 'A';

System.out.println((int) c);
```

Output:

```text
65
```

Similarly:

```java
char c = 65;
System.out.println(c);
```

prints:

```text
A
```

This becomes important when we study **type casting**.

---

# 13. 🆕 Unicode — Important Modern Understanding

Don't think of Java's `char` as:

> "one Unicode character."

That's not always true.

Some Unicode characters require **two UTF-16 code units**, meaning they are represented by a **surrogate pair**.

For example, many emoji are outside the Basic Multilingual Plane.

Therefore:

```java
String
```

is the appropriate abstraction for general text, not `char` for every possible Unicode character.

This becomes important when dealing with internationalization, APIs, databases, and text processing.

---

# 14. `boolean`

```java
boolean isActive = true;
```

Possible values:

```text
true
false
```

Java does **not** allow:

```java
boolean value = 1;
```

or:

```java
boolean value = 0;
```

Unlike C/C++ conventions.

You must use:

```java
true
false
```

---

# 15. Reference Types

Now the other major category.

Example:

```java
String name = "Sandesh";
```

`String` is **not a primitive type**.

It is a class.

Similarly:

```java
Student student = new Student();
```

`Student` is a reference type.

Conceptually:

```text
Reference variable
      ↓
   object
```

Example:

```java
Student s = new Student();
```

Think:

```text
s ───────────► Student object
```

The variable `s` contains a reference to an object rather than the object being a primitive value.

---

# 16. Primitive vs Reference

This distinction is one of the most important Java concepts.

| Primitive | Reference |
|---|---|
| `int` | `String` |
| `double` | `Student` |
| `boolean` | `Integer` |
| `char` | `int[]` |
| `long` | `List<String>` |

Example:

```java
int x = 10;

String name = "Sandesh";
```

Conceptually:

```text
x
↓
10

name
 ↓
String object
```

---

# 17. Default Values

Instance/static fields have default values if they aren't explicitly initialized.

```java
class User {

    int age;
    boolean active;
    String name;
}
```

Conceptually:

```text
age    → 0
active → false
name   → null
```

Common defaults:

| Type | Default |
|---|---|
| `byte` | `0` |
| `short` | `0` |
| `int` | `0` |
| `long` | `0L` |
| `float` | `0.0f` |
| `double` | `0.0d` |
| `char` | `'\u0000'` |
| `boolean` | `false` |
| Reference | `null` |

---

# 18. ⚠️ Local Variables Are Different

This is a common interview question.

```java
public static void main(String[] args) {

    int age;

    System.out.println(age);
}
```

❌ Compilation error.

Local variables do **not** receive automatic default values before use.

You must initialize:

```java
int age = 23;
```

Whereas:

```java
class User {
    int age;
}
```

is valid because `age` is an instance field and gets its default value.

---

# 19. `null`

`null` represents the absence of a reference.

Example:

```java
String name = null;
```

But:

```java
int age = null;
```

❌ Invalid.

Why?

Because `int` is primitive.

`String` is a reference type.

---

# 20. Wrapper Classes

Java provides wrapper classes for primitive types.

```text
byte    → Byte
short   → Short
int     → Integer
long    → Long
float   → Float
double  → Double
char    → Character
boolean → Boolean
```

Why?

Because many Java APIs work with **objects**, not primitives.

For example:

```java
List<Integer> numbers;
```

You cannot write:

```java
List<int> numbers;
```

because Java generics work with reference types.

---

# 21. Autoboxing

Java can automatically convert:

```java
int
```

to:

```java
Integer
```

Example:

```java
Integer x = 10;
```

Conceptually:

```text
int
 ↓
Integer
```

This is called **autoboxing**.

---

# 22. Unboxing

The reverse:

```java
Integer x = 10;

int y = x;
```

is **unboxing**.

Conceptually:

```text
Integer
   ↓
 int
```

---

# 23. ⚠️ Wrapper Trap — `NullPointerException`

Consider:

```java
Integer age = null;

int value = age;
```

This can cause:

```text
NullPointerException
```

because Java needs to unbox:

```text
Integer → int
```

but the reference is `null`.

This is extremely relevant in backend applications because database fields and API values can legitimately be nullable.

---

# 24. `==` with Primitives vs References

For primitives:

```java
int a = 10;
int b = 10;

System.out.println(a == b);
```

prints:

```text
true
```

It compares values.

For references:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);
```

prints:

```text
false
```

because `==` compares references.

For object content, generally use:

```java
a.equals(b)
```

This is extremely important and we'll revisit it in **Strings and Methods**.

---

# 25. Type Promotion

Java performs numeric promotion during expressions.

Example:

```java
byte a = 10;
byte b = 20;

var result = a + b;
```

What type is `result`?

Not `byte`.

It's:

```text
int
```

Java promotes the operands.

This surprises beginners and is frequently tested in interviews.

---

# 26. Integer Arithmetic Overflow

Consider:

```java
int x = Integer.MAX_VALUE;

x = x + 1;

System.out.println(x);
```

You don't get an exception by default.

The result wraps around:

```text
2147483647
+
1
=
-2147483648
```

This is **integer overflow**.

For some applications, especially financial/scientific systems, overflow handling needs to be explicitly considered.

---

# 27. `var` — Modern Java

As discussed earlier:

```java
var age = 23;
```

The compiler infers:

```text
int
```

This:

```java
var name = "Sandesh";
```

infers:

```text
String
```

### Important

`var`:

- works for local variables
- requires an initializer
- doesn't make Java dynamically typed
- cannot generally be used for fields
- cannot be used as a method parameter type
- cannot be used as a method return type

Example:

```java
var x = 10;
```

Valid.

```java
var x;
```

❌ Invalid.

---

# 28. `var` Does Not Mean `Object`

This is important.

```java
var x = "Hello";
```

doesn't mean:

```java
Object x = "Hello";
```

The inferred type is:

```text
String
```

So the compiler knows String-specific methods are available.

```java
var name = "Sandesh";

name.length();
```

works.

---

# 29. `final`

You can make a variable non-reassignable:

```java
final int MAX_USERS = 100;
```

Then:

```java
MAX_USERS = 200;
```

❌ Compilation error.

But remember:

> `final` on a reference does not make the object immutable.

Example:

```java
final List<String> names = new ArrayList<>();

names.add("Sandesh");     // valid
names = new ArrayList<>(); // ❌
```

The reference cannot be reassigned, but the object may still be mutable.

We'll study `final` later because your roadmap has a dedicated **Final Keyword** section. :chatgpt-content-reference{index="1"}

---

# 30. 🆕 Modern Java — Primitive Patterns

Modern Java is increasingly adding better interactions between primitives and pattern matching.

For example, Java 26 has preview work around **primitive types in patterns, `instanceof`, and `switch`**.

This is not something you need to memorize in the Data Types section yet.

We'll cover it properly when we reach:

```text
Conditionals
↓
Type Casting
↓
Pattern Matching
```

The important thing now is simply knowing:

> Modern Java is evolving beyond the traditional primitive/reference handling model.

---

# 31. 💼 Backend Relevance

Data types become extremely important when working with APIs and databases.

Suppose you have:

```java
public class User {

    private Long id;

    private String name;

    private Integer age;

    private Boolean active;

}
```

Why might a backend developer use:

```java
Long
Integer
Boolean
```

instead of:

```java
long
int
boolean
```

One reason is **nullability**.

For example:

```text
Database
   ↓
NULL
   ↓
Java
   ↓
Long id = null
```

A primitive:

```java
long id;
```

cannot represent `null`.

This distinction becomes extremely important with:

- JPA/Hibernate 
- DTOs 
- JSON
- database columns
- API requests
- optional fields

---

# 32. 💼 Primitive vs Wrapper — Backend Example

Imagine an API request:

```json
{
    "age": null
}
```

With:

```java
Integer age;
```

you can represent:

```text
age = null
```

With:

```java
int age;
```

you cannot.

This is one reason wrapper types appear heavily in enterprise Java.

---

# 33. 🎯 Interview Traps

### Trap 1

```java
byte b = 10;
b = b + 1;
```

Does this compile?

**No.**

Because arithmetic promotes `b` to `int`.

But:

```java
b++;
```

has special compound increment behavior and can compile.

---

### Trap 2

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

This can print:

```text
true
```

because of Integer caching.

But:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
```

should not be used as a value comparison and can produce:

```text
false
```

Use:

```java
a.equals(b)
```

for value comparison.

**Never rely on wrapper caching for application logic.**

---

### Trap 3

```java
Integer x = null;

if (x == 10) {
}
```

This can trigger unboxing and potentially result in `NullPointerException`.

---

### Trap 4

```java
double x = 0.1;
double y = 0.2;

System.out.println(x + y == 0.3);
```

Don't assume this is `true`.

Floating-point arithmetic has precision limitations.

---

# 34. 🧪 Practice Checkpoint

### Q1

What are Java's 8 primitive types?

---

### Q2

Why does this fail?

```java
float price = 10.5;
```

How would you fix it?

---

### Q3     

What is the difference between:

```java
int x;
```

as a field and:

```java
int x;
```

as a local variable?

---

### Q4

What happens here?

```java
byte x = 10;
byte y = 20;

var z = x + y;
```

What is the type of `z` and why?

---

### Q5

Explain:

```java
Integer x = null;
int y = x;
```

---

### Q6

What is the difference between:

```java
int
```

and:

```java
Integer
```

and why does it matter in backend development?

---

### Q7

Why is this dangerous for money?

```java
double price = 0.1 + 0.2;
```

What should you generally consider instead?

---

### Q8

What type does Java infer here?

```java
var x = 10;
var y = 10.5;
var z = "Java";
var a = true;
```

---

### Q9 — Interview Level

What is the difference between:

```java
final Integer x = 10;
```

and:

```java
Integer x = 10;
```

---

### Q10 — Code Analysis

What happens here?

```java
public class Test {

    static int x;

    public static void main(String[] args) {

        int y;

        System.out.println(x);
        System.out.println(y);
    }
}
```

Which line causes the compilation error and why?

---

# 📌 Final Data Types Cheat Sheet

```text
                    JAVA TYPES
                        │
             ┌──────────┴──────────┐
             │                     │
        Primitive              Reference
             │                     │
     ┌───────┼────────┐      String
     │       │        │      Arrays
   Integer Floating  Other   Classes
     │       │        │      Interfaces
 byte      float     char    Enums
 short     double    boolean Records
 int
 long
```

### Remember these especially:

```text
int      → default integer
double   → default floating-point
char     → UTF-16 code unit
boolean  → true / false
String   → reference type
null     → reference absence
Integer  → wrapper for int
var      → local type inference
BigDecimal → consider for exact decimal/money calculations
```

### The most important interview distinctions:

```text
Primitive vs Reference
Field vs Local Variable
int vs Integer
== vs equals()
float vs double
Stack vs Heap
null vs 0
var vs dynamic typing
```

That completes the **Data Types** revision layer. The next roadmap section is **Conditionals**, where we'll cover `if/else`, ternary, `switch`, modern switch expressions, pattern matching, and the newer Java developments around primitive patterns.