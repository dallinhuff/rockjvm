package com.rockthejvm.part3fp

object TuplesMaps {

  // tuples = finite ordered "lists"
  val aTuple: (Int, String) = (2, "RockTheJVM") // Tuple2[Int, String] == (Int, String)
  val firstField: Int = aTuple._1
  val aCopiedTuple: (Int, String) = aTuple.copy(_1 = 54)

  // tuples of 2 elements can be written as "associations"
  val aTuple_v2: (Int, String) = 2 -> "RockTheJVM"

  // maps: keys -> values
  val phoneBook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  ).withDefaultValue(-1)

  // core APIs
  val phoneBookHasDaniel = phoneBook.contains("Daniel")

  // -1, would crash with NoSuchElementException if didn't supply default value
  val marysPhoneNumber = phoneBook("Mary")

  // add an entry
  val newPair: (String, Int) = "Mary" -> 678
  val newPhoneBook: Map[String, Int] = phoneBook + newPair

  // remove a key
  val phoneBookWithoutDaniel: Map[String, Int] = phoneBook - "Daniel"

  val linearPhoneBook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )

  val phoneBook_v2: Map[String, Int] = linearPhoneBook.toMap

  val linearPhoneBook_v2: List[(String, Int)] = phoneBook.toList // toSeq, toVector, toArray, toSet, etc.

  // map, flatMap, filter, etc
  // usually a bad idea to change the keys
  val aProcessedPhoneBook: Map[String, Int] = phoneBook.map(pair => (pair._1.toUpperCase(), pair._2))

  // filtering keys & mapping values
  val phoneBookNoJ: Map[String, Int] = phoneBook.view.filterKeys(!_.startsWith("J")).toMap
  val prefixedNumbers: Map[String, String] = phoneBook.view.mapValues(num => s"0255-$num").toMap

  // other collections can create maps
  val names: List[String] = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings: Map[Char, List[String]] = names.groupBy(_.charAt(0))

  def main(args: Array[String]): Unit = {
    println(phoneBook)
    println(phoneBookHasDaniel)
    println(marysPhoneNumber)
    println(nameGroupings)
  }
}
