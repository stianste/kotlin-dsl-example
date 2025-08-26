package org.example

@DslMarker annotation class CitizenDsl

@CitizenDsl
data class Citizen(var name: String = "", var age: Int = 0, var address: Address = Address())

@CitizenDsl data class Address(var street: String = "", var city: String = "")

fun configureCitizen(block: Citizen.() -> Unit): Citizen {
  val person = Citizen()
  person.block() // 'this' inside block refers to person
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

  // Using regular lambdas (for contrast)
  val alice = configurePersonRegular { person ->
    person.name = "Alice"
    person.age = 25
    person.configureAddressRegular { a ->
      a.street = "Side Rd. 456"
      a.city = "Bergen"
    }
  }

  println(alice)
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
