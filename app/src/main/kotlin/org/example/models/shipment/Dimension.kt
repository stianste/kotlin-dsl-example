package org.example.models.shipment

data class Dimension(val length: Double, val width: Double, val height: Double) {
  fun volume(): Double {
    return width * height * length
  }
}

data class DimensionBuilder(
  var length: Double = -1.0,
  var width: Double = -1.0,
  var height: Double = -1.0,
) {
  fun asDimension(): Dimension {
    return Dimension(width = width, height = height, length = length)
  }
}

data class LengthBuilder(var length: Double = -1.0) {}
