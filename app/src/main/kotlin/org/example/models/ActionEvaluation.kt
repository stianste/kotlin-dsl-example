package org.example.models

sealed class ActionEvaluation {
  abstract val actionType: ActionType

  data class AllowedAction(override val actionType: ActionType) : ActionEvaluation()

  data class DisallowedAction(override val actionType: ActionType, val reason: RuleFailureReason) :
    ActionEvaluation()

  fun isAllowed() = this is AllowedAction
}
