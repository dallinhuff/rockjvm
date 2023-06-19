package com.rockthejvm.part2oop

object Inheritance {

  class Animal {
    val creatureType = "Wild"
    def eat(): Unit = println("nomnomnom")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch, crunch")
    }
  }

  class Dog extends Animal {
    override val creatureType: String = "Domestic"
    override def eat(): Unit = println("mmm, I like this bone")

    override def toString: String = "a dog"
  }

  class Crocodile extends Animal {
    override val creatureType: String = "Very Wild"
    override def eat(): Unit = println("I can eat whatever I want, I'm a croc")

    // overloading: different signature + [different return type]
    def eat(animal: Animal): Unit = println("I'm eating this fella")
  }

  val cat = new Cat

  // subtype polymorphism (a Dog is an Animal)
  val dog: Animal = new Dog

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) {
  }

  def main(args: Array[String]): Unit = {
    cat.eat()
    cat.crunch()
    dog.eat() // the most specific method will be called
    println(dog)
  }
}
