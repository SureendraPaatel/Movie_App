package com.surendra.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Params, out Type> {
    abstract suspend operator fun invoke(params: Params) : Flow<Result<Type>>
}

abstract class BaseUseCaseNoParams<out Type> {
    abstract suspend operator fun invoke(): Flow<Result<Type>>
}