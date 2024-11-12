package org.example.models

import org.example.models.ActionEvaluation.AllowedAction
import org.example.models.shipment.AdditionalService
import org.example.models.shipment.DimensionBuilder
import org.example.models.shipment.EventType
import org.example.models.shipment.Item
import org.example.models.shipment.Shipment

sealed class ActionType() {
  abstract fun evaluate(shipment: Shipment): ActionEvaluation

  fun ActionType.isAllowedWhen(shipment: Shipment, block: (Shipment) -> Unit): ActionEvaluation {
    try {
      block(shipment)
    } catch (e: RuleViolation) {
      return e.actionType.disallowBecause(e.reason)
    }
    return allow()
  }

  infix fun Shipment.isNot(disallowedEventType: EventType) {
    if (events.any { it.type == disallowedEventType }) {
      failWithReason(RuleFailureReason.ShipmentIsAlreadyDelivered)
    }
  }

  infix fun Shipment.hasAllServices(requiredServices: List<AdditionalService>) {
    if (!payedForServices.containsAll(requiredServices)) {
      failWithReason(RuleFailureReason.MissingRequiredAdditionalService(requiredServices))
    }
  }

  infix fun Shipment.doesNotExceedWeightInKg(maxWeightInKg: Double) {
    val actualWeightInKg = items.sumOf(Item::weightInKg)
    if (actualWeightInKg > maxWeightInKg) {
      failWithReason(
        RuleFailureReason.ShipmentTooHeavy(
          maxWeight = maxWeightInKg,
          actualWeight = actualWeightInKg,
        )
      )
    }
  }

  infix fun Shipment.doesNotExceedDimensions(block: DimensionBuilder.() -> Unit) {
    val maxDimensions = DimensionBuilder().apply(block)
    val itemWhichIsPotentiallyTooLarge =
      items.find {
        it.dimension.length > maxDimensions.length ||
          it.dimension.width > maxDimensions.width ||
          it.dimension.height > maxDimensions.height
      }
    if (itemWhichIsPotentiallyTooLarge != null) {
      failWithReason(
        RuleFailureReason.ShipmentTooLarge(
          maxDimensions.asDimension(),
          itemWhichIsPotentiallyTooLarge.dimension,
        )
      )
    }
  }

  infix fun Shipment.doesNotHaveAdditionalService(illegalService: AdditionalService) =
    if (payedForServices.contains(illegalService))
      failWithReason(RuleFailureReason.IllegalAdditionalServicePresent(illegalService))
    else Unit

  fun Shipment.isNotABusinessShipment() =
    if (this.shipmentType.code.lowercase().contains("b"))
      failWithReason(RuleFailureReason.IsBusinessShipment)
    else Unit

  private fun failWithReason(reason: RuleFailureReason): Nothing {
    throw RuleViolation(this, reason)
  }
}

fun ActionType.allow() = AllowedAction(this)

fun ActionType.disallowBecause(reason: RuleFailureReason) =
  ActionEvaluation.DisallowedAction(this, reason)

class RuleViolation(val actionType: ActionType, val reason: RuleFailureReason) : Throwable()
