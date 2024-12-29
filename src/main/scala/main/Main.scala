package com.osprey.app
package main

import soundchange.*, os.*

object Main extends App {
  println(SoundChange("a", "e", "aP", "")("amasata"))

  def convertSoundChangeSeqToConjugate(
      soundChangeSeq: Seq[SoundChange]
  ): (String) => String =
    soundChangeSeq.map(_.apply).reduce((f1, f2) => f1.andThen(f2))
}
