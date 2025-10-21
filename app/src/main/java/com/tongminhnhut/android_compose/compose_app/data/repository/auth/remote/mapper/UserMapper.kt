package com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.mapper

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.domain.model.auth.User

fun UserResponseDto.toUser(): User {
    return User(
        id = id,
        name = name,
        token = token,
    )
}