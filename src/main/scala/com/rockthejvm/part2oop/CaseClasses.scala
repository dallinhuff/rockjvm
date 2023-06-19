package com.rockthejvm.part2oop

object CaseClasses {

  case class Person(name: String, age: Int)

  // CASE CLASSES
  // 1> constructor args are automatically promoted to fields
  val aPerson = Person("Daniel", 37)
  val danielAge = aPerson.age

  // 2> toString, equals, hashCode are automatically implemented
  val danielString = aPerson.toString
  val danielDuped = Person("Daniel", 37)
  val sameDaniel = aPerson == danielDuped

  // 3> utility methods
  val danielYounger = aPerson.copy(age = 35)

  // 4> companion objects

  // 5> serializable

  // 6> extractor patterns for pattern matching

  // case classes must have argument lists because they are compared by field
  // case objects can be created with no args
  case object UnitedKingdom {
    def name: String = "The United Kingdom of Great Britain and Northern Ireland"

  }

  def main(args: Array[String]): Unit = {

  }
}
