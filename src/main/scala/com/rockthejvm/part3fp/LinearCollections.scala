package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections {

  def testSeq(): Unit = {
    // Seq - trait that describes an ordered & indexed sequence
    val aSequence = Seq(4, 2, 3, 1)

    val thirdElement = aSequence.apply(2)
    val thirdElement_v2 = aSequence(2)
    println(thirdElement_v2)

    val reversed = aSequence.reverse
    val anotherSequence = aSequence ++ Seq(5, 6, 7)
    val sorted = aSequence.sorted
    println(reversed)
    println(anotherSequence)
    println(sorted)

    val anIncrementedSeq = aSequence.map(_ + 1)
    println(anIncrementedSeq)

    val aFlatMappedSeq = aSequence.flatMap(x => Seq(x, x + 1))
    println(aFlatMappedSeq)

    val aFilteredSeq = aSequence.filter(_ % 2 == 0)
    println(aFilteredSeq)

    val doubleSum = aSequence.sum

  }

  def testList(): Unit = {
    val aList = List(1, 2, 3)
    // implements Seq trait
    val secondElement = aList(1) // map, flatMap, etc.

    // list-specific methods
    val firstElement = aList.head
    val rest = aList.tail

    val aBiggerList = 0 +: aList :+ 4
    val prepended = 0 :: aList

    // utility methods
    val fiveScalas = List.fill(5)("Scala")
  }

  def testRange(): Unit = {
    val aRange = 1 to 10
    val aNonInclusiveRange = 1 until 10
    // same seq api
    (1 to 10).foreach(_ => println("Scala"))
  }

  def testArray(): Unit = {
    val anArray = Array(1, 2, 3, 4, 5, 6)
    // most Seq apis available, but Arrays are not Sequences
    val aSeq = anArray.toIndexedSeq

    // arrays are mutable
    anArray.update(2, 30) // no new array is allocated
  }

  // fast sequences for a large amount of data
  def testVector(): Unit = {
    val aVector = Vector(1, 2, 3, 4, 5, 6)
  }

  def benchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1_000_000
    def getWriteTime(coll: Seq[Int]): Double = {
      val aRandom = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = aRandom.nextInt(maxCapacity)
        val element = aRandom.nextInt()
        val currentTime = System.nanoTime()
        val updatedCollection = coll.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRuns
    }



    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector
    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  def testSet(): Unit = {
    val aSet = Set(1, 2, 3, 4, 5, 4) // {1, 2, 3, 4, 5}
    val contains3 = aSet.contains(3)
    val contains3_v2 = aSet(3)
    val aBiggerSet = aSet + 4 // {1, 2, 3, 4, 5}
    val aSmallerSet = aSet - 4 // {1, 2, 3, 5}
    val anotherSet = Set(4, 5, 6, 7, 8)

    // union
    val anEvenBiggerSet = aSet ++ anotherSet
    val anEvenBiggerSet_v2 = aSet.union(anotherSet)
    val anEvenBiggerSet_v3 = aSet | anotherSet

    // diff
    val aDifferentSet = aSet.diff(anotherSet)
    val aDifferentSet_v2 = aSet -- anotherSet

    // intersection
    val anIntersection = aSet.intersect(anotherSet)
    val anIntersection_v2 = aSet & anotherSet
  }

  def main(args: Array[String]): Unit = {
    testSeq()
    testList()
    testRange()
    testArray()
    testVector()
    benchmark()
    testSet()
  }
}
