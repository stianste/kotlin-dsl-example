package org.example.models

import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment): ActionEvaluation = ensureRulesForShipment {
    shipment shouldNotBe DELIVERED
  }
}
