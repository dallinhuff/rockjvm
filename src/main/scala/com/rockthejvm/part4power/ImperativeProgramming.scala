package com.rockthejvm.part4power

object ImperativeProgramming {

  val meaningOfLife: Int = 42 // immutable, cannot reassign

  var aVariable: Int = 99 // mutable, supports reassignment (of same type)
  aVariable += 10
  aVariable = 22

  def testLoop(): Unit = {
    // loops
    var i = 0
    while (i < 10) {
      println(s"counter at $i")
      i += 1
    }
  }

  // loops & imperative programming make code harder to read & reason about as codebase grows
  // also are vulnerable to classical concurrency problems
  // using immutable data and functional paradigm can help a lot with concurrency and distributed systems

  // should only use imperative Scala for:
  // performance critical applications (really rarely the difference-maker)
  // interfacing with Java libraries

  // using imperative style (for no good reason) largely defeats the purpose of Scala

  // imperative statements are actually expressions that return Unit
  val anExpression: Unit = aVariable += 10

  def main(args: Array[String]): Unit = {
    testLoop()
  }
}
