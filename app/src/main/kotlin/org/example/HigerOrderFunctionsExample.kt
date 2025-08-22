package org.example

fun surroundWithStars(action: () -> Unit) {
  println("***")
  action()
  println("***")
}

fun surroundWithDashes(action: () -> Unit) {
  println("---")
  action()
  println("---")
}

fun repeatThreeTimes(action: () -> Unit) {
  repeat(3) { action() }
}

fun main() {
  repeat(2, { println("Hello from inside parentheses!") })
  repeat(2) { println("Hello from outside parentheses!") }

  println("\nComposed example with parentheses:")
  repeatThreeTimes({
    surroundWithStars({
      surroundWithDashes({
        println("Harder to read")
      })
    })
  })

  repeatThreeTimes {
    surroundWithStars {
      surroundWithDashes {
        println("Less clutter!")
      }
    }
  }
}
