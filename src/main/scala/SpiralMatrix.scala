object SpiralMatrix {
  def spiralMatrix(size: Int = 0): List[List[Int]] = {
    fillMatrix(size)
  }

  private def fillMatrix(size: Int): List[List[Int]] = {
    val matrix = Array.ofDim[Int](size, size)
    val valueCounts: Int = size * size
    var value: Int = 1
    var left, right, top, bottom = 0

    while (value <= valueCounts) {
      for (i <- left until (size - right)) {
        matrix(top)(i) = value
        value += 1
      }
      top += 1

      for (i <- top until (size - bottom)) {
        matrix(i)(size - right -1) = value
        value += 1
      }
      right += 1

      for (i <- (size - right -1)  until (left -1) by -1) {
        matrix(size - bottom -1)(i) = value
        value += 1
      }
      bottom += 1

      for (i <- (size - bottom -1)  until (top -1) by -1) {
        matrix(i)(left) = value
        value += 1
      }
      left += 1
    }

    matrix.map(_.toList).toList
  }
}


// Instructions
// Given the size, return a square matrix of numbers in spiral order.

// The matrix should be filled with natural numbers, starting from 1 in the top-left corner, increasing in an inward, clockwise spiral order, like these examples:

// Examples
// Spiral matrix of size 3
// 1 2 3
// 8 9 4
// 7 6 5
// Spiral matrix of size 4
//  1  2  3 4
// 12 13 14 5
// 11 16 15 6
// 10  9  8 7
