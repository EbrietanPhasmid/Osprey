package com.osprey.app
package main

import soundchange.*, os.*, filereader.*

object Main extends App {
  val wordList = FileReader.createWordList()
  val soundChangeList = FileReader.createSoundChangeList()
  wordList.foreach(println)
  soundChangeList.foreach(println)
  val conjugate = convertSoundChangeListToConjugate(soundChangeList)
  val newWords = wordList.map(conjugate)
  newWords.foreach(println)
  def convertSoundChangeListToConjugate(
      soundChangeSeq: List[SoundChange]
  ): (String) => String =
    soundChangeSeq.map(_.apply).reduce((f1, f2) => f1.andThen(f2))
}
