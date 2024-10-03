package org.example.models

import org.example.models.shipment.AdditionalService

sealed class RuleFailureReason {
  data object ShipmentIsAlreadyDelivered : RuleFailureReason()

  data class IllegalAdditionalServicePresent(val additionalService: AdditionalService) :
    RuleFailureReason()

  data object IsBusinessShipment : RuleFailureReason()
}
