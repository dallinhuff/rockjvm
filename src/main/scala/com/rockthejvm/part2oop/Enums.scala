package com.rockthejvm.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields or methods
    def openDoc(): Unit =
      if (this == READ) println("opening doc...")
      else println("reading not allowed!")
  }

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  val somePermissions: Permissions = Permissions.READ

  def main(args: Array[String]): Unit = {

  }
}
