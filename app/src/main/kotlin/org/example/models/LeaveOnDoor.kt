package org.example.models

import org.example.models.shipment.AdditionalService.EXPRESS_DELIVERY
import org.example.models.shipment.AdditionalService.LEAVE_ON_DOOR
import org.example.models.shipment.AdditionalService.PREMIUM_TREATMENT
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment

data object LeaveOnDoor : ActionType() {
  override fun evaluate(shipment: Shipment) =
    shipment.isAllowedWhen {
      events doesNotInclude DELIVERED
      payedForServices doesNotInclude LEAVE_ON_DOOR
      payedForServices includesAll listOf(EXPRESS_DELIVERY, PREMIUM_TREATMENT)

      items doesNotExceedWeightInKg 100.0
      items noneExceedDimensions
        {
          length = 150.0
          width = 100.0
          height = 25.0
        }

      this.mustNotBeABusinessShipment()
    }
}
