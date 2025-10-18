package com.practice.graphql.global.exception

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GlobalExceptionHandler : DataFetcherExceptionResolverAdapter() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java.simpleName)
    }

    override fun resolveToSingleError(e: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (e) {
            is BasicException ->  {
                log.warn(e.errorCode.code)
                log.warn(e.errorCode.msg)
                toGraphQLError(e.errorCode)
            }
            else -> {
                log.error(e.message)
                super.resolveToSingleError(e, env)
            }
        }
    }

    private fun toGraphQLError(errorCode: ErrorCode): GraphQLError? {
        return GraphqlErrorBuilder.newError().message(errorCode.msg).errorType(errorCode.errorType).build()
    }
}