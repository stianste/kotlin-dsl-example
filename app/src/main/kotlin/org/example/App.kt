package org.example

fun main() {
  // Extension functions
  "john doe".capitalizeWordsExtensionFn()

  // Infix functions
  "Charlie" befriends "Dana"

  // Higher-order functions
  repeatThreeTimes { surroundWithStars { surroundWithDashes { println("Less clutter!") } } }

  // Lambdas with receiver
  val alice = configureCitizen {
    name = "Alice"
    age = 30
    address {
      street = "Main St. 123"
      city = "Oslo"
    }
  }
  println(alice)
}
