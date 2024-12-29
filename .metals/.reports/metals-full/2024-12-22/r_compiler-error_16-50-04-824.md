file://<WORKSPACE>/src/test/scala/soundchangetest/SoundChangeTest.sc
### java.lang.StringIndexOutOfBoundsException: Index 1 out of bounds for length 1

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 0
uri: file://<WORKSPACE>/src/test/scala/soundchangetest/SoundChangeTest.sc
text:
```scala
@@

```



#### Error stacktrace:

```
java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:55)
	java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:52)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:213)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:210)
	java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:98)
	java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
	java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
	java.base/java.lang.String.checkIndex(String.java:4832)
	java.base/java.lang.StringLatin1.charAt(StringLatin1.java:46)
	java.base/java.lang.String.charAt(String.java:1555)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.isWhitespace$1(CommonMtagsEnrichments.scala:84)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1$$anonfun$1(CommonMtagsEnrichments.scala:88)
	scala.Option.filter(Option.scala:319)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1(CommonMtagsEnrichments.scala:88)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trimWhitespaceInRange(CommonMtagsEnrichments.scala:93)
	scala.meta.internal.mtags.MtagsEnrichments$.sourcePosition(MtagsEnrichments.scala:47)
	scala.meta.internal.pc.PcInlayHintsProvider.<init>(PcInlayHintsProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.inlayHints$$anonfun$1(ScalaPresentationCompiler.scala:128)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: Index 1 out of bounds for length 1