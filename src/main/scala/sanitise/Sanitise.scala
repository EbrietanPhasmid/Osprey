package com.osprey.app
package san

import scala.util.{Try, Success, Failure}
import scala.annotation.tailrec

object Sanitise {

  def sanitiseCondition(input: String, position: String): String = {
    val sanitisedCondition = sanitiseBorder(input, position)
    val result = convertToRegex(sanitisedCondition)
    result
  }

  def sanitiseBorder(input: String, position: String): String = {
    val BORDER_GLYPH = '#'
    if !input.contains(BORDER_GLYPH) then return input

    class WordBorderException extends Exception

    val ANY_CHARS = raw"[\u0000-\uFFFF]*"
    val regex = raw"($ANY_CHARS)($BORDER_GLYPH)($ANY_CHARS)".r
    val output =
      try {
        input match {
          case regex(a, b, c) if a.isEmpty && position == "left" =>
            raw"\b$c"
          case regex(a, b, c) if c.isEmpty && position == "right" =>
            raw"$a${"$"}"
          case _ if input contains BORDER_GLYPH =>
            throw new WordBorderException
        }
      } catch {
        case exception: WordBorderException =>
          println(
            "Exception: Word boundary not on edge of condition definition."
          ); ""
      }

    if !output.contains(BORDER_GLYPH) then return output
    class ExtraBorderException extends Exception
    try { throw new ExtraBorderException }
    catch {
      case exception: ExtraBorderException =>
        println(s"More than one word border inserted into condition: $input")
    }
    "\uFFFF" * 10
  }

  val phonemeCategoryMap: Map[String, String] = Map("P" -> "[m,t,k]")

  def convertToRegex(input: String): String = {
    val attemptedConversionToRegex: Try[String] = Try {
      phonemeCategoryMap(input)
    }
    attemptedConversionToRegex match {
      case Success(regexComponent) => return regexComponent
      case Failure(e)              => return input
    }
  }
}
