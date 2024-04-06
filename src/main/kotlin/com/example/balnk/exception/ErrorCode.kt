package com.example.balnk.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String, val code: Int) {
    //region 400 - Bad Request

    //endregion

    //region 401 - Unauthorized
    AUTH_FAIL(HttpStatus.UNAUTHORIZED, "인증에 실패 했습니다.",401001),
    //endregion

    //region 404 - Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND,"데이터를 찾을 수 없습니다.",404001),
    //endregion
}