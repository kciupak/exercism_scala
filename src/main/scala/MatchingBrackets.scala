object MatchingBrackets {
  def isPaired(brackets: String): Boolean = {
    val regexBrackets = """[\(\)\[\]{}]""".r
    var matches = regexBrackets.findAllIn(brackets).toList.mkString
    while (stillIncludePairedBrackets(matches)) {
      matches = removePairedBrackets(matches)
    }
    matches.isEmpty
  }
  def removePairedBrackets(inputString: String): String = {
    var stringWithRemovedBrackets: String = inputString.replaceAll("\\(\\)", "")
    stringWithRemovedBrackets = stringWithRemovedBrackets.replaceAll("\\[\\]", "")
    stringWithRemovedBrackets.replaceAll("\\{\\}", "")
  }
  def stillIncludePairedBrackets(inputString: String): Boolean = {
    var includesPaired: Boolean = "\\(\\)".r.findFirstIn(inputString).isDefined
    if (!includesPaired) includesPaired = "\\[\\]".r.findFirstIn(inputString).isDefined
    if (!includesPaired) includesPaired = "\\{\\}".r.findFirstIn(inputString).isDefined
    includesPaired
  }
}



// Instructions
// Given a string containing brackets [], braces {}, parentheses (), or any combination thereof, verify that any and all pairs are matched and nested correctly.
