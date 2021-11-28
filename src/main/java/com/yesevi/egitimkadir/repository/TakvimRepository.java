package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.Takvim;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Takvim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TakvimRepository extends JpaRepository<Takvim, Long> {}
