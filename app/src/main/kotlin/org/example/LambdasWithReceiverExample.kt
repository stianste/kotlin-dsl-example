package org.example

fun configureCitizen(block: Citizen.() -> Unit): Citizen {
  val person = Citizen()
  person.block()
  return person
}

fun Citizen.address(block: Address.() -> Unit) {
  address.block()
}

fun main() {

  // Using lambdas with receiver (idiomatic Kotlin)
  val bob = configureCitizen {
    name = "Bob"
    age = 30
    address {
      street = "Main St. 123"
      city = "Oslo"
    }
  }

  println(bob)
}
