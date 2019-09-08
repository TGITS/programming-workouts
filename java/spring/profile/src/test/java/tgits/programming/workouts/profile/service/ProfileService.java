package tgits.programming.workouts.profile.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tgits.programming.workouts.profile.entity.Profile;
import tgits.programming.workouts.profile.repository.ProfileRepository;

@Log4j2
@Service
public class ProfileService {

    private final ApplicationEventPublisher publisher;
    private final ProfileRepository profileRepository;

    public ProfileService(ApplicationEventPublisher publisher, ProfileRepository profileRepository){
        this.publisher = publisher;
        this.profileRepository = profileRepository;
    }

    public Flux<Profile> all() {
        return this.profileRepository.findAll();
    }
}
