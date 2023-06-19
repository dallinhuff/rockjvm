package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm a weird animal")
  }

  val someAnimal = new SomeAnimal
  val someAnimal_v2 = new Animal {
    override def eat(): Unit = println("I'm an anonymous weird animal")
  }

  def main(args: Array[String]): Unit = {

  }
}
