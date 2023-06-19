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

  // Exercise 1
  class Writer(firstName: String, lastName: String, val year: Int) {
    def fullName: String = s"$firstName $lastName"
  }

  class Novel(name: String, year: Int, author: Writer) {
    def authorAge: Int = year - author.year
    def isWrittenBy(author: Writer): Boolean = this.author == author
    def copy(newYear: Int) = new Novel(name, newYear, author)
  }

  // Exercise 2
  class Counter(count: Int = 0) {
    def increment(): Counter = new Counter(count + 1)
    def increment(n: Int): Counter =
      if n <= 0 then this
      else increment().increment(n - 1)

    def decrement(): Counter =
      if count <= 0 then this
      else new Counter(count - 1)

    def decrement(n: Int): Counter =
      if count <= 0 || n <= 0 then this
      else decrement().decrement(n - 1)

    def print(): Unit = println(s"The count is: $count")
  }

  def main(args: Array[String]): Unit = {
    println(aliceGreetsDaniel)
    println(aliceSaysHi)

    val charlesDickens = new Writer("Charles", "Dickens", 1812)
    println(charlesDickens.fullName)
    val impostor = new Writer("Charles", "Dickens", 1812)

    val novel = new Novel("Great Expectations", 1860, charlesDickens)
    println(novel.authorAge)

    val writtenByDickens = novel.isWrittenBy(charlesDickens)
    val writtenByImpostor = novel.isWrittenBy(impostor)
    println(writtenByDickens)
    println(writtenByImpostor)

    val newEdition = novel.copy(1871)
    println(newEdition.authorAge)

    val counter = new Counter()
    counter.print() // 0
    counter.increment().print() // 1
    counter.increment() // new Counter(1)
    counter.print() // 0
    val otherCounter = counter.increment(6)
    otherCounter.print()
    otherCounter.decrement(3).print()
  }
}
