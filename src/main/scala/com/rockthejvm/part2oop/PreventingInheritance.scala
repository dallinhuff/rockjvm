package com.rockthejvm.part2oop

object PreventingInheritance {

  class Person(name: String) {
    final def enjoyLife(): Int = 42 // cannot be overridden by subclasses
  }

  class Adult(name: String) extends Person(name)

  final class Animal // cannot be extended
  // class Cat extends Animal // illegal

  // sealing a type hierarchy = inheritance only permitted inside this file
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier = "not recommended for extension"
  // marking for extension with 'open' is not mandatory, but good practice
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {

  }
}
