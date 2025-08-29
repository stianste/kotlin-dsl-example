package org.example

class Person(val name: String) {
  infix fun befriends(other: Person): String = "${this.name} is friends with ${other.name}"
}

infix fun String.befriends(other: String): String = "$this is friends with $other"

fun main() {
  val alice = Person("Alice")
  val bob = Person("Bob")

  alice befriends bob

  val name1 = "Charlie"
  val name2 = "Dana"

  name1 befriends name2
}
