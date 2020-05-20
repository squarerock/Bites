package squarerock.bites.network.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)

        fun <T> handle(
            resource: Resource<T>,
            onSuccess: () -> Unit,
            onError: () -> Unit = {},
            onLoading: () -> Unit = {}
        ) {
            resource.let {
                when (it.status) {
                    Status.SUCCESS -> onSuccess()
                    Status.ERROR -> onError()
                    Status.LOADING -> onLoading()
                }
            }
        }
    }
}