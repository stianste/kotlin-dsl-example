package org.example

data class Citizen(var name: String = "", var age: Int = 0, var address: Address = Address())

data class Address(var street: String = "", var city: String = "")

// For contrast: function that takes a regular lambda
fun configureCitizenRegular(block: (Citizen) -> Unit): Citizen {
  val citizen = Citizen()
  block(citizen)
  return citizen
}

fun Citizen.configureAddressRegular(block: (Address) -> Unit) {
  block(address)
}

fun main() {
  val alice = configureCitizenRegular { citizen ->
    citizen.name = "Alice"
    citizen.age = 25
    citizen.configureAddressRegular { address ->
      address.street = "Side Rd. 456"
      address.city = "Bergen"
    }
  }

  println(alice)
}
