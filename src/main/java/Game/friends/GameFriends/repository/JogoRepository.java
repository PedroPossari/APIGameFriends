package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.JogoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<JogoEntity, Integer> {
    Page<JogoEntity> findByTituloContainingIgnoreCase(String search,Pageable pageable);
}