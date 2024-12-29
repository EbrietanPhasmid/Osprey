package com.osprey.app
package san

import scala.annotation.tailrec

object Sanitise {

  def sanitiseCondition(input: String, position: String): String = {
    sanitiseBorder(input, position)
  }

  def sanitiseBorder(input: String, position: String): String = {
    val BORDER_GLYPH = '#'
    if !input.contains(BORDER_GLYPH) then return input

    class WordBorderException extends Exception

    val ANY_CHARS = raw"[\u0000-\uFFFF]*"
    val hash = raw"($ANY_CHARS)($BORDER_GLYPH)($ANY_CHARS)".r
    val output =
      try {
        input match {
          case hash(a, b, c) if a.isEmpty && position == "left" =>
            raw"\b$c"
          case hash(a, b, c) if c.isEmpty && position == "right" =>
            raw"$a${"$"}"
          case _ if input contains BORDER_GLYPH =>
            throw new WordBorderException
        }
      } catch {
        case e: WordBorderException =>
          println(
            "Exception: Word boundary not on edge of condition definition."
          ); ""
      }

    if !output.contains(BORDER_GLYPH) then return output
    class ExtraBorderException extends Exception
    try { throw new ExtraBorderException }
    catch {
      case e: ExtraBorderException =>
        println(s"More than one word border inserted into condition: $input")
    }
    "\uFFFF" * 10
  }
}
