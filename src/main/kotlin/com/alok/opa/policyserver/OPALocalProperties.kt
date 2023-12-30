package com.alok.opa.policyserver

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "opa", ignoreUnknownFields = true, ignoreInvalidFields = false)
data class OPALocalProperties(
        var url: String = "",
        var path: List<String> = emptyList(),
        var method: String = ""
)