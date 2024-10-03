package org.example.models

import org.example.models.ActionEvaluation.AllowedAction

sealed class ActionEvaluation {
  abstract val actionType: ActionType

  data class AllowedAction(override val actionType: ActionType) : ActionEvaluation()

  data class DisallowedAction(override val actionType: ActionType, val reason: RuleFailureReason) :
    ActionEvaluation()

  fun isAllowed() = this is AllowedAction
}

fun ActionEvaluation.allow() = AllowedAction(actionType)

fun ActionEvaluation.disallowBecause(reason: RuleFailureReason) =
  ActionEvaluation.DisallowedAction(actionType, reason)
