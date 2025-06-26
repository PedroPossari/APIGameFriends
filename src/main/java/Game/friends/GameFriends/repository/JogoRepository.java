package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.JogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<JogoEntity, Integer> {
}
