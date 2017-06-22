package uk.deloitte.interview

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest.Matchers._
import org.scalatest.WordSpec

/**
  * Created by Abimbola on 22/06/2017.
  */
class BootTest extends WordSpec {

  "A Deloitte Flow" should {

    val lineSeparator = sys.props("line.separator")

    "Print instructions if no arguments are supplied" in {

      val output = new ByteArrayOutputStream()

      Console.withOut(output) {
        Boot.main(Array.empty[String])
      }

      val expected =
        """### Deloitte Flow Application
          |
          |To view the application instructions, simply run the application from the command line using `sbt`
          |
          |    > sbt "run"
          |
          |To start the application flow, pass the application an age as an integer when you run the application
          |
          |    > sbt "run 12"""".stripMargin + lineSeparator

      output.toString should be (expected)
    }

    "Print an error message if the age is not entered as a number" in {

      val output = new ByteArrayOutputStream()

      Console.withOut(output) {
        Boot.main(Array("Twelve"))
      }

      val expected = "Error: An age must be entered as a number!" + lineSeparator

      output.toString should be (expected)
    }

    "Print questions 1, 2 and 3 if the person is less than 18 years old" in {

      val answers = List("Abim", "12", "Boris")

      val input = new ByteArrayInputStream(
        answers.mkString(lineSeparator).getBytes
      )
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(output) {
          Boot.main(Array("12"))
        }
      }

      val expected = List("What is your name?", "How old are you?", "Enter your pet name please")
        .mkString("", lineSeparator, lineSeparator)

      output.toString should be (expected)
    }

    "Print questions 1, 2 and 4 if the person is more than 18 years old" in {

      val answers = List("Abim", "24", "Boris")

      val input = new ByteArrayInputStream(
        answers.mkString(lineSeparator).getBytes
      )
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(output) {
          Boot.main(Array("24"))
        }
      }

      val expected = List("What is your name?", "How old are you?", "Do you have a driving license?")
        .mkString("", lineSeparator, lineSeparator)

      output.toString should be (expected)
    }
  }
}
