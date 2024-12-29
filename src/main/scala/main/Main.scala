package com.osprey.app
package main

import soundchange.*, os.*, filereader.*

object Main extends App {
  // Read files
  val wordList = FileReader.createWordList()
  val soundChangeList = FileReader.createSoundChangeList()
  val phonemeGroupList = FileReader.createPhonemeGroupMapList()

  // Create conjugate lambda
  val conjugate = convertSoundChangeListToConjugate(soundChangeList)

  // Compile
  val newWords = wordList.map(conjugate)
  def convertSoundChangeListToConjugate(
      soundChangeList: List[SoundChange]
  ): (String) => String =
    soundChangeList.map(_.apply).reduce((f1, f2) => f1.andThen(f2))

  // Results
  println("")
  println("======== WORDS =========")
  wordList.foreach(println)
  println("==== SOUND CHANGES =====")
  soundChangeList.foreach(println)
  println("====== RESULTS =========")
  newWords.foreach(println)
  println("")
}
