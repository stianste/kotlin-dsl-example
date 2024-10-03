package org.example.models

data class EvaluationResult(
  val allowedActions: List<ActionType>,
  val disallowedActions: Map<ActionType, ReasonList>,
)

typealias ReasonList = List<RuleFailureReason>
