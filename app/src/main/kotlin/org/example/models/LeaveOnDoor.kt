package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    shipment.ensure {
      this shouldNotBe DELIVERED
      this shouldNotHaveAdditionalService AdditionalService.LEAVE_ON_DOOR

      shouldNotBeABusinessShipment()
    }

  private fun Shipment.shouldNotBeABusinessShipment() =
    if (this.shipmentType.code.lowercase().contains("b"))
      this@LeaveOnDoor.failWithReason(RuleFailureReason.IsBusinessShipment)
    else Unit
}
