package com.rockthejvm.part2oop

object Packages {

  // packages = "folders"

  // fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ???

  // import
  import com.rockthejvm.practice.LList
  val anotherList: LList[Int] = ???

  // import under alias
  import java.util.List as JList
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: Predicate[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}

  // import everything but a symbol
  object PlayingPhysics {
    import PhysicsConstants.{PLANCK as _, *}
  }

  // default imports
  // scala.*, scala.Predef.*, java.lang.*

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength: Double): Double =
      PLANCK / wavelength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator
    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }


  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstants {
  // constants
  val SPEED_OF_LIGHT = 29979792458L
  val PLANCK = 6.62e-34
  val EARTH_GRAVITY = 9.8

}
