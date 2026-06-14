package osm.dev.io.server.lib

data class ApiResponse<out T> private constructor (
    val success: Boolean,
    val data: T? = null,
    val error: ApiError? = null,
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(success = true, data = data)

        fun success(): ApiResponse<Nothing> =
            ApiResponse(success = true)

        fun error(error: ApiError): ApiResponse<Nothing> =
            ApiResponse(success = false, error = error)
    }
}

data class ApiError(
    val code: String,
    val message: String,
    val detail: List<FieldErrorDetail>? = null
)

data class FieldErrorDetail(
    val field: String,
    val reason: String
)