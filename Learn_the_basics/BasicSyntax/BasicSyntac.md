# Module 1 — Learn the Basics
## Section 1 — Basic Syntax

Good. We'll do this as a **revision + intermediate + modern Java** lesson, not as a beginner-only tutorial.

Your roadmap places **Basic Syntax first under Learn the Basics**, before Data Types, Conditionals, Arrays, Loops, etc. :chatgpt-content-reference{index="0"}

I'm also checking the modern Java additions against current Java documentation. As of **September 2026, Java 27 is the latest Java SE release**, while Java 25 is an LTS release. :chatgpt-content-reference{index="1"}

---

# 1. What Exactly Is Java Syntax?

**Syntax** means the rules that determine how Java code must be written so that the compiler/JVM can understand it.

For example:

```java
int age = 23;
```

This follows Java syntax.

But:

```java
int = age 23;
```

doesn't.

At a high level, Java code consists of:

```text
Source File
    ↓
Packages / Imports
    ↓
Classes / Interfaces / Records
    ↓
Fields / Methods
    ↓
Statements / Expressions
    ↓
Execution
```

For now, concentrate on the basic building blocks.

---

# 2. The Traditional Java Program

The classic structure is:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello Java");

    }
}
```

You should know this syntax automatically.

Let's break it down.

---

## 2.1 `class`

```java
class Main {

}
```

A class defines a type.

```java
public class Student {

}
```

Here:

- `class` → Java keyword
- `Student` → class name
- `{ }` → class body

### Naming convention

Classes normally use **PascalCase**:

```java
Student
BankAccount
PaymentService
UserController
OrderRepository
```

---

# 3. `public`

```java
public class Main
```

`public` is an **access modifier**.

It controls accessibility.

At this stage, remember:

```text
public       → accessible broadly
private      → accessible only within the declaring class
protected    → special inheritance/package access rules
(no modifier) → package-private
```

We'll study access modifiers properly later under **Basics of OOP → Access Specifiers**. Your roadmap explicitly has that section later. :chatgpt-content-reference{index="2"}

---

# 4. `main()` Method

Traditional Java applications commonly start execution from:

```java
public static void main(String[] args)
```

Example:

```java
public static void main(String[] args) {

    System.out.println("Starting application");

}
```

Understand every component:

| Component | Meaning |
|---|---|
| `public` | Accessible to the launcher |
| `static` | Belongs to the class rather than an instance |
| `void` | Returns nothing |
| `main` | Launch method name |
| `String[]` | Array of Strings |
| `args` | Parameter name |

The Java launcher loads the specified class and invokes its `main()` method. :chatgpt-content-reference{index="3"}

---

# 5. Why Is `main()` Static?

This is an important **intermediate-level question**.

Suppose:

```java
public class Main {

    public void start() {
        System.out.println("Hello");
    }
}
```

`start()` is an instance method.

To call it, normally:

```java
Main obj = new Main();
obj.start();
```

But when Java is starting your program, it doesn't have an object of `Main` that you created yet.

Therefore the traditional entry point is:

```java
public static void main(String[] args)
```

because it can be invoked without first creating a `Main` object.

Conceptually:

```text
JVM / Java launcher
       ↓
Main.main(...)
       ↓
program execution
```

We'll revisit this when we study **Static Keyword**.

---

# 6. Statements

A statement represents an action.

Example:

```java
int age = 23;
```

```java
System.out.println("Hello");
```

```java
return;
```

Most Java statements terminate with:

```java
;
```

Example:

```java
int x = 10;
int y = 20;
System.out.println(x + y);
```

---

# 7. Expressions

An expression produces a value.

Examples:

```java
10 + 20
```

produces:

```text
30
```

Another:

```java
x + y
```

produces the result of adding `x` and `y`.

This:

```java
int result = x + y;
```

contains:

```text
statement
 └── expression: x + y
```

### Important distinction

```java
x + y;
```

is an expression used as a statement.

Whereas:

```java
int result = x + y;
```

is a declaration/initialization statement containing an expression.

This distinction becomes useful when studying lambdas, streams, and modern `switch` expressions.

---

# 8. Blocks `{ }`

A block is a group of statements surrounded by `{}`.

```java
{
    int x = 10;
    System.out.println(x);
}
```

You'll see blocks in:

```java
class
method
if
else
for
while
switch
try
catch
```

Example:

```java
if (age >= 18) {
    System.out.println("Adult");
}
```

The `{}` defines the block associated with the `if`.

---

# 9. Scope

A block can introduce a scope.

```java
public static void main(String[] args) {

    int x = 10;

    {
        int y = 20;

        System.out.println(x);
        System.out.println(y);
    }

    System.out.println(x);
}
```

`y` exists only within its block.

```text
main scope
│
├── x
│
└── inner block
    └── y
