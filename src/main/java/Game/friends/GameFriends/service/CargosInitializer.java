package Game.friends.GameFriends.service;

import Game.friends.GameFriends.entity.CargoEntity;
import Game.friends.GameFriends.repository.CargoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargosInitializer implements CommandLineRunner {

    private final CargoRepository cargoRepository;

    public CargosInitializer(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public void run(String... args) {
        String nomeCargo = "ROLE_USUARIO";
        boolean exists = cargoRepository.findByNome(nomeCargo).isPresent();

        if (!exists) {
            CargoEntity cargo = new CargoEntity();
            cargo.setNome(nomeCargo);
            cargoRepository.save(cargo);
        }
    }
}
