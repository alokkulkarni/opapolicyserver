package com.alok.opa.policyserver

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(OPALocalProperties::class)
class PolicyserverApplication(private val policyEvaluationService: PolicyEvaluationService)
{

	@Bean
	fun evaluate(): CommandLineRunner {
		return CommandLineRunner {
			val input = PolicyEvaluationInput("bob", "alok")
			val output = policyEvaluationService.evaluatePolicy(input)
			println("Allowed: ${output.allowed}")
		}
	}

}

fun main(args: Array<String>) {
	runApplication<PolicyserverApplication>(*args)
}
