package com.rockthejvm.part4power

import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val aValue: Int = random.nextInt(100)

  val description: String = aValue match {
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $aValue"
  }

  // decompose values
  case class Person(name: String, age: Int)
  val bob: Person = Person("Bob", 20)
  val jen: Person = Person("Jen", 14)

  val greeting: Person => String = {
    case Person(n, a) if a < 18 => s"Hello! My name is $n and I am $a years old."
    case Person(n, a) => s"Hello! My name is $n and I'm not allowed to say my age."
    case _ => "I don't know who I am"
  }

  /**
   * patterns are matched in order, so put most specific cases first
   * if no cases are matched, throws MatchError
   * type returned is the lowest common ancestor of all of the arms of the match block
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Labradoodle")
  val animalPM: String = anAnimal match {
    case Dog(b) => "I've detected a dog"
    case Cat(m) => "I've detected a cat"
    // exhaustive since Animal is a sealed class
  }

  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Number(n) => s"$n"
    case Sum(left, right) => show(left) + " + " + show(right)
    case Prod(left, right) =>
      def maybeShowParens(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case Sum(_, _) => s"(${show(exp)})"
      }

      maybeShowParens(left) + " * " + maybeShowParens(right)
  }

  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting(bob))
    println(greeting(jen))
    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
    println(show(Prod(Sum(Number(2), Number(3)), Number(4))))
    println(show(Sum(Prod(Number(2), Number(3)), Number(4))))
  }
}
