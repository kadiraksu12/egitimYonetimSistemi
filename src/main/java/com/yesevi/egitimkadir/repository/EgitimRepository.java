package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.Egitim;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Egitim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EgitimRepository extends JpaRepository<Egitim, Long> {}
