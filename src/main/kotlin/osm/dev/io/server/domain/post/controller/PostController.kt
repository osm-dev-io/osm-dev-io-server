package osm.dev.io.server.domain.post.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import osm.dev.io.server.domain.post.Post
import osm.dev.io.server.domain.post.controller.dto.CreatePostRequest
import osm.dev.io.server.domain.post.service.PostService
import osm.dev.io.server.lib.ApiResponse

@RestController
@RequestMapping("posts")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun createPost(
        @RequestBody request: CreatePostRequest
    ): ApiResponse<Nothing> {
        postService.createPost(request.title, request.content)
        return ApiResponse.success()
    }

    @GetMapping
    fun getPosts(): ApiResponse<List<Post>> {
        val posts = postService.getPosts()
        return ApiResponse.success(posts)
    }
}