package org.example.models.shipment

enum class ShipmentType(val code: String) {
  DOMESTIC_C2C("DC2C"),
  DOMESTIC_BUSINESS("DB1434"),
  INTERNATIONAL("I"),
  INTERNATIONAL_BUSINESS("IB"),
}
