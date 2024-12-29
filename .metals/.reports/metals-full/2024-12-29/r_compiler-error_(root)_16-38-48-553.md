file://<WORKSPACE>/src/main/scala/filereader/FileReader.scala
### java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new RunTimeException(null: <notype>) # -1,
parent span = <783..842>,
child       = null # -1,
child span  = [804..847..847]

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-VhZL2GhzRQq2n8dVA6BmJQ== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.0/semanticdb-javac-0.10.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/os-lib_3/0.9.1/os-lib_3-0.9.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/geny_3/1.0.0/geny_3-1.0.0.jar [exists ]
Options:
-Xsemanticdb -sourceroot <HOME>/Desk/Osprey -release 17


action parameters:
uri: file://<WORKSPACE>/src/main/scala/filereader/FileReader.scala
text:
```scala
package com.osprey.app
package filereader

import scala.io.Source
import scala.util.{Try, Success, Failure}
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
    val listOnlySuccess: List[Option[SoundChange]] = listWithFailures.filter {
      case Some(_) => true
      case None    => false
    }
    return listOnlySuccess.map(_ match
      case Some(content) => content
      case _ => throw new RunTimeException("\"None\" found in listOnlySuccess. H)
    )
  }
  def createWorldList(): List[String] = {
    val filename = "wordlist.txt"
    getLinesFromFile(filename)
  }
  def getLinesFromFile(filename: String): List[String] = {
    val file = Source.fromFile(filename)
    val lines = file.getLines
    file.close
    lines.toList
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
    val input = inputUntrimmed.trim
    if !input.contains(CONVERSION_SYMBOL) then return None
    if !input.contains(CONDITION_SYMBOL) then
      return Some { basicSoundChange(input) }
    if input.filter(_ == '>').size > 1 || input.filter(_ == '/').size > 1 then
      return None
    return Some(quaternarySoundChange(input))
  }
}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:175)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$1(ParserPhase.scala:39)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$adapted$1(ParserPhase.scala:40)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.parsing.Parser.parse(ParserPhase.scala:40)
	dotty.tools.dotc.parsing.Parser.runOn$$anonfun$1(ParserPhase.scala:49)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.parsing.Parser.runOn(ParserPhase.scala:49)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:246)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1323)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:262)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:270)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:71)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:28)
	scala.meta.internal.pc.SimpleCollector.<init>(PcCollector.scala:373)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:117)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new RunTimeException(null: <notype>) # -1,
parent span = <783..842>,
child       = null # -1,
child span  = [804..847..847]