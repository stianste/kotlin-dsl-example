package org.example.models

import org.example.models.shipment.AdditionalService
import org.example.models.shipment.Dimension
import org.example.models.shipment.DimensionBuilder

sealed class RuleFailureReason {
  data object ShipmentIsAlreadyDelivered : RuleFailureReason()

  data class IllegalAdditionalServicePresent(val additionalService: AdditionalService) :
    RuleFailureReason()

  data class MissingRequiredAdditionalService(
    val requiredAdditionalServices: List<AdditionalService>
  ) : RuleFailureReason()

  data class ShipmentTooHeavy(val maxWeight: Double, val actualWeight: Double) :
    RuleFailureReason()

  data class ShipmentTooLarge(
    val maxDimensions: DimensionBuilder,
    val actualDimensions: Dimension,
  ) : RuleFailureReason()

  data object IsBusinessShipment : RuleFailureReason()
}
