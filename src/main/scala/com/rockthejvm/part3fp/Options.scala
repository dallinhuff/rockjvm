package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  // options: "collections" with at most one value
  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty

  // useful for pattern matching, but usually should use constructors above
  val aPresentOption: Option[Int] = Some(4)
  val anEmptyOption_v2: Option[Int] = None

  // extracting values
  val isEmpty: Boolean = anOption.isEmpty
  val innerValue: Int = anOption.getOrElse(99)
  val aChainedOption: Option[Int] = anEmptyOption.orElse(aPresentOption)

  // map, flatMap, etc.
  val anIncrementedOption: Option[Int] = anOption.map(_ + 1) // Some(43)
  val aFilteredOption: Option[Int] = anIncrementedOption.filter(_ % 2 == 0) // None
  val aFlatMappedOption: Option[Int] = anOption.flatMap(value => Option(value * 10)) // Some(420)



  // work with unsafe APIs
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "a valid result"

  // defensive style
  val strLen: Int = {
    val unsafeString = unsafeMethod()
    if (unsafeString == null) -1
    else unsafeString.length
  }

  // option style: no need for null checks
  val strLen_opt: Option[Int] = Option(unsafeMethod()).map(_.length)
  val someResult: Option[String] = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN
  def betterUnsafeMethod(): Option[String] = None
  def betterFallback(): Option[String] = Some("a valid result")
  val betterChainedOption: Option[String] = betterUnsafeMethod().orElse(betterFallback())

  // example: map.get
  val phonebook: Map[String, Int] = Map("Daniel" -> 1234)
  val maryNumber: Option[Int] = phonebook.get("Mary") // None

  val config: Map[String, String] = Map(
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection() {
    def connect(): String = "Connection successful"
  }

  object Connection {
    val random = new Random()
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  def main(args: Array[String]): Unit = {
    for (_ <- 1 to 10) {
      val host = config.get("host")
      val port = config.get("port")

      val conn = host.flatMap(h => port.flatMap(p => Connection(h, p)))

      val connStatus = for {
        h <- config.get("host")
        p <- config.get("port")
        conn <- Connection(h, p)
      } yield conn.connect()

      println(connStatus.getOrElse("Connection failed"))
    }
  }
}
