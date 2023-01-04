package com.tbahlai.cryptowallet.data.utils

object StatusCode {
    const val OK = 200
    const val CREATED = 201
    const val ACCEPTED = 202
    const val NON_AUTHORITATIVE_INFORMATION = 203
    const val NO_CONTENT = 204
    const val RESET_CONTENT = 205
    const val PARTIAL_CONTENT = 206
    const val MULTIPLE_CHOICES = 300
    const val MOVED_PERMANENTLY = 301
    const val FOUND = 302
    const val SEE_OTHER = 303
    const val NOT_MODIFIED = 304
    const val TEMPORARY_REDIRECT = 307
    const val PERMANENT_REDIRECT = 308

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val PAYMENT_REQUIRED = 402
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405
    const val NOT_ACCEPTABLE = 406
    const val PROXY_AUTHENTICATION_REQUIRED = 407
    const val REQUEST_TIMEOUT = 408
    const val CONFLICT = 409
    const val GONE = 410
    const val LENGTH_REQUIRED = 411
    const val PRECONDITION_FAILED = 412
    const val PAYLOAD_TOO_LARGE = 413
    const val URI_TOO_LONG = 414
    const val UNSUPPORTED_MEDIA_TYPE = 415
    const val RANGE_NOT_SATISFIABLE = 416
    const val EXPECTATION_FAILED = 417
    const val IM_A_TEAPOT = 418
    const val UNPROCESSABLE_ENTITY = 422
    const val TOO_EARLY = 425
    const val UPGRADE_REQUIRED = 426
    const val PRECONDITION_REQUIRED = 428
    const val TOO_MANY_REQUESTS = 429
    const val REQUEST_HEADER_FIELDS_TOO_LARGE = 431
    const val UNAVAILABLE_FOR_LEGAL_REASONS = 451

    const val INTERNAL_SERVER_ERROR = 500
    const val NOT_IMPLEMENTED = 501
    const val BAD_GATEWAY = 502
    const val SERVICE_UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504
    const val HTTP_VERSION_NOT_SUPPORTED = 505
    const val VARIANT_ALSO_NEGOTIATES = 506
    const val INSUFFICIENT_STORAGE = 507
    const val LOOP_DETECTED = 508
    const val NOT_EXTENDED = 510
    const val NETWORK_AUTHENTICATION_REQUIRED = 511

    private const val LAST_STATUS_CODE = 599

    val HTTP_SUCCESS_RANGE = OK until MULTIPLE_CHOICES
    val HTTP_FAILURE_RANGE = BAD_REQUEST..LAST_STATUS_CODE
    val HTTP_CLIENT_FAILURE_RANGE = BAD_REQUEST until INTERNAL_SERVER_ERROR
    val HTTP_SERVER_FAILURE_RANGE = INTERNAL_SERVER_ERROR..LAST_STATUS_CODE

    @Suppress("ComplexMethod", "unused")
    fun getEnglishReasonMessage(code: Int): String = when (code) {
        OK -> "OK"
        CREATED -> "Created"
        ACCEPTED -> "Accepted"
        NON_AUTHORITATIVE_INFORMATION -> "Non-Authoritative Information"
        NO_CONTENT -> "No Content"
        RESET_CONTENT -> "Reset Content"
        PARTIAL_CONTENT -> "Partial Content"
        MULTIPLE_CHOICES -> "Multiple Choices"
        MOVED_PERMANENTLY -> "Moved Permanently"
        FOUND -> "Found"
        SEE_OTHER -> "See Other"
        NOT_MODIFIED -> "Not Modified"
        TEMPORARY_REDIRECT -> "Temporary Redirect"
        PERMANENT_REDIRECT -> "Permanent Redirect"
        BAD_REQUEST -> "Bad Request"
        UNAUTHORIZED -> "Unauthorized"
        PAYMENT_REQUIRED -> "Payment Required"
        FORBIDDEN -> "Forbidden"
        NOT_FOUND -> "Not Found"
        METHOD_NOT_ALLOWED -> "Method Not Allowed"
        NOT_ACCEPTABLE -> "Not Acceptable"
        PROXY_AUTHENTICATION_REQUIRED -> "Proxy Authentication Required"
        REQUEST_TIMEOUT -> "Request Timeout"
        CONFLICT -> "Conflict"
        GONE -> "Gone"
        LENGTH_REQUIRED -> "Length Required"
        PRECONDITION_FAILED -> "Precondition Failed"
        PAYLOAD_TOO_LARGE -> "Payload Too Large"
        URI_TOO_LONG -> "URI Too Long"
        UNSUPPORTED_MEDIA_TYPE -> "Unsupported Media Type"
        RANGE_NOT_SATISFIABLE -> "Range Not Satisfiable"
        EXPECTATION_FAILED -> "Expectation Failed"
        IM_A_TEAPOT -> "I'm a teapot"
        UNPROCESSABLE_ENTITY -> "Unprocessable Entity"
        TOO_EARLY -> "Too Early"
        UPGRADE_REQUIRED -> "Upgrade Required"
        PRECONDITION_REQUIRED -> "Precondition Required"
        TOO_MANY_REQUESTS -> "Too Many Requests"
        REQUEST_HEADER_FIELDS_TOO_LARGE -> "Request Header Fields Too Large"
        UNAVAILABLE_FOR_LEGAL_REASONS -> "Unavailable For Legal Reasons"
        INTERNAL_SERVER_ERROR -> "Internal Server Error"
        NOT_IMPLEMENTED -> "Not Implemented"
        BAD_GATEWAY -> "Bad Gateway"
        SERVICE_UNAVAILABLE -> "Service Unavailable"
        GATEWAY_TIMEOUT -> "Gateway Timeout"
        HTTP_VERSION_NOT_SUPPORTED -> "HTTP Version Not Supported"
        VARIANT_ALSO_NEGOTIATES -> "Variant Also Negotiates"
        INSUFFICIENT_STORAGE -> "Insufficient Storage"
        LOOP_DETECTED -> "Loop Detected"
        NOT_EXTENDED -> "Not Extended"
        NETWORK_AUTHENTICATION_REQUIRED -> "Network Authentication Required"
        in OK..LAST_STATUS_CODE -> "Non standard status code"
        else -> throw IllegalStateException("Unknown status code $code.")
    }
}
