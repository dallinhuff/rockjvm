package com.rockthejvm.part2oop

object Objects {

  object MySingleton { // type + the only instance of the type
    val aField = 45
    def aMethod(x: Int): Int = x + 1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton

  val theField = MySingleton.aField
  val theField2 = theSingleton.aField
  val isSameField = theField == theField2

  // "object" in scala refers to one of these singleton instances
  // not to be confused with instances of classes (commonly called "objects" in OOP literature)

  class Person(name: String) {
    // instance-dependent functionality
    def sayHi(): String = s"Hi, my name is $name"
  }

  // "companions" = class + object with the same name in the same file
  object Person {
    // instance-independent functionality ("static"/class-level)
    // can access each other's private fields & methods
    infix def named(name: String): Person = new Person(name)
  }

  val alice = Person named "Alice"
  val alice2 = Person named "Alice"

  // equality
  // 1. equality of reference ("identity") (usually defined '==') (in scala, 'eq')
  val sameAlice = alice eq alice2 // false
  val sameSingleton = theSingleton eq anotherSingleton

  // 2. equality of value ("sameness") (in Java, 'equals')
  // default implementation IS equality of reference
  val sameAlice_v2 = alice equals alice2
  val sameAlice_v3 = alice == alice2
  val sameSingleton_v2 = theSingleton == anotherSingleton

  // objects can extend classes
  object BigFoot extends Person("Big Foot") // only one instance of BigFoot

  // Scala Application = object + def main(args: Array[String]): Unit
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
    println(isSameField)
  }
}
