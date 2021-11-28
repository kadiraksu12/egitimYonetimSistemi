package com.yesevi.egitimkadir.service.impl;

import com.yesevi.egitimkadir.domain.ApplicationUser;
import com.yesevi.egitimkadir.repository.ApplicationUserRepository;
import com.yesevi.egitimkadir.service.ApplicationUserService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserServiceImpl.class);

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public ApplicationUser save(ApplicationUser applicationUser) {
        log.debug("Request to save ApplicationUser : {}", applicationUser);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public Optional<ApplicationUser> partialUpdate(ApplicationUser applicationUser) {
        log.debug("Request to partially update ApplicationUser : {}", applicationUser);

        return applicationUserRepository
            .findById(applicationUser.getId())
            .map(existingApplicationUser -> {
                if (applicationUser.getBirimi() != null) {
                    existingApplicationUser.setBirimi(applicationUser.getBirimi());
                }

                return existingApplicationUser;
            })
            .map(applicationUserRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationUser> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationUsers");
        return applicationUserRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationUser> findOne(Long id) {
        log.debug("Request to get ApplicationUser : {}", id);
        return applicationUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationUser : {}", id);
        applicationUserRepository.deleteById(id);
    }
}
