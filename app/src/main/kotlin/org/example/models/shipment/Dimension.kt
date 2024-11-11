package org.example.models.shipment

data class Dimension(
  var width: Double = -1.0,
  var height: Double = -1.0,
  var length: Double = -1.0,
) {
  fun volume(): Double {
    return width * height * length
  }
}
