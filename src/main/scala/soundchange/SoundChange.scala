package com.osprey.app
package soundchange

import scala.annotation.tailrec, san.*

class SoundChange(
    val target: String,
    val output: String,
    val leftCondition: String = "",
    val rightCondition: String = ""
) {
  def apply(word: String): String = {
    val PLACEHOLDER_GLYPH = "§"
    val proc = applySoundChange(
      input = word,
      target = target,
      leftCondition = Sanitise.sanitiseCondition(leftCondition, "left"),
      rightCondition = Sanitise.sanitiseCondition(rightCondition, "right"),
      desiredOutput = PLACEHOLDER_GLYPH
    )
    applySoundChange(
      input = proc,
      target = PLACEHOLDER_GLYPH,
      desiredOutput = output
    )
  }

  override def toString
      : String = { // When printing a soundchange, it prints it in standard lingusitic notation.
    target + " > " + output + " / " + leftCondition + "_" + rightCondition
  }

  @tailrec
  private def applySoundChange(
      input: String, // String entered to be converted
      target: String, // String that will be replaced
      leftCondition: String = "",
      rightCondition: String = "",
      desiredOutput: String
  ): String = {
    if target == "" then
      return input // if statement exists to not append desiredOutput to input if target is empty.
    val ANY_CHARS = raw"[\u0000-\uFFFF]*"
    val soundChangeTarget =
      raw"($ANY_CHARS$leftCondition)($target)($rightCondition$ANY_CHARS)".r
    val acc = input match {
      case soundChangeTarget(beginning, target, end) =>
        s"$beginning$desiredOutput$end"
      case _ => input
    }
    if acc != input then // If a change occured, loop again.
      applySoundChange(
        acc,
        target,
        leftCondition,
        rightCondition,
        desiredOutput
      )
    else input // Loop finished
  }
}
