package com.rockthejvm.part3fp

object AnonFuncs {

  // lambdas - anonymous function instances
  val doubler = (x: Int) => x * 2
  val adder = (x: Int, y: Int) => x + y

  // zero-arg functions
  val doSomething = () => 45
  val anInvocation = doSomething()

  // alt syntax with curly braces
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // type inference
  val doubler_v2: Int => Int = x => x * 2

  // shortest lambdas
  val doubler_v3: Int => Int = _ * 2
  val adder_v2: (Int, Int) => Int = _ + _
  // each underscore is a different argument


  def main(args: Array[String]): Unit = {

  }
}
