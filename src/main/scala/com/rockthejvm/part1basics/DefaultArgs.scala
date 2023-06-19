package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntil(x: Int, acc: Int = 0): Int = {
    if x <= 0 then acc
    else sumUntil(x - 1, acc + x)
  }

  val sumUntil100: Int = sumUntil(100)

  def savePicture(
    path: String,
    name: String,
    format: String = "jpg",
    width: Int = 1920,
    height: Int = 1080
  ): Unit = {
    println(s"Saving picture in format $format in path $path...")
  }

  def main(args: Array[String]): Unit = {
    // default args are injected
    savePicture("Desktop/photos", "myphoto")
    // passing values to override default args
    savePicture("Desktop/photos", "myphoto", "png")
    // passing values to override some default args while omitting the first
    savePicture("Desktop/photos", "myphoto", width = 800, height = 600)
  }
}
