package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    ensure(shipment) {
      it shouldNotBe DELIVERED
      it shouldNotHaveAdditionalService AdditionalService.LEAVE_ON_DOOR
      it mustHaveServices
        listOf(AdditionalService.EXPRESS_DELIVERY, AdditionalService.PREMIUM_TREATMENT)

      shipment mustNotExceedWeight 1000
      shipment mustNotExceedDimensions
        {
          length = 100
          width = 100
          height = 100
        }

      shouldNotBeABusinessShipment()
    }

  private fun Shipment.shouldNotBeABusinessShipment() =
    if (this.shipmentType.code.lowercase().contains("b"))
      this@LeaveOnDoor.failWithReason(RuleFailureReason.IsBusinessShipment)
    else Unit
}
