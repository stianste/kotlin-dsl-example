package org.example.models.shipment

data class Dimension(val width: Double, val height: Double, val length: Double) {
  fun volume(): Double {
    return width * height * length
  }
}

data class DimensionBuilder(
  var width: Double = -1.0,
  var height: Double = -1.0,
  var length: Double = -1.0,
) {
  fun asDimension(): Dimension {
    return Dimension(width = width, height = height, length = length)
  }
}
