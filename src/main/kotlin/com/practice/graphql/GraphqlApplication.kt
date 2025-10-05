package com.practice.graphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GraphqlApplication

fun main(args: Array<String>) {
	runApplication<GraphqlApplication>(*args)
}
