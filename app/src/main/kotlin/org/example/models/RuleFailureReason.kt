package org.example.models

sealed class RuleFailureReason {
  data object ShipmentIsAlreadyDelivered : RuleFailureReason()
}
