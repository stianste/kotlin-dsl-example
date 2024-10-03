package org.example.models

import org.example.models.shipment.Shipment

sealed class ActionType() {
  abstract fun evaluate(shipment: Shipment): ActionEvaluation
}
