package com.rockthejvm.practice

abstract class Maybe[A] {
  def map[B](f: A => B): Maybe[B]
  def filter(f: A => Boolean): Maybe[A]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
}

case class Nothing[A]() extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = Nothing[B]()
  override def filter(f: A => Boolean): Maybe[A] = this
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = Nothing[B]()
}

case class Just[A](value: A) extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = Just(f(value))
  override def filter(f: A => Boolean): Maybe[A] =
    if (f(value)) this
    else Nothing[A]()
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(value)
}

object MaybeTest {
  def main(args: Array[String]): Unit = {
    val maybeInt: Maybe[Int] = Just(3)
    val maybeIncrementedInt: Maybe[Int] = maybeInt.map(_ + 1)
    println(maybeIncrementedInt)

    val otherMaybe: Maybe[Int] = Nothing()
    val otherMaybeIncremented: Maybe[Int] = otherMaybe.map(_ + 1)
    println(otherMaybeIncremented)

    val maybeIntFiltered: Maybe[Int] = maybeInt.filter(_ % 2 == 0)
    println(maybeIntFiltered)

    val maybeFlatMapped: Maybe[Int] = maybeInt.flatMap(num => Just(num * 3))
    println(maybeFlatMapped)
  }
}