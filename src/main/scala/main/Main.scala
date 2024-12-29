package com.osprey.app
package main

import soundchange.*, os.*, filereader.*

object Main extends App {
  val wordList = FileReader.createWordList()
  val soundChangeList = FileReader.createSoundChangeList()
  val conjugate = convertSoundChangeListToConjugate(soundChangeList)
  val newWords = wordList.map(conjugate)
  def convertSoundChangeListToConjugate(
      soundChangeList: List[SoundChange]
  ): (String) => String =
    soundChangeList.map(_.apply).reduce((f1, f2) => f1.andThen(f2))
  println("")
  println("======== WORDS =========")
  wordList.foreach(println)
  println("==== SOUND CHANGES =====")
  soundChangeList.foreach(println)
  println("====== RESULTS =========")
  newWords.foreach(println)
  println("")
}
