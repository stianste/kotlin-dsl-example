package org.example.models

import org.example.models.ActionEvaluation.AllowedAction
import org.example.models.shipment.AdditionalService
import org.example.models.shipment.EventType
import org.example.models.shipment.Shipment

sealed class ActionType() {
  // TODO: Can we get rid of this one all together?
  abstract fun evaluate(shipment: Shipment): ActionEvaluation

  fun ActionType.ensure(shipment: Shipment, block: (Shipment) -> Unit): ActionEvaluation {
    try {
      block(shipment)
    } catch (e: RuleViolation) {
      return e.actionType.disallowBecause(e.reason)
    }
    return allow()
  }

  infix fun Shipment.shouldNotBe(disallowedEventType: EventType) {
    if (events.any { it.type == disallowedEventType }) {
      failWithReason(RuleFailureReason.ShipmentIsAlreadyDelivered)
    }
  }

  infix fun Shipment.shouldNotHaveAdditionalService(illegalService: AdditionalService) =
    if (payedForServices.contains(illegalService))
      failWithReason(RuleFailureReason.IllegalAdditionalServicePresent(illegalService))
    else Unit

  internal fun failWithReason(reason: RuleFailureReason): Nothing {
    throw RuleViolation(this, reason)
  }
}

fun ActionType.allow() = AllowedAction(this)

fun ActionType.disallowBecause(reason: RuleFailureReason) =
  ActionEvaluation.DisallowedAction(this, reason)

class RuleViolation(val actionType: ActionType, val reason: RuleFailureReason) : Throwable()
