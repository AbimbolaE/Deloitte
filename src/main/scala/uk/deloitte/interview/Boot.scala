package uk.deloitte.interview

import scala.io.{Source, StdIn}

/**
  * Created by Abimbola on 21/06/2017.
  */
object Boot {

  def main(args: Array[String]): Unit = {

    args.headOption match {
      case Some(age) if age.forall(_.isDigit) && age.toInt >= 0 => startFlow(age.toInt)
      case Some(_) => displayError("An age must be entered as a number!")
      case _ => printInstructions()
    }
  }

  case class Question[T](id: Int, text: String, capture: () => T) {

    def display(): Unit = {
      println(text)
      capture()
    }
  }

  private val question1 = Question[String](1, "What is your name?", StdIn.readLine)
  private val question2 = Question[String](2, "How old are you?", StdIn.readLine)
  private val question3 = Question[String](3, "Enter your pet name please", StdIn.readLine)
  private val question4 = Question[Boolean](4, "Do you have a driving license?", StdIn.readBoolean)

  private def startFlow(age: Int) {

    question1.display()

    question2.display()

    if (age < 18) question3.display() else question4.display()
  }

  private def displayError(message: String) = {
    println(s"Error: $message")
  }

  private def printInstructions() {
    val instructions = Source.fromFile("README.md").mkString
    println(instructions)
  }
}
