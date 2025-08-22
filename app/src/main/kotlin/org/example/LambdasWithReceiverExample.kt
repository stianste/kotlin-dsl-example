package org.example

@DslMarker
annotation class PersonDsl

@PersonDsl
data class Citizen(var name: String = "", var age: Int = 0, var address: Address = Address())

@PersonDsl
data class Address(var street: String = "", var city: String = "")

fun configurePerson(block: Citizen.() -> Unit): Citizen {
  val person = Citizen()
  person.block() // 'this' inside block refers to person
  return person
}

fun Citizen.configureAddress(block: Address.() -> Unit) {
  address.block()
}

// For contrast: function that takes a regular lambda
fun configurePersonRegular(block: (Citizen) -> Unit): Citizen {
  val person = Citizen()
  block(person) // must reference person explicitly inside lambda
  return person
}

fun Citizen.configureAddressRegular(block: (Address) -> Unit) {
  block(address)
}

fun main() {
  // Using lambdas with receiver (idiomatic Kotlin)
  val alice = configurePerson {
    name = "Alice"
    age = 30
    configureAddress {
      street = "Main St. 123"
      city = "Oslo"
    }
  }
  println(alice)

  // Using regular lambdas (for contrast)
  val bob = configurePersonRegular { p ->
    p.name = "Bob"
    p.age = 25
    p.configureAddressRegular { a ->
      a.street = "Side Rd. 456"
      a.city = "Bergen"
    }
  }
  println(bob)
}
