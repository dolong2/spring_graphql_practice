package com.practice.graphql.global.config.exception

import com.practice.graphql.global.config.exception.collections.BasicException
import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GlobalExceptionHandler: DataFetcherExceptionResolverAdapter() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java.simpleName)
    }

    override fun resolveToSingleError(e: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (e) {
            is BasicException -> toGraphQLError(e)
            else -> super.resolveToSingleError(e, env)
        }
    }

    private fun toGraphQLError(e: BasicException): GraphQLError? {
        log.warn("Exception while handling request: ${e.message}", e)
        return GraphqlErrorBuilder.newError().message(e.message).errorType(ErrorType.DataFetchingException).build()
    }
}