package org.example

class Person(val name: String) {
  infix fun befriends(other: Person): String = "${this.name} is friends with ${other.name}"
}

infix fun String.befriends(other: String): String = "$this is friends with $other"

fun main() {
  val alice = Person("Alice")
  val bob = Person("Bob")

  alice befriends bob

  val charlie = "Charlie"
  val dana = "Dana"

  charlie befriends dana
}
