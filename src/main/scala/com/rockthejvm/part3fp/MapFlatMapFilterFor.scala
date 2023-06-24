package com.rockthejvm.part3fp

object MapFlatMapFilterFor {

  // standard linked list
  val aList: List[Int] = List(1, 2, 3) // [1] -> [2] -> [3] -> Nil
  val first: Int = aList.head
  val rest: List[Int] = aList.tail

  // map
  val anIncrementedList: List[Int] = aList.map(_ + 1) // [2, 3, 4]

  // filter
  val anOddList: List[Int] = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair: Int => List[Int] = x => List(x, x + 1)
  val aFlatMappedList: List[Int] = aList.flatMap(toPair) // [1, 2, 2, 3, 3, 4]

  val numbers: List[Int] = List(1, 2, 3, 4)
  val chars: List[Char] = List('a', 'b', 'c', 'd')
  val colors: List[String] = List("black", "white", "red")

  val combinations: List[String] =
    numbers.flatMap {num =>
      chars.flatMap {char =>
        colors.map(color => s"$num$char - $color")
      }
    }

  // for comprehension - identical to flatMap + map chains
  val combinations_v2: List[String] = for {
    num <- numbers if num % 2 == 0  // if guard identical to "withFilter" (basically filter)
    char <- chars                   // generators are like flatMap
    color <- colors                 // last generator is like map
  } yield s"$num$char - $color"     // an expression

  // for comprehension with unit - "side effects"
  for {
    num <- numbers if num % 2 != 0
    color <- colors
  } println(s"$num - $color")

  import com.rockthejvm.practice.*
  val someNums: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  val incrementedNums: LList[Int] = someNums.map(_ + 1)
  val filteredNums: LList[Int] = someNums.filter(_ % 2 == 0)
  val flatMappedNums: LList[Int] = someNums.flatMap(x => Cons(x, Cons(x + 1, Empty())))

  val someComboNums: LList[String] = for {
    num <- someNums if num % 2 == 0
  } yield s"$num"


  def main(args: Array[String]): Unit = {

  }

}
