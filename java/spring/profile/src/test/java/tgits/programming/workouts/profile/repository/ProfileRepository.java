package tgits.programming.workouts.profile.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import tgits.programming.workouts.profile.entity.Profile;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {

    Flux<Profile> findByEmail(String email);
}
