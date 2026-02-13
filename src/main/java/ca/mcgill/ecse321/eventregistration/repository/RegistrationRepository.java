package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<Registration, Registration.RegistrationId> {
    Registration findRegistrationByrId(Registration.RegistrationId key);

    List<Registration> findRegistrationByrIdRegistrantOrderByEvent(int pid);
}
