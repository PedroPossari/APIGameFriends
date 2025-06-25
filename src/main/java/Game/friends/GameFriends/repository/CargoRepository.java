package Game.friends.GameFriends.repository;

import Game.friends.GameFriends.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Integer>
{
    Optional<CargoEntity> findByNome(String nome);
}
