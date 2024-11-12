package org.example.models.shipment

data class Shipment(
  var id: String,
  val shipmentType: ShipmentType,
  val payedForServices: List<AdditionalService> = emptyList(),
  val events: List<Event> = emptyList(),
  val items: List<Item> = emptyList(),
)
