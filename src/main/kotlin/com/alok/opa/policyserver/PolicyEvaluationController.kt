package com.alok.opa.policyserver

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PolicyEvaluationController(private val policyEvaluationService: PolicyEvaluationService) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(PolicyEvaluationController::class.java)
    }


    @GetMapping("/policy/evaluate")
    fun evaluatePolicy(@RequestBody policyEvaluationInput: PolicyEvaluationInput): PolicyEvaluationOutput {
        log.info("Evaluating policy")
        return policyEvaluationService.evaluatePolicy(policyEvaluationInput)
    }
}