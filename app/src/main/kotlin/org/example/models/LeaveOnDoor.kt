package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    isAllowedWhen(shipment) {
      events doesNotInclude DELIVERED
      payedForServices doesNotInclude AdditionalService.LEAVE_ON_DOOR
      payedForServices includesAll
        listOf(AdditionalService.EXPRESS_DELIVERY, AdditionalService.PREMIUM_TREATMENT)

      items doesNotExceedWeightInKg 100.0
      items noneExceedDimensions
        {
          length = 150.0
          width = 100.0
          height = 25.0
        }

      this.isNotABusinessShipment()
    }
}
