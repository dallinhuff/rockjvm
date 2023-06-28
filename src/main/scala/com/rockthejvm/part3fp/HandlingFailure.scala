package com.rockthejvm.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {

  // Try - a potentially failed computation
  val aTry: Try[Int] = Try(42)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException())

  // direct instantiation of Try subtypes (usually just use try apply method)
  val aTry_v2: Try[Int] = Success(42)
  val aFailedTry_v2: Try[Int] = Failure(new RuntimeException())

  val checkSuccess: Boolean = aTry.isSuccess
  val checkFailure: Boolean = aTry.isFailure
  val aChain: Try[Int] = aFailedTry.orElse(aTry)

  val anIncrementedTry: Try[Int] = aTry.map(_ + 1)
  val aFlatMappedTry: Try[String] = aTry.flatMap(mol => Try(s"My meaning of life is $mol"))
  val aFilteredTry: Try[Int] = aTry.filter(_ % 2 == 0)

  // WHY: work with unsafe APIs that can throw exceptions
  def unsafeMethod(): String = throw new RuntimeException("bad job, buddy")

  // defensive: try-catch-finally
  val strLenDefensive: Int = try {
    val aString = unsafeMethod()
    aString.length
  } catch {
    case e: RuntimeException => -1
  }

  val strLenPure: Int = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  // DESIGN
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("bad job, buddy"))
  def betterBackup(): Try[String] = Success("Scala")
  val strLenPure_v2: Int = betterUnsafeMethod().orElse(betterBackup()).map(_.length).getOrElse(-1)

  val host: String = "localhost"
  val port: String = "8081"
  val myDesiredUrl: String = "rockthejvm.com/home"

  class Connection {
    val random = new Random()
    def get(url: String): String = {
      if random.nextBoolean() then "<html>Success</html>"
      else throw new RuntimeException("Cannot fetch page rn, try again later")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random()
    def getConnection(host: String, port: String): Connection = {
      if random.nextBoolean() then new Connection
      else throw new RuntimeException("Cannot access host-port combo")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  def main(args: Array[String]): Unit = {
    val pageContent: String =
      Try(HttpService.getConnection(host, port))
        .flatMap(conn => Try(conn.get(myDesiredUrl)))
        .fold(
          e => s"<html>${e.getMessage}</html>",
          s => s
        )

    println(pageContent)

    val pageContentSafe: Try[String] = for {
      conn <- HttpService.getConnectionSafe(host, port)
      content <- conn.getSafe(myDesiredUrl)
    } yield content

    println(pageContentSafe.fold(e => s"<html>${e.getMessage}</html>", s => s))
  }
}
