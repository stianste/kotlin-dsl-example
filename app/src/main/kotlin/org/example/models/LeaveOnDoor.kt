package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    isAllowedWhen(shipment) {
      it isNot DELIVERED
      it.payedForServices doesNotInclude AdditionalService.LEAVE_ON_DOOR
      it hasAllServices
        listOf(AdditionalService.EXPRESS_DELIVERY, AdditionalService.PREMIUM_TREATMENT)

      it doesNotExceedWeightInKg 100.0
      it doesNotExceedDimensions
        {
          length = 150.0
          width = 100.0
          height = 25.0
        }

      it.isNotABusinessShipment()
    }
}