```

We'll study scopes much more deeply in the roadmap's **Variables and Scopes** section.

---

# 10. Comments

### Single-line

```java
// This is a comment
```

### Multi-line

```java
/*
   This is a
   multi-line comment.
*/
```

### Documentation comment

There's also:

```java
/**
 * Calculates the total price.
 */
public double calculateTotal() {
    ...
}
```

This is a **Javadoc comment**.

It can be processed by the `javadoc` tool to generate API documentation.

For backend development, you'll see Javadocs frequently in Java libraries and frameworks.

---

# 11. Identifiers

Identifiers are names given to program elements.

Examples:

```java
age
studentName
calculateSalary
Student
PaymentService
MAX_RETRIES
```

### Valid

```java
age
student1
studentName
_student
$value
```

### Invalid

```java
1student
student-name
class
```

Why?

Because:

- identifiers cannot start with a digit
- `-` isn't valid as part of a normal identifier
- Java keywords cannot be used as identifiers

---

# 12. Java Is Case-Sensitive

These are completely different:

```java
age
Age
AGE
aGe
```

Likewise:

```java
System.out.println()
```

is correct.

```java
system.out.println()
```

is not.

This also matters with classes:

```java
String
```

is not:

```java
string
```

---

# 13. Keywords

Java reserves certain words for the language.

Examples:

```java
class
public
private
protected
static
final
void
int
if
else
for
while
return
new
this
super
extends
implements
interface
record
sealed
var
```

You cannot freely use these as identifiers.

For example:

```java
int class = 10;
```

❌ Invalid.

---

# 14. Naming Conventions

This isn't syntax enforced by the compiler, but it's extremely important in professional Java.

### Classes

```java
PaymentService
UserController
BankAccount
```

### Methods

```java
calculateSalary()
getUserById()
processPayment()
```

### Variables

```java
userName
accountBalance
totalAmount
```

### Constants

```java
MAX_RETRIES
DEFAULT_TIMEOUT
```

Generally:

```text
Class        → PascalCase
method       → camelCase
variable     → camelCase
constant     → UPPER_SNAKE_CASE
```

You'll see these conventions throughout Spring Boot code.

---

# 15. `print()` vs `println()`

```java
System.out.print("Hello");
System.out.print("Java");
```

Output:

```text
HelloJava
```

Whereas:

```java
System.out.println("Hello");
System.out.println("Java");
```

Output:

```text
Hello
Java
```

`println()` adds a line terminator after printing.

---

# 16. String vs Character

String:

```java
"Hello"
```

Character:

```java
'H'
```

So:

```java
System.out.println("A");
```

uses a `String`.

```java
System.out.println('A');
```

uses a `char`.

Remember:

```text
"..." → String
'...' → char
```

We'll go deeply into this in **Data Types** and **Strings and Methods**.

---

# 17. Escape Sequences

Java supports escape sequences inside character/string literals.

```java
System.out.println("Hello\nJava");
```

Output:

```text
Hello
Java
```

Common ones:

| Escape | Meaning |
|---|---|
| `\n` | New line |
| `\t` | Tab |
| `\"` | Double quote |
| `\'` | Single quote |
| `\\` | Backslash |
| `\r` | Carriage return |
| `\b` | Backspace |

Example:

```java
System.out.println("He said \"Hello\"");
```

Output:

```text
He said "Hello"
```

---

# 18. Text Blocks — Modern Java

Java 15 introduced **text blocks**.

Instead of:

```java
String json = "{\n" +
              "  \"name\": \"Sandesh\",\n" +
              "  \"age\": 23\n" +
              "}";
```

you can write:

```java
String json = """
        {
          "name": "Sandesh",
          "age": 23
        }
        """;
```

This is extremely useful in backend development for:

- JSON
- SQL
- HTML
- multiline messages

Text blocks became a permanent Java feature in Java 15. :chatgpt-content-reference{index="4"}

---

# 19. `var` — Modern Java

Java 10 introduced local variable type inference.

Instead of:

```java
String name = "Sandesh";
```

you can write:

```java
var name = "Sandesh";
```

The compiler infers:

```text
name → String
```

Similarly:

```java
var age = 23;
```

means:

```text
age → int
```

And:

```java
var users = new ArrayList<String>();
```

means the compiler knows:

```text
users → ArrayList<String>
```

### Important

`var` does **not** make Java dynamically typed.

This is invalid:

```java
var age = 23;

age = "hello";  // ❌
```

The type was inferred as `int`.

### Important limitation

You cannot generally use `var` for fields:

```java
class User {

    // ❌
    var name = "Sandesh";

}
```

`var` is for **local variable type inference**, not a replacement for explicit field types.

We'll revisit this under **Data Types** and **Variables and Scopes**.

---

# 20. Compilation

Traditional compilation:

```text
Main.java
    ↓
javac
    ↓
Main.class
    ↓
Bytecode
    ↓
JVM
    ↓
Machine execution
```

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

### `javac`

Java compiler.

### `java`

Java launcher.

### JVM

Executes Java bytecode.

---

# 21. Source-File Mode

Java also supports:

```bash
java Main.java
```

The launcher can compile and run a source-file program without you explicitly invoking `javac` first. :chatgpt-content-reference{index="5"}

So you can have:

```text
Main.java
   ↓
java Main.java
   ↓
program executes
```

This is particularly convenient for small programs, scripts, demonstrations, and learning.

---

# 22. 🆕 Java 25 — Compact Source Files

This is one of the most important **new additions** you should know.

Java 25 made **compact source files and instance `main` methods permanent**. :chatgpt-content-reference{index="6"}

Traditional:

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

Modern Java 25+:

```java
void main() {
    System.out.println("Hello");
}
```

That's valid Java.

The compiler implicitly creates a class around the top-level code. Oracle calls this an **implicitly declared class**. :chatgpt-content-reference{index="7"}

---

# 23. Instance `main()`

Traditionally:

```java
public static void main(String[] args)
```

Modern compact source files can have:

```java
void main() {
    System.out.println("Hello");
}
```

Notice:

```text
public  → gone
static  → gone
String[] args → gone
```

The launcher can create an instance of the implicitly declared class and invoke the instance `main()` method. :chatgpt-content-reference{index="8"}

### But don't forget the traditional syntax

For professional Java/Spring Boot development, you absolutely need to understand:

```java
public static void main(String[] args)
```

The new syntax **doesn't replace your knowledge of the traditional class/object model**.

---

# 24. `IO.println()` — Java 25

Java 25 also introduced `java.lang.IO` for basic console I/O.

You can write:

```java
void main() {
    IO.println("Hello Java");
}
```

instead of:

```java
System.out.println("Hello Java");
```

Oracle describes `IO` as providing simpler basic line-oriented console I/O. :chatgpt-content-reference{index="9"}

For your learning:

```java
System.out.println("Hello");
```

### Traditional/professional syntax

and:

```java
IO.println("Hello");
```

### Modern compact-program syntax

Know **both**.

---

# 25. Current Java Landscape

Since you specifically want modern Java included, we'll track the important versions.

| Version | Important language feature |
|---|---|
| Java 8 | Lambdas, Streams |
| Java 9 | Modules |
| Java 10 | `var` |
| Java 11 | `var` in lambda parameters |
| Java 14 | Switch expressions |
| Java 15 | Text blocks |
| Java 16 | Pattern matching `instanceof`, Records |
| Java 17 | Sealed classes |
| Java 21 | Record patterns, Pattern matching for `switch` |
| Java 22 | Unnamed variables/patterns |
| Java 25 | Compact source files, instance `main`, module imports, flexible constructor bodies |
| Java 26 | Primitive types in patterns — preview |
| Java 27 | Current Java SE release; preview features may vary |

Oracle's current language-change summary lists these developments through Java 26, and Java SE specifications identify Java 27 as released in September 2026. :chatgpt-content-reference{index="10"}

**Important:** we'll only study a feature as part of the roadmap when we reach the section where it naturally belongs.

For example:

```text
Records
   ↓
Classes / OOP

Sealed Classes
   ↓
OOP

Pattern Matching
   ↓
Conditionals / Type Casting / OOP

Virtual Threads
   ↓
Concurrency

Streams
   ↓
Functional Programming
```

This prevents the roadmap from becoming a random list of Java features.

---

# 26. Errors You Should Recognize

## Compile-time error

```java
int age = "23";
```

Compiler catches this.

```text
Source Code
    ↓
Compiler
    ↓
❌ compilation error
```

---

## Runtime error

```java
int x = 10 / 0;
```

This compiles but fails while executing.

```text
Source
 ↓
Compile ✓
 ↓
Run
 ↓
❌ Runtime exception
```

---

## Logical error

```java
int price = 100;
int quantity = 5;

int total = price + quantity;  // logically wrong
```

It may compile and execute, but the result isn't what you intended.

Understanding these three categories is important for debugging backend applications.

---

# 27. Backend Example

Eventually your Java code will look more like:

```java
public class PaymentService {

    public void processPayment(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        System.out.println("Processing payment: " + amount);
    }
}
```

Notice how everything we just revised appears:

```text
class
 ↓
method
 ↓
parameters
 ↓
block
 ↓
condition
 ↓
statement
 ↓
exception
```

Later, in Spring Boot, the same basic syntax becomes:

```java
@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {

        // business logic

    }
}
```

The syntax doesn't fundamentally change; you're simply using more advanced Java and frameworks.

---

# 28. 🎯 Interview Questions

For this section, make sure you can answer these **without memorizing definitions**.

### Basic

1. What is a class?
2. What is a method?
3. What is a statement?
4. What is an expression?
5. Why do we use `{}`?
6. Why is Java case-sensitive?
7. What is an identifier?
8. What is a keyword?

### Intermediate

9. Why is `main()` traditionally `static`?
10. What does `String[] args` represent?
11. Difference between `javac` and `java`?
12. What happens when you execute a `.class` file?
13. Difference between compile-time, runtime, and logical errors?
14. What is the difference between a class and an object?
15. Why can't `var` normally be used for fields?
16. Is `var` dynamically typed?

### Modern Java

17. What are compact source files?
18. What is an implicitly declared class?
19. What is an instance `main()`?
20. What is the difference between traditional `main()` and Java 25's instance `main()`?
21. What is `IO.println()`?
22. What are text blocks?
23. Which of these Java 25 features are permanent rather than preview?

---

# 29. 🧠 What You Should Be Able to Explain Now

Don't just memorize syntax. You should be able to explain this:

```java
public class Main {

    public static void main(String[] args) {

        var message = """
                Hello Java
                Welcome to backend development
                """;

        System.out.println(message);
    }
}
```

You should know:

```text
public
   ↓
access modifier

class
   ↓
defines a class

Main
   ↓
class identifier

static
   ↓
class-level member

void
   ↓
no return value

main
   ↓
launch method

String[] args
   ↓
command-line arguments

var
   ↓
local variable type inference

""" ... """
   ↓
text block

System.out.println()
   ↓
console output
```

And you should understand **why** each piece exists.

---

# 30. 🆕 Modern Version

You should also recognize:

```java
void main() {

    IO.println("Hello Java");

}
```

as valid modern Java 25+ syntax.

But remember:

> **Learn the new syntax; don't abandon the traditional syntax.**

For your backend career, you need to be comfortable reading existing Java codebases, which will heavily use the traditional class/method structure.

---

# 31. 📌 Final Revision Sheet

### Java program structure

```text
Class
 ↓
Method
 ↓
Statements
 ↓
Expressions
```

### Important syntax

```java
class Main {}

public static void main(String[] args) {}

int x = 10;

System.out.println("Hello");

// comment

/*
   comment
*/
```

### Modern Java

```java
var name = "Sandesh";
```

```java
String json = """
        {
          "name": "Sandesh"
        }
        """;
```

Java 25+:

```java
void main() {
    IO.println("Hello");
}
```

### Compilation

```text
.java
 ↓
javac
 ↓
.class / bytecode
 ↓
JVM
 ↓
execution
```

### Current version awareness

```text
Java 25 → LTS
Java 26 → released
Java 27 → current Java SE release
```

Java 26 currently has **primitive types in patterns, `instanceof`, and `switch` as a preview feature**, so we will not treat that as a normal permanent language feature. :chatgpt-content-reference{index="11"}

---

# 🧪 Basic Syntax Checkpoint

Since you already have intermediate Java knowledge, let's test **understanding rather than syntax memorization**.

### Q1

Why is this valid?

```java
var x = 10;
```

but this isn't?

```java
var x;
```

### Q2

Why is this traditionally required?

```java
public static void main(String[] args)
```

What does each of the four major components mean?

### Q3

What's the difference between:

```java
javac Main.java
```

and:

```bash
java Main
```

### Q4

Is this dynamically typed?

```java
var value = 100;
```

Explain why.

### Q5

What is happening conceptually here?

```java
void main() {
    IO.println("Hello");
}
```

How is it different from:

```java
public static void main(String[] args) {
    System.out.println("Hello");
}
```

---

**Once you answer these 5, we'll consider Basic Syntax revised and move to the next roadmap section: _Data Types_.**