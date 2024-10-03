package org.example.models

import org.example.models.ActionEvaluation.AllowedAction
import org.example.models.shipment.EventType
import org.example.models.shipment.Shipment

sealed class ActionType() {
  // TODO: Can we get rid of this one all together?
  abstract fun evaluate(shipment: Shipment): ActionEvaluation

  // TODO: extenion function?
  fun ensureRulesForShipment(block: () -> Unit): ActionEvaluation {
    try {
      block()
    } catch (e: RuleViolation) {
      return e.actionType.disallowBecause(e.reason)
    }
    return this.allow()
  }

  infix fun Shipment.shouldNotBe(disallowedEventType: EventType) {
    if (events.any { it.type == disallowedEventType }) {
      failWithReason(RuleFailureReason.ShipmentIsAlreadyDelivered)
    }
  }

  private fun failWithReason(reason: RuleFailureReason): Nothing {
    throw RuleViolation(this, reason)
  }
}

fun ActionType.allow() = AllowedAction(this)

fun ActionType.disallowBecause(reason: RuleFailureReason) =
  ActionEvaluation.DisallowedAction(this, reason)

class RuleViolation(val actionType: ActionType, val reason: RuleFailureReason) : Throwable()
