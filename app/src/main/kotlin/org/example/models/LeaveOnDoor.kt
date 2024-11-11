package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    defineRulesFor(shipment) {
      it shouldNotBe DELIVERED
      it shouldNotHaveAdditionalService AdditionalService.LEAVE_ON_DOOR
      it mustHaveServices
        listOf(AdditionalService.EXPRESS_DELIVERY, AdditionalService.PREMIUM_TREATMENT)

      it mustNotExceedWeightInKg 1000.0
      it mustNotExceedDimensions
        {
          length = 150.0
          width = 100.0
          height = 25.0
        }

      it.shouldNotBeABusinessShipment()
    }
}
