package com.ahsailabs.beritakita_kotlin.kotlin_fundamental

import java.io.File
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.text.*
/**
 * Created by ahmad s on 25/03/21.
 */

@ExperimentalStdlibApi
fun mains(args: Array<String>) {
    print("Hello ")
    print("world!")

    //val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment

    //var x = 5 // `Int` type is inferred
    //x += 1

    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")

    // This is an end-of-line comment

    /* This is a block comment
    on multiple lines. */

    /* The comment starts here
    /* contains a nested comment */
    and ends here. */

    var a = 1
    // simple name in template:
    val s1 = "a is $a"

    a = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"

//    val items = listOf("apple", "banana", "kiwifruit")
//    for (item in items) {
//        println(item)
//    }

//    val items = listOf("apple", "banana", "kiwifruit")
//    for (index in items.indices) {
//        println("item at $index is ${items[index]}")
//    }

    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }


    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    for (x in 1..5) {
        print(x)
    }

    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

    for (item in items) {
        println(item)
    }

    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.uppercase() }
            .forEach { println(it) }


    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1

    val pi = 3.14 // Double
    // val one: Double = 1 // Error: type mismatch
    val oneDouble = 1.0 // Double

    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float, actual value is 2.7182817

    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010




}

@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
fun main2(){
//    val x = 5 / 2
//    //println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
//    println(x == 2)

//    val x = 5L / 2
//    println(x == 2L)

    val x = 5 / 2.toDouble()
    println(x == 2.5)

    val b: UByte = 1u  // UByte, expected type provided
    //val s: UShort = 1u // UShort, expected type provided
    val l: ULong = 1u  // ULong, expected type provided

    val a1 = 42u // UInt: no expected type provided, constant fits in UInt
    val a2 = 0xFFFF_FFFF_FFFFu // ULong: no expected type provided, constant doesn't fit in UInt

    val a = 1UL // ULong, even though no expected type provided and constant fits into UInt

    val myTrue: Boolean = true
    val myFalse: Boolean = false
    val boolNull: Boolean? = null

    println(myTrue || myFalse)
    println(myTrue && myFalse)
    println(!myTrue)

    val aChar: Char = 'a'

    println(aChar)
    println('\n') //prints an extra newline character
    println('\uFF00')

    //val str = "abcd 123"

    //for (c in str) {
        //println(c)
    //}

    val str = "abcd"
    println(str.uppercase()) // Create and print a new String object
    println(str) // the original string remains the same

    //s = "abc" + 1
    //println(s + "def")

    //val s = "Hello, world!\n"

    val text = """
        for (c in "foo")
            print(c)
        """

    val i = 10
    println("i = $i") // prints "i = 10"

    val s = "abc"
    println("$s.length is ${s.length}") // prints "abc.length is 3"
    val price = """
        ${'$'}_9.99
        """

    // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }




}

fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }



open class Shape

class Rectangle(var height: Double, var length: Double): Shape() {
    var perimeter = (height + length) * 2
}

//
//class Rectangle(var height: Double, var length: Double) {
//    var perimeter = (height + length) * 2
//}

val PI = 3.14
var x = 0

fun incrementX() {
    x += 1
}
//
//fun sum(a: Int, b: Int): Int {
//    return a + b
//}

fun sum(a: Int, b: Int) = a + b

//
//fun printSum(a: Int, b: Int): Unit {
//    println("sum of $a and $b is ${a + b}")
//}

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

//fun maxOf(a: Int, b: Int): Int {
//    if (a > b) {
//        return a
//    } else {
//        return b
//    }
//}
fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun parseInt(str: String): Int? {
    return null
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }

    // ...
    if (x == null) {
        println("Wrong number format in arg1: '$arg1'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '$arg2'")
        return
    }

    // x and y are automatically cast to non-nullable after null check
    println(x * y)



}


//fun getStringLength(obj: Any): Int? {
//    if (obj is String) {
//        // `obj` is automatically cast to `String` in this branch
//        return obj.length
//    }
//
//    // `obj` is still of type `Any` outside of the type-checked branch
//    return null
//
//}

//fun getStringLength(obj: Any): Int? {
//    if (obj !is String) return null
//
//    // `obj` is automatically cast to `String` in this branch
//    return obj.length
//}

fun getStringLength(obj: Any): Int? {
    // `obj` is automatically cast to `String` on the right-hand side of `&&`
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}


fun main3(){
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]

    // Array of int of size 5 with values [0, 0, 0, 0, 0]
    val arr1 = IntArray(5)

    // e.g. initialise the values in the array with a constant
    // Array of int of size 5 with values [42, 42, 42, 42, 42]
    val arr2 = IntArray(5) { 42 }

    // e.g. initialise the values in the array using a lambda
    // Array of int of size 5 with values [0, 1, 2, 3, 4] (values initialised to their index value)
    var arr3 = IntArray(5) { it * 1 }

//    if (obj is String) {
//        print(obj.length)
//    }
//
//    if (obj !is String) { // same as !(obj is String)
//        print("Not a String")
//    } else {
//        print(obj.length)
//    }


}

fun demo(x: Any) {
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }

    if (x !is String) return

    print(x.length) // x is automatically cast to String

    // x is automatically cast to string on the right-hand side of `||`
    if (x !is String || x.length == 0) return

    // x is automatically cast to string on the right-hand side of `&&`
    if (x is String && x.length > 0) {
        print(x.length) // x is automatically cast to String
    }

    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }

    val y = "hore"
    val b: String = y as String
    val c: String? = y as String?

    val x: String? = y as? String




}

fun main4(){
//    var max = a
//    if (a < b) max = b
//
//    // With else
//    var max: Int
//    if (a > b) {
//        max = a
//    } else {
//        max = b
//    }
//
//    // As expression
//    val max = if (a > b) a else b

//    val max = if (a > b) {
//        print("Choose a")
//        a
//    } else {
//        print("Choose b")
//        b
//    }

//    when (x) {
//        1 -> print("x == 1")
//        2 -> print("x == 2")
//        else -> { // Note the block
//            print("x is neither 1 nor 2")
//        }
//    }
//
//    when (x) {
//        parseInt(s) -> print("s encodes x")
//        else -> print("s does not encode x")
//    }
//
//    when (x) {
//        in 1..10 -> print("x is in the range")
//        in validNumbers -> print("x is valid")
//        !in 10..20 -> print("x is outside the range")
//        else -> print("none of the above")
//    }
//
//
//    when {
//        x.isOdd() -> print("x is odd")
//        y.isEven() -> print("y is even")
//        else -> print("x+y is odd")
//    }

//    for (item in collection) print(item)
//
//    for (i in 1..3) {
//        println(i)
//    }
//    for (i in 6 downTo 0 step 2) {
//        println(i)
//    }
//
//    for (i in array.indices) {
//        println(array[i])
//    }
//
//    for ((index, value) in array.withIndex()) {
//        println("the element at $index is $value")
//    }
//
//    while (x > 0) {
//        x--
//    }
//
//    do {
//        val y = retrieveData()
//    } while (y != null) // y is visible here!

//    loop@ for (i in 1..100) {
//        for (j in 1..100) {
//            if (...) break@loop
//        }
//    }
//
//    fun foo() {
//        listOf(1, 2, 3, 4, 5).forEach lit@{
//            if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
//            print(it)
//        }
//        print(" done with explicit label")
//    }

//    fun foo() {
//        listOf(1, 2, 3, 4, 5).forEach {
//            if (it == 3) return@forEach // local return to the caller of the lambda - the forEach loop
//            print(it)
//        }
//        print(" done with implicit label")
//    }

//    fun foo() {
//        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
//            if (value == 3) return  // local return to the caller of the anonymous function - the forEach loop
//            print(value)
//        })
//        print(" done with anonymous function")
//    }

//    fun foo() {
//        run loop@{
//            listOf(1, 2, 3, 4, 5).forEach {
//                if (it == 3) return@loop // non-local return from the lambda passed to run
//                print(it)
//            }
//        }
//        print(" done with nested loop")
//    }
//    throw Exception("Hi There!")
//    try {
//        // some code
//    } catch (e: SomeException) {
//        // handler
//    } finally {
//        // optional finally block
//    }
//    val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null }
//    val s = person.name ?: "my name"
//    println(s)

}

//fun Request.getBody() =
//        when (val response = executeRequest()) {
//            is Success -> response.body
//            is HttpError -> throw HttpException(response.status)
//        }

//fun Any?.toString(): String {
//    if (this == null) return "null"
//    // after the null check, 'this' is autocast to a non-null type, so the toString() below
//    // resolves to the member function of the Any class
//    return toString()
//}
//
//val <T> List<T>.lastIndex: Int
//    get() = size - 1
//
//class MyClass {
//    companion object { }  // will be called "Companion"
//}
//​
//fun MyClass.Companion.printCompanion() { println("companion") }
//​
//fun main() {
//    MyClass.printCompanion()
//}
//
//class Host(val hostname: String) {
//    fun printHostname() { print(hostname) }
//}
//
//class Connection(val host: Host, val port: Int) {
//    fun printPort() { print(port) }
//
//    fun Host.printConnectionString() {
//        printHostname()   // calls Host.printHostname()
//        print(":")
//        printPort()   // calls Connection.printPort()
//    }
//
//    fun connect() {
//        /*...*/
//        host.printConnectionString()   // calls the extension function
//
//    }
//}
//
//
//
//data class User(val name: String, val age: Int)
//
//fun main() {
//    Connection(Host("kotl.in"), 443).connect()
//    //Host("kotl.in").printConnectionString(443)  // error, the extension function is unavailable outside Connection
//}

//class Example {
//    fun printFunctionType() { println("Class method") }
//}
//
//fun Example.printFunctionType(i: Int) { println("Extension function") }
//
//Example().printFunctionType(1)

//fun MutableList<Int>.swap(index1: Int, index2: Int) {
//    val tmp = this[index1] // 'this' corresponds to the list
//    this[index1] = this[index2]
//    this[index2] = tmp
//}
//
//val list = mutableListOf(1, 2, 3)
//list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'

//
//fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
//    val tmp = this[index1] // 'this' corresponds to the list
//    this[index1] = this[index2]
//    this[index2] = tmp
//}

//class Array<T> private constructor() {
//    val size: Int
//    operator fun get(index: Int): T
//    operator fun set(index: Int, value: T): Unit
//
//    operator fun iterator(): Iterator<T>
//    // ...
//}
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}

class Person { /*...*/ }


class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

//class Customer(name: String) {
//    val customerKey = name.uppercase()
//}
//class Person(val firstName: String, val lastName: String, var isEmployed: Boolean = true)
//
//class Customer public @Inject constructor(name: String) { /*...*/ }


//class Person(val name: String) {
//    var children: MutableList<Person> = mutableListOf()
//    constructor(name: String, parent: Person) : this(name) {
//        parent.children.add(this)
//    }
//}

//val invoice = Invoice()
//
//val customer = Customer("Joe Smith")
//open class Polygon {
//    open fun draw() {}
//}
//
//abstract class Rectangle : Polygon() {
//    abstract override fun draw()
//}

//open class Shape {
//    open fun draw() { /*...*/ }
//    fun fill() { /*...*/ }
//}
//
//class Circle() : Shape() {
//    override fun draw() { /*...*/ }
//}

//open class Shape {
//    open val vertexCount: Int = 0
//}
//
//class Rectangle : Shape() {
//    override val vertexCount = 4
//}

//open class Rectangle {
//    open fun draw() { println("Drawing a rectangle") }
//    val borderColor: String get() = "black"
//}
//
//class FilledRectangle : Rectangle() {
//    override fun draw() {
//        super.draw()
//        println("Filling the rectangle")
//    }
//
//    val fillColor: String get() = super.borderColor
//}
//
//interface MyInterface {
//    fun bar()
//    fun foo() {
//        // optional body
//    }
//}
//
//class Child : MyInterface {
//    override fun bar() {
//        // body
//    }
//}

interface MyInterface {
    val prop: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

fun interface KRunnable {
    fun invoke()
}


fun interface IntPredicate {
    fun accept(i: Int): Boolean
}
//
//// Creating an instance of a class
//val isEven = object : IntPredicate {
//    override fun accept(i: Int): Boolean {
//        return i % 2 == 0
//    }
//}
//
//// Creating an instance using lambda
//val isEven = IntPredicate { it % 2 == 0 }


class Child : MyInterface {
    override val prop: Int = 29
}

//public class MyTest {
//    lateinit var subject: TestSubject
//
//    @SetUp fun setup() {
//        subject = TestSubject()
//    }
//
//    @Test fun test() {
//        subject.method()  // dereference directly
//    }
//}
//
//if (this::bar.isInitialized) {
//    println(this.bar)
//}

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}
//
//var stringRepresentation: String
//    get() = this.toString()
//    set(value) {
//        setDataFromString(value) // parses the string and assigns values to other properties
//    }
//var counter = 0 // the initializer assigns the backing field directly
//    set(value) {
//        if (value >= 0)
//            field = value
//        // counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
//    }
//var setterVisibility: String = "abc"
//    private set // the setter is private and has the default implementation
//
//var setterWithAnnotation: Any? = null
//    @Inject set // annotate the setter with Inject

data class User(val name: String = "", val age: Int = 0)

fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    // ...

    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    return result
}

//sealed class UiOp {
//    object Show: UiOp()
//    object Hide: UiOp()
//    class TranslateX(val px: Float): UiOp()
//    class TranslateY(val px: Float): UiOp()
//}
//fun execute(view: View, op: UiOp) = when (op) {
//    UiOp.Show -> view.visibility = View.VISIBLE
//    UiOp.Hide -> view.visibility = View.GONE
//    is UiOp.TranslateX -> view.translationX = op.px
//    is UiOp.TranslateY -> view.translationY = op.px
//}


//sealed interface Expr
//sealed class MathExpr(): Expr
//
//data class Const(val number: Double) : MathExpr()
//data class Sum(val e1: Expr, val e2: Expr) : MathExpr()
//object NotANumber : Expr
//
//
//fun eval(expr: Expr): Double = when(expr) {
//    is Const -> expr.number
//    is Sum -> eval(expr.e1) + eval(expr.e2)
//    NotANumber -> Double.NaN
//    // the `else` clause is not required because we've covered all the cases
//}

//class Box<T>(t: T) {
//    var value = t
//}
//
//val box: Box<Int> = Box<Int>(1)
//val box = Box(1) // 1 has type Int, so the compiler figures out that it is Box<Int>
//
//
//fun <T> singletonList(item: T): List<T> {
//    // ...
//}
//
//fun <T> T.basicToString(): String {  // extension function
//    // ...
//}
//
//val l = singletonList<Int>(1)


interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}

//class Outer {
//    private val bar: Int = 1
//    class Nested {
//        fun foo() = 2
//    }
//}
//
//val demo = Outer.Nested().foo() // == 2

interface OuterInterface {
    class InnerClass
    interface InnerInterface
}

class OuterClass {
    class InnerClass
    interface InnerInterface
}

//class Outer {
//    private val bar: Int = 1
//    inner class Inner {
//        fun foo() = bar
//    }
//}
//
//val demo = Outer().Inner().foo() // == 1

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//@JvmInline
//value class Password(private val s: String)
//
//// No actual instantiation of class 'Password' happens
//// At runtime 'securePassword' contains just 'String'
//val securePassword = Password("Don't try this in production")

//@JvmInline
//value class Name(val s: String) {
//    init {
//        require(s.length > 0) { }
//    }
//
//    val length: Int
//        get() = s.length
//
//    fun greet() {
//        println("Hello, $s")
//    }
//}
//
//fun main() {
//    val name = Name("Kotlin")
//    name.greet() // method `greet` is called as a static method
//    println(name.length) // property getter is called as a static method
//}

//window.addMouseListener(object : MouseAdapter() {
//    override fun mouseClicked(e: MouseEvent) { /*...*/ }
//
//    override fun mouseEntered(e: MouseEvent) { /*...*/ }
//})
//
//open class A(x: Int) {
//    public open val y: Int = x
//}
//
//interface B { /*...*/ }
//
//val ab: A = object : A(1), B {
//    override val y = 15
//}

//
//class MyClass {
//    companion object { }
//}
//
//val x = MyClass.Companion

//interface Base {
//    fun printMessage()
//    fun printMessageLine()
//}
//
//class BaseImpl(val x: Int) : Base {
//    override fun printMessage() { print(x) }
//    override fun printMessageLine() { println(x) }
//}
//
//class Derived(b: Base) : Base by b {
//    override fun printMessage() { print("abc") }
//}
//
//fun main() {
//    val b = BaseImpl(10)
//    Derived(b).printMessage()
//    Derived(b).printMessageLine()
//}
//

//fun resourceDelegate(): ReadWriteProperty<Any?, Int> =
//        object : ReadWriteProperty<Any?, Int> {
//            var curValue = 0
//            override fun getValue(thisRef: Any?, property: KProperty<*>): Int = curValue
//            override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
//                curValue = value
//            }
//        }
//
//val readOnly: Int by resourceDelegate()  // ReadWriteProperty as val
//var readWrite: Int by resourceDelegate()
//class Type

//fun double(x: Int): Int {
//    return 2 * x
//}
//
//val result = double(2)
//
//Stream().read() // create instance of class Stream and call read()
//
//fun powerOf(number: Int, exponent: Int): Int { /*...*/ }
//
//fun read(
//        b: ByteArray,
//        off: Int = 0,
//        len: Int = b.size,
//) { /*...*/ }



fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = asList(1, 2, 3)

//fun reformat(
//        str: String,
//        normalizeCase: Boolean = true,
//        upperCaseFirstLetter: Boolean = true,
//        divideByCamelHumps: Boolean = false,
//        wordSeparator: Char = ' ',
//) { /*...*/ }
//
//fun oke() {
//    reformat(
//            "String!",
//            false,
//            upperCaseFirstLetter = false,
//            divideByCamelHumps = true,
//            '_'
//    )
//}
//fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
//    var accumulator: R = initial
//    for (element: T in this) {
//        accumulator = combine(accumulator, element)
//    }
//    return accumulator
//}


class A { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver
            }

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}

//fun printLine() { println("Top-level function") }
//
//class A {
//    fun printLine() { println("Member function") }
//
//    fun invokePrintLine(omitThis: Boolean = false)  {
//        if (omitThis) printLine()
//        else this.printLine()
//    }
//}
//
//A().invokePrintLine() // Member function
//A().invokePrintLine(omitThis = true) // Top-level function

//ints.filter {
//    val shouldFilter = it > 0
//    shouldFilter
//}
//
//ints.filter {
//    val shouldFilter = it > 0
//    return@filter shouldFilter
//}

//val stringPlus: (String, String) -> String = String::plus
//val intPlus: Int.(Int) -> Int = Int::plus
//
//println(stringPlus.invoke("<-", "->"))
//println(stringPlus("Hello, ", "world!"))
//
//println(intPlus.invoke(1, 1))
//println(intPlus(1, 2))
//println(2.intPlus(3)) // extension-like call

//val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
//val twoParameters: (String, Int) -> String = repeatFun // OK
//
//fun runTransformation(f: (String, Int) -> String): String {
//    return f("hello", 3)
//}
//val result = runTransformation(repeatFun) // OK

//class MyStringCollection {
//    infix fun add(s: String) { /*...*/ }
//
//    fun build() {
//        this add "abc"   // Correct
//        add("abc")       // Correct
//        //add "abc"        // Incorrect: the receiver must be specified
//    }
//}

//typealias NodeSet = Set<Network.Node>
//
//typealias FileTable<K> = MutableMap<K, MutableList<File>>
//
//typealias MyHandler = (Int, String, Any) -> Unit
//
//typealias Predicate<T> = (T) -> Boolean

//class C {
//    var prop: Type by MyDelegate()
//}
//
//// this code is generated by the compiler instead:
//class C {
//    private val prop$delegate = MyDelegate()
//    var prop: Type
//        get() = prop$delegate.getValue(this, this::prop)
//        set(value: Type) = prop$delegate.setValue(this, this::prop, value)
//}

//val lazyValue: String by lazy {
//    println("computed!")
//    "Hello"
//}
//
//var name: String by Delegates.observable("init") {
//    prop, old, new ->
//    println("$old -> $new")
//}
//
//class MyClass {
//    var newName: Int = 0
//    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
//    var oldName: Int by this::newName
//}
//
//class User(val map: Map<String, Any?>) {
//    val name: String by map
//    val age: Int     by map
//}

//import kotlin.reflect.KProperty
//
//class UserX(val map: MutableMap<String, Any?>) {
//    var name: String by map
//    var age: Int     by map
//}
//
//class Example {
//    var p: String by Delegate()
//}
//fun doAction(){
//    val e = Example()
//    println(e.p) //print => Example@33a17727, thank you for delegating ‘p’ to me!
//
//    e.p = "NEW" //print => NEW has been assigned to ‘p’ in Example@33a17727.
//}
//
//class Delegate {
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
//        return "$thisRef, thank you for delegating '${property.name}' to me!"
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
//        println("$value has been assigned to '${property.name}' in $thisRef.")
//    }
//}

//interface Base {
//    fun print()
//}
//
//class BaseImpl(val x: Int) : Base {
//    override fun print() { print(x) }
//}
//
//class Derived(b: Base) : Base by b
//
//fun main() {
//    val b = BaseImpl(10)
//    Derived(b).print()
//}

//object DataProviderManager {
//    fun registerDataProvider(provider: DataProvider) {
//        // ...
//    }
//
//    val allDataProviders: Collection<DataProvider>
//        get() = // ...
//}
//
//DataProviderManager.registerDataProvider(...)


//fun main() {
//    val helloWorld = object {
//        val hello = "Hello"
//        val world = "World"
//        // object expressions extend Any, so `override` is required on `toString()`
//        override fun toString() = "$hello $world"
//    }
//    print(helloWorld)
//}


//interface Printable {
//    fun prettyPrint(): String
//}
//
//@JvmInline
//value class Name(val s: String) : Printable {
//    override fun prettyPrint(): String = "Let's $s!"
//}
//
//fun main() {
//    val name = Name("Kotlin")
//    println(name.prettyPrint()) // Still called as a static method
//}
//
//
//@JvmInline
//value class UInt(val x: Int)
//
//fun compute(x: Int) { }
//
//@JvmName("computeUInt")
//fun compute(x: UInt) { }

//package org.example.declarations
//
//fun List<String>.getLongestString() { /*...*/}
//
//package org.example.usage
//
//import org.example.declarations.getLongestString
//
//fun main() {
//    val list = listOf("red", "green", "blue")
//    list.getLongestString()
//}