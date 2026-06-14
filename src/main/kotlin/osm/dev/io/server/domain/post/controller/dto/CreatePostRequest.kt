package osm.dev.io.server.domain.post.controller.dto

data class CreatePostRequest(
    val title: String,
    val content: Map<String, Any>
)
