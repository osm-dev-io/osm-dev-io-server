package osm.dev.io.server.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import osm.dev.io.server.lib.BaseTimeEntity

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true)
    var email: String
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}