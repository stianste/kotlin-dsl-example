package org.example.models

import org.example.models.ActionEvaluation.AllowedAction
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment): ActionEvaluation = AllowedAction(this)
}
