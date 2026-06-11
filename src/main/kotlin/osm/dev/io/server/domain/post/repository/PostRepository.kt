package osm.dev.io.server.domain.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import osm.dev.io.server.domain.post.Post

interface PostRepository : JpaRepository<Post, Long>