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
  surroundWithStars({ println("Hello from inside parentheses!") })

  surroundWithStars { println("Hello from outside parentheses!") }

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
