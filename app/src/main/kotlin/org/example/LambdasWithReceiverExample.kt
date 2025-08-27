package org.example

@DslMarker
annotation class CitizenDslMarker

@CitizenDslMarker
data class Citizen(var name: String = "", var age: Int = 0, var address: Address = Address())

@CitizenDslMarker
data class Address(var street: String = "", var city: String = "")

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
      name = "Alice" // 'name' is accessible here, but refers to Citizen's name
      street = "Main St. 123"
      city = "Oslo"
    }
  }
  println(bob)

  // Using regular lambdas (for contrast)
  val alice = configureCitizenRegular { citizen ->
    citizen.name = "Alice"
    citizen.age = 25
    citizen.configureAddressRegular { a ->
      a.street = "Side Rd. 456"
      a.city = "Bergen"
    }
  }

  println(alice)
}

// For contrast: function that takes a regular lambda
fun configureCitizenRegular(block: (Citizen) -> Unit): Citizen {
  val person = Citizen()
  block(person) // must reference person explicitly inside lambda
  return person
}

fun Citizen.configureAddressRegular(block: (Address) -> Unit) {
  block(address)
}
