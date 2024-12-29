package com.osprey.app
package main

import soundchange.*, os.*

object Main extends App {
  val output1 = SoundChange("o", "u", "#", "")("ono")
  println(output1) // uno
  val output2 = SoundChange("o", "u", "", "#")("ono")
  println(output2) // onu

  def convertSoundChangeSeqToConjugate(
      soundChangeSeq: Seq[SoundChange]
  ): (String) => String =
    soundChangeSeq.map(_.apply).reduce((f1, f2) => f1.andThen(f2))
}
