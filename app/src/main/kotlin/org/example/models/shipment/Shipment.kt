package org.example.models.shipment

data class Shipment(
  val id: String,
  val shipmentType: ShipmentType,
  val payedForServices: List<AdditionalService>,
  val events: List<Event>,
  val items: List<Item>,
)
