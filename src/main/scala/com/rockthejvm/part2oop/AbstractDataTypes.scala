package com.rockthejvm.part2oop

object AbstractDataTypes {

  // cannot be instantiated
  abstract class Animal {
    val creatureType: String // abstract
    def eat(): Unit // abstract
    def preferredMeal: String = "anything" // concrete accessor method
  }

  // children of abstract classes must override abstract fields/methods
  // or be abstract as well
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat(): Unit = println("mmm, I like this bone")

    // overriding accessor method with a field
    override val preferredMeal: String = "dog food"
  }

  // traits: abstract data types that describe behavior (analogous to java interfaces)
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
  }

  // practical differences between abstract classes & traits
  // one class inheritance
  // multiple trait inheritance
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat(): Unit = println("I'm a croc, I'm crunching")

    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  // philosophical difference
  // a Crocodile is an Animal (class inheritance)
  // classes are THINGS
  // a crocodile does what a cold-blooded carnivore does (trait inheritance)
  // traits are BEHAVIORS

  // all classes extend AnyRef
  // all primitives extend AnyVal

  def main(args: Array[String]): Unit = {

  }
}
