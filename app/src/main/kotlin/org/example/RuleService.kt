package org.example

import org.example.models.ActionEvaluation
import org.example.models.ActionEvaluation.DisallowedAction
import org.example.models.EvaluationResult
import org.example.models.LeaveOnDoor
import org.example.models.shipment.Shipment

class RuleService {

  companion object {
    private val enabledActions = listOf(LeaveOnDoor)

    fun evaluate(shipment: Shipment): EvaluationResult {
      val (allowedActions, disallowedActions) =
        enabledActions.map { it.evaluate(shipment) }.partition(ActionEvaluation::isAllowed)

      return EvaluationResult(
        allowedActions = allowedActions.map(ActionEvaluation::actionType),
        disallowedActions =
          disallowedActions
            .filterIsInstance<DisallowedAction>()
            .groupBy(ActionEvaluation::actionType)
            .mapValues { it.value.map(DisallowedAction::reason) },
      )
    }
  }
}
