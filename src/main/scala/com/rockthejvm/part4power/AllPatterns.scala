package com.rockthejvm.part4power

import com.rockthejvm.practice.{Cons, LList, Empty}

object AllPatterns {

  object MySingleton

  // 1 - constants
  val someValue: Any = "Scala"
  val constants: String = someValue match {
    case 42 => "A number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case MySingleton => "a singleton object"
  }

  // 2 - match anything
  val matchAnythingVar: String = 2 + 3 match {
    case something => s"I've matched anything, it's $something"
  }

  val matchAnything: String = someValue match {
    case _ => "I can match anything at all"
  }

  // 3 - tuples
  val aTuple: (Int, Int) = (1, 4)
  val matchedTuple: String = aTuple match {
    case (1, somethingElse) => s"A tuple with 1 and $somethingElse"
    case (_, 2) => "A tuple with 2 as its second field"
  }

  // nested
  val nestedTuple: (Int, (Int, Int)) = (1, (2, 3))
  val matchedNestedTuple: String = nestedTuple match {
    case (_, (2, v)) => "A nested tuple..."
  }

  // 4 - case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchList: String = aList match {
    case Empty() => "an empty list"
    case Cons(head, Cons(_, tail)) => s"non-empty list starting with $head"
  }

  val anOption: Option[Int] = Option(2)
  val matchOption: String = anOption match {
    case None => "an empty option"
    case Some(v) => s"a full option with $v"
  }

  // 5 - List patterns
  val aStdList: List[Int] = List(1, 2, 3, 42)
  val matchStdList: String = aStdList match {
    case List(1, _, _, _) => "List with 4 elements, first is 1"
    case List(1, _*) => "List starting with one"
    case List(1, 2, _) :+ 42 => "List starting with 1, 2 and ending with 42"
    case head :: tail => s"List starting with $head"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped: String = unknown match {
    case anInt: Int => s"I matched an int, I can add 2 to it: ${anInt + 2}"
    case aString: String => "I matched a string"
    case _: Double => "I matched a double I don't care about"
  }

  // 7 - name binding
  val bindingNames: String = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"can use $rest"
  }

  // 8 - chained patterns
  val multiMatch: String = aList match {
    case Empty() | Cons(0, _) => "an empty list to me"
    case _ => "something else"
  }

  // 9 - if guards
  val secondElementSpecial: String = aList match {
    case Cons(_, Cons(special, _)) if special > 5 => "second element is big enough"
    case _ => "not big enough"
  }

  // An anti-pattern
  val aSimpleInt: Int = 45
  val isEvenBad: Boolean = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  val isEvenBad_v2: Boolean = if (aSimpleInt % 2 == 0) true else false

  // better
  val isEven: Boolean = aSimpleInt % 2 == 0

  // this doesn't work as expected, because pattern matching is
  // evaluated at runtime using reflection
  // generic types are erased at runtime (i.e., List[Int] == List[String])
  val numbers = List(1, 2, 3, 4)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of numbers"
  }

  def main(args: Array[String]): Unit = {
    println(numbersMatch)
  }
}
