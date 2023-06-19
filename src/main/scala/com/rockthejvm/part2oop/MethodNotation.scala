package com.rockthejvm.part2oop

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    infix def likes(movie: String): Boolean =
      movie == favoriteMovie

    infix def +(person: Person): String =
      s"$name is hanging out with ${person.name}"

    infix def +(nickname: String): Person =
      new Person(s"$name $nickname", age, favoriteMovie)

    infix def !!(programmingLang: String): String =
      s"$name wonders how $programmingLang can be so cool"

    // prefix position
    // unary ops: -, +, ~, !
    def unary_- : String = s"$name's alter ego"
    def unary_+ : Person = new Person(name, age + 1, favoriteMovie)

    def apply(): String = s"Hi, my name is $name and I really enjoy $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times time(s)"
  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")


  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))
    println(mary likes "Fight Club") // infix notation (for methods with one arg)
    println(mary + john)
    println(mary.+(john))
    println(mary !! "Scala")
    println(-mary)
    println(mary.apply())
    println(mary()) // same as mary.apply()
    println(mary(2))
  }
}
