package osm.dev.io.server.domain.post.service

import org.springframework.stereotype.Service
import osm.dev.io.server.domain.post.Post
import osm.dev.io.server.domain.post.repository.PostRepository

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(title: String, content: Map<String, Any>) {
        val newPost = Post(title, content)
        postRepository.save(newPost)
    }

    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }
}