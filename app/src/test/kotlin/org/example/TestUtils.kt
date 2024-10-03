package org.example

import kotlin.test.assertContains
import kotlin.test.assertTrue
import org.example.models.ActionType
import org.example.models.EvaluationResult
import org.example.models.RuleFailureReason
import org.junit.jupiter.api.Assertions.assertFalse

fun EvaluationResult.assertActionWasAllowed(action: ActionType) {
  assertTrue(disallowedActions.isEmpty())
  assertContains(allowedActions, action)
}

fun EvaluationResult.assertActionWasNotAllowedBecause(
  action: ActionType,
  reason: RuleFailureReason,
) {
  assertFalse(allowedActions.contains(action))
  assertTrue(disallowedActions[action]?.contains(reason) == true)
  println("Result: $this")
}
