package com.rockthejvm.part1basics

object StringOps {

  val aString = "Hello, I am learning Scala"

  // typical string methods
  val secondChar = aString.charAt(1) // 'e'
  val firstWord = aString.substring(0, 5) // "Hello"
  val words = aString.split(" ") // Array("Hello", "I", "am", "learning", "Scala")
  val startsWithHello = aString.startsWith("Hello") // true
  val dashed = aString.replace(' ', '-') // Hello,-I-am-learning-Scala
  val uppered = aString.toUpperCase()
  val lowered = aString.toLowerCase()
  val len = aString.length

  // non-standard scala string methods
  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10)

  // parse to numeric
  val numericString = "2"
  val num = numericString.toInt

  // s-interpolation
  val aName = "Alice"
  val anAge = 12
  val aGreeting = s"Hello, my name is $aName and I am $anAge years old."
  val anotherGreeting = s"Hello, I am $aName and I will be turning ${anAge + 1} soon."

  // f-interpolation
  val speed = 1.2f
  val myth = f"$aName can eat $speed%2.2f burgers per minute."

  // raw-interpolation
  val escapes = raw"This is a \n newline" // escape sequences are ignored

  def main(args: Array[String]): Unit = {

  }
}
