package com.rockthejvm.part3fp

object WhatsAFunction {

  // FP: functions are 'first-class citizens'


  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler.apply(meaningOfLife)
  val meaningDoubled_v2 = doubler(meaningDoubled)

  // function types
  val doublerStandard = new (Int => Int) {
    override def apply(arg: Int): Int = arg * 2
  }

  // all functions are instances of FunctionX with apply methods

  val adder = new ((Int, Int) => Int) {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val anAddition = adder(2, 76)

  val concat = new ((String, String) => String) {
    override def apply(str1: String, str2: String): String = str1 + str2
  }

  val superAdder: (Int) => (Int) => Int =
    (v1) => (v2) => v1 + v2
  val add1 = superAdder(1)
  val onePlusTwo = add1(2)
  println(onePlusTwo)

  val fourPlusFive = superAdder(4)(5)
  println(fourPlusFive)

  def main(args: Array[String]): Unit = {

  }
}
