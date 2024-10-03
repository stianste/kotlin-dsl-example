/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

import kotlin.test.Test
import org.example.models.EvaluationResult
import org.example.models.LeaveOnDoor
import org.example.models.RuleFailureReason
import org.example.models.RuleFailureReason.IllegalAdditionalServicePresent
import org.example.models.RuleFailureReason.ShipmentIsAlreadyDelivered
import org.example.models.shipment.AdditionalService.EXPRESS_DELIVERY
import org.example.models.shipment.AdditionalService.LEAVE_ON_DOOR
import org.example.models.shipment.Event
import org.example.models.shipment.EventType.DELIVERED
import org.example.models.shipment.Shipment
import org.example.models.shipment.ShipmentType.DOMESTIC_BUSINESS
import org.example.models.shipment.ShipmentType.DOMESTIC_C2C

internal class LeaveOnDoorTest {
  @Test
  fun `it should allow LeaveOnDoor when everything is in order`() {

    val result: EvaluationResult = RuleService.evaluate(shipmentValidForLeaveOnDoor)

    result.assertActionWasAllowed(LeaveOnDoor)
  }

  @Test
  fun `it should not allow LeaveOnDoor if shipment is already delivered`() {
    val shipment =
      shipmentValidForLeaveOnDoor.copy(events = listOf(Event(code = "D1", type = DELIVERED)))
    val result = RuleService.evaluate(shipment)

    result.assertActionWasNotAllowedBecause(LeaveOnDoor, ShipmentIsAlreadyDelivered)
  }

  @Test
  fun `it should not allow LeaveOnDoor if LeaveOnDoor is already ordered`() {
    val shipment = shipmentValidForLeaveOnDoor.copy(payedForServices = listOf(LEAVE_ON_DOOR))
    val result = RuleService.evaluate(shipment)

    result.assertActionWasNotAllowedBecause(
      LeaveOnDoor,
      IllegalAdditionalServicePresent(LEAVE_ON_DOOR),
    )
  }

  @Test
  fun `it should not allow business shipments`() {
    val shipment = shipmentValidForLeaveOnDoor.copy(shipmentType = DOMESTIC_BUSINESS)
    val result = RuleService.evaluate(shipment)

    result.assertActionWasNotAllowedBecause(LeaveOnDoor, RuleFailureReason.IsBusinessShipment)
  }

  companion object {
    val shipmentValidForLeaveOnDoor =
      Shipment(
        id = "123",
        shipmentType = DOMESTIC_C2C,
        payedForServices = listOf(EXPRESS_DELIVERY),
        events = emptyList(),
      )
  }
}
