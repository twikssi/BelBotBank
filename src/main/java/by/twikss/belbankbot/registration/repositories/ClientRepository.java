package by.twikss.belbankbot.registration.repositories;

import by.twikss.belbankbot.registration.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
