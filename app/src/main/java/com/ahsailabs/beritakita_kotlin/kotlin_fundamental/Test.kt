package com.ahsailabs.beritakita_kotlin.kotlin_fundamental

/**
 * Created by ahmad s on 25/03/21.
 */

fun main(args: Array<String>) {
    val name: String = "John"
    println(name)

    var myNum: Int = 0
    myNum = 15
    println(myNum)

    var x: Int = 100 + 50

    val time = 22
    if (time < 10) {
        println("Good morning.")
    } else if (time < 20) {
        println("Good day.")
    } else {
        println("Good evening.")
    }
    // Outputs "Good evening."

    val time2 = 20
    val result = if (time2 < 18) "Good day." else "Good evening."
    println(result)


    val day = 4
    when (day) {
        6 -> println("Today is Saturday")
        7 -> println("Today is Sunday")
        else -> println("Looking forward to the Weekend")
    }
    // Outputs "Looking forward to the Weekend"


    // Outputs "Looking forward to the Weekend"
    var i = 0
    while (i < 5) {
        println(i)
        i++
    }


    var j = 0
    do {
        println(j)
        j++
    } while (j < 5)


    for (k in 0..4) {
        println(k)
    }

    val items = listOf(1, 22, 83, 4)

    for ((index, value) in items.withIndex()) {
        println("the element at $index is $value")
    }



    for (l in 0..9) {
        if (l == 4) {
            break
        }
        println(l)
    }

    for (m in 0..9) {
        if (m == 4) {
            continue
        }
        println(m)
    }

    //val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5)​
    //println("Hey!! I am array Example" + numbers[2])​

    val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")

    println(cars[0])

    cars[0] = "Opel"
    println(cars[0])
    // Now outputs Opel instead of Volvo


    println(cars.size)
    // Outputs 4

    for (n in cars.indices) {
        println(cars[n])
    }

    for (car in cars) {
        println(car)
    }

    val myNumbers = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7))
    val chosenNumber = myNumbers[1][2]
    println(chosenNumber) // Outputs 7

}
