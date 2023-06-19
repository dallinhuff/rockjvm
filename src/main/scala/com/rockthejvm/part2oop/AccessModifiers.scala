package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String) {
    protected def sayHi(): String = s"Hi, my name is $name"
    private def watchNetflix(): String = "I am watching my favorite series"
  }

  class Kid(name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = sayHi() + s" and I am $age years old"
  }
  val aKid = new Kid("Alice", 5)

  class KidWithParents(name: String, age: Int, momName: String, dadName: String) extends Kid(name, age) {
    val mom = new Person(momName)
    val dad = new Person(dadName)
    override def sayHi(): String = {
      // doesn't work because we can't access sayHi on mom and dad
      // s"Hi, I'm $name and here are my parents: ${mom.sayHi()}, ${dad.sayHi()}"
      ""
    }
  }

  def main(args: Array[String]): Unit = {
    println(aKid.greetPolitely())
  }
}
