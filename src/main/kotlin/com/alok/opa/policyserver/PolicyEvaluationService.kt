@file:Suppress("unused")

package com.alok.opa.policyserver

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class PolicyEvaluationService(private val opaLocalProperties: OPALocalProperties) {

    companion object {
        val log: Logger = getLogger(PolicyEvaluationService::class.java)
        val restTemplate = RestTemplate()
    }

    fun evaluatePolicy(input: PolicyEvaluationInput): PolicyEvaluationOutput {
        log.info("Evaluating policy for input: $input")
        val opaPaths = ArrayList<String>(opaLocalProperties.path)
        opaPaths.add(input.employee)

        val opaRequestBodyDto = OpaRequestBodyDto(input.currentUser, opaPaths, opaLocalProperties.method)

        val opaRequestDto = OpaRequestDto(opaRequestBodyDto)

        val headers = HttpHeaders()
        headers.contentType = APPLICATION_JSON
        headers[HttpHeaders.CONTENT_TYPE] = "application/json"

        log.info("OPA URL: ${opaLocalProperties.url}")

        val httpEntity = HttpEntity(opaRequestDto, headers)
        val responseEntity: ResponseEntity<OpaResponseDto> = restTemplate.exchange(opaLocalProperties.url, HttpMethod.POST, httpEntity, OpaResponseDto::class.java)

        log.info("OPA Response: ${responseEntity.body?.result?.allow}")

        opaPaths.clear()

        val policyEvaluationOutput = PolicyEvaluationOutput()
        policyEvaluationOutput.allowed =  responseEntity.body?.result?.allow ?: false
        return policyEvaluationOutput
    }
}


data class PolicyEvaluationInput(
        val currentUser: String,
        val employee: String,
)

data class PolicyEvaluationOutput(
        var allowed: Boolean = false
)

data class OpaRequestBodyDto(
    val user: String,
    val path: List<String>,
    val method: String
)

data class OpaResponseBodyDto(
    val allow: Boolean
) {
    constructor() : this(false)
}

data class OpaRequestDto(
    val input: OpaRequestBodyDto
) {
    constructor() : this(OpaRequestBodyDto("", emptyList(), ""))
}

data class OpaResponseDto(
    val result: OpaResponseBodyDto
) {
    constructor() : this(OpaResponseBodyDto(false))
}

