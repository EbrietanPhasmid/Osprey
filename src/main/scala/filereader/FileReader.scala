package com.osprey.app
package filereader

import scala.io.Source
import scala.util.{Try, Success, Failure}
import scala.unchecked
import soundchange.SoundChange

object FileReader {
  def createPhonemeGroupMap(): Map[Char, String] = {
    val filename = "phonemegroups.txt"
    val lines = getLinesFromFile(filename)
    return convertToPhonemeGroupMap(lines)
  }
  def createSoundChangeList(): List[SoundChange] = {
    val filename = "soundchanges.txt"
    val stringList = getLinesFromFile(filename)
    val listWithFailures = stringList.map(convertToSoundChange)
    val soundChangeList: List[SoundChange] = listWithFailures.collect {
      case Some(content) => content
    }
    soundChangeList
  }
  def createWordList(): List[String] = {
    val filename = "wordlist.txt"
    getLinesFromFile(filename)
  }
  def getLinesFromFile(filename: String): List[String] = {
    val RESOURCE_DIRECTORY: String =
      "/Users/marcinzawadiak/Desk/Osprey/resources/"
    val fileDirectory: String = s"$RESOURCE_DIRECTORY$filename"
    Try {
      val file = Source.fromFile(fileDirectory)
      try file.getLines.toList
      finally file.close
    } match {
      case Success(lines) => lines
      case Failure(exception) =>
        println(s"Error when reading file $filename: ${exception.getMessage}")
        List.empty
    }
  }
  def convertToPhonemeGroupMap(inputList: List[String]): Map[Char, String] = {
    return Map('P' -> "[k,t,sr")
  }

  def convertToSoundChange(inputUntrimmed: String): Option[SoundChange] = {
    val ANY_CHARS = raw"[\u0000-\uFFFF]*"
    val CONVERSION_SYMBOL = raw">"
    val CONDITION_SYMBOL = raw"/"
    val TARGET_SYMBOL = raw"_"
    def basicSoundChange(input: String): SoundChange = {
      val basicPattern = raw"($ANY_CHARS)>($ANY_CHARS)".r
      input match {
        case basicPattern(target, output) => return SoundChange(target, output)
      }
    }
    def quaternarySoundChange(input: String): SoundChange = {
      val pattern = raw"($ANY_CHARS)>($ANY_CHARS)/($ANY_CHARS)_($ANY_CHARS)".r
      input match {
        case pattern(target, output, leftCondition, rightCondition) =>
          return SoundChange(target, output, leftCondition, rightCondition)
      }
    }
    val input = inputUntrimmed.filterNot(_.isWhitespace)
    if !input.contains(CONVERSION_SYMBOL) then return None
    if input.count(_ == '>') > 1 || input.count(_ == '/') > 1 then return None
    if !input.contains(CONDITION_SYMBOL) then
      return Some { basicSoundChange(input) }
    return Some(quaternarySoundChange(input))
  }
}
