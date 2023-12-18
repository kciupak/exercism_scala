class DNA(dnaString: String) {
  def nucleotideCounts: Either[String, Map[Char, Int]] = {
  if (isDnaValid) Right(createDnaMap) else Left("Error")
  }

  private def isDnaValid: Boolean = {
    dnaString.filter(char => !Set('A', 'C', 'G', 'T').contains(char)).isEmpty
  }

  private def createDnaMap: Map[Char, Int] ={
    Set('A', 'C', 'G', 'T').map { nucleotide => nucleotide -> nucleotideCount(nucleotide)}.toMap
  }

  private def nucleotideCount(nucleotide: Char): Int = {
    dnaString.count(_ == nucleotide)
  }
}


// Instructions
// Each of us inherits from our biological parents a set of chemical instructions known as DNA that influence how our bodies are constructed. All known life depends on DNA!

// Note: You do not need to understand anything about nucleotides or DNA to complete this exercise.

// DNA is a long chain of other chemicals and the most important are the four nucleotides, adenine, cytosine, guanine and thymine. A single DNA chain can contain billions of these four nucleotides and the order in which they occur is important! We call the order of these nucleotides in a bit of DNA a "DNA sequence".

// We represent a DNA sequence as an ordered collection of these four nucleotides and a common way to do that is with a string of characters such as "ATTACG" for a DNA sequence of 6 nucleotides. 'A' for adenine, 'C' for cytosine, 'G' for guanine, and 'T' for thymine.

// Given a string representing a DNA sequence, count how many of each nucleotide is present. If the string contains characters that aren't A, C, G, or T then it is invalid and you should signal an error.

// For example:

// "GATTACA" -> 'A': 3, 'C': 1, 'G': 1, 'T': 2
// "INVALID" -> error
// A common use of Either is to indicate a computation that may possibly result in an error (if the actual error is of no interest then the simpler Option type might be a better choice). In the absence of an error the result is usually a Right (mnemonic: the "right" value) whereas an error is a Left, for example a Left[String] containing an error message. Note that in Scala 2.12 Either is right-biased by default, so it works as expected for operations like filter, map, flatMap or in a for-comprehension. If you are unfamiliar with Either you may read this tutorial. But be aware that this tutorial is about Scala versions prior to 2.12. For Scala 2.12 you can safely ignore RightProjection and omit .right. Either is a so-called Monad which covers a "computational aspect", in this case error handling. Proper use of Monads can result in very concise yet elegant and readable code. Improper use can easily result in the contrary. Watch this video to learn more.
