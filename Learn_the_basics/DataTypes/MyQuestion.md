# detailed explained of type promotion?
It means that when Java performs arithmetic using small integer types such as `byte`, `short`, or `char`, it automatically converts them to `int` first.

```java
byte a = 10;
byte b = 20;

var result = a + b;
```

Java treats the expression like this:

```java
int result = (int) a + (int) b;
```

So `result` becomes `int`.

Why? A `byte` can hold only `-128` to `127`. During addition, the result might be outside that range:

```java
byte a = 100;
byte b = 100;

// Result is 200, which cannot fit in byte
```

Therefore, Java uses `int` for safer arithmetic.

This will give an error:

```java
byte result = a + b; // Error: int cannot be converted to byte
```

If you are certain the result fits in a `byte`, cast it:

```java
byte result = (byte) (a + b);
```

The same promotion happens for `byte`, `short`, and `char`.

---

# but my question why does promotion happens with only int why not long?

Java does not promote based on how large the actual values are. It promotes based on the **declared data types**, at compile time.

For `byte`, `short`, and `char`, Java’s rule is:

```text
byte / short / char arithmetic → int
```

Java chose `int` as the default arithmetic type because it is the normal 32-bit integer type used by the JVM and CPU. Promoting every calculation to `long` would use a larger 64-bit value even when most calculations do not need it.

So Java uses this fixed rule:

```text
byte + byte   → int
short + short → int
char + char   → int
int + int     → int
long + int    → long
long + long   → long
```

Java does **not** inspect values like `2_000_000_000 + 2_000_000_000` and decide, “This needs `long`.” The type must already be decided before the program runs.

If you need `long`, tell Java explicitly:

```java
long c = (long) a + b;
```

The promotion from `byte` to `int` is a language rule, not a guarantee that Java will automatically prevent every overflow.

---

# what is mean by integer caching

**Integer caching** means Java reuses certain commonly used `Integer` objects instead of creating new ones every time.

Java usually caches `Integer` values from **-128 to 127**.

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b); // true
```

Both references usually point to the same cached `Integer(100)` object.

```text
a ──┐
    ├──► Integer object: 100
b ──┘
```

But for values outside that range:

```java
Integer x = 200;
Integer y = 200;

System.out.println(x == y); // false
```

Here, Java normally creates two different `Integer` objects:

```text
x ──► Integer object: 200
y ──► Integer object: 200
```

The values are equal, but the objects are different.

```java
System.out.println(x.equals(y)); // true
```

So use `.equals()` to compare `Integer` values. Use `==` only when you intentionally want to check whether two references point to the exact same object.

