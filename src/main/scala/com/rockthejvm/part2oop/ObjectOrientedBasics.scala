package com.rockthejvm.part2oop

object ObjectOrientedBasics {

  // classes
  class Person(val name: String, age: Int) { // constructor signature
    // fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name!"

    // same method name, but different signature (overloading)
    def greet(): String =
      s"Hi, everyone! my name is $name."

    // auxiliary constructor (can use default args instead)
    def this(name: String) =
      this(name, 0)

    def this() = this("Jane Doe")
  }

  val aPerson = new Person("Alice", 12)
  val alice = aPerson.name
  val aliceYelling = aPerson.allCaps
  val aliceGreetsDaniel = aPerson.greet("Daniel")
  val aliceSaysHi = aPerson.greet()

  def main(args: Array[String]): Unit = {
    println(aliceGreetsDaniel)
    println(aliceSaysHi)
  }
}
