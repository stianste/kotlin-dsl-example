package org.example

import org.example.dsl.configurePerson
import org.example.models.shipment.Shipment
import org.example.models.shipment.ShipmentType.DOMESTIC_BUSINESS

fun main() {
  // Extension functions
  "john doe".capitalizeWordsExtenisionFn()

  // Infix functions
  "Charlie" befriends "Dana"

  // Higher-order functions
  repeatThreeTimes {
    surroundWithStars {
      surroundWithDashes {
        println("Less clutter!")
      }
    }
  }

  // Lambdas with receiver
  val alice = configurePerson {
    name = "Alice"
    age = 30
    configureAddress {
      street = "Main St. 123"
      city = "Oslo"
    }
  }
  println(alice)
}
