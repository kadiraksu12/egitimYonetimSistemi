package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.EgitimTuru;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EgitimTuru entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EgitimTuruRepository extends JpaRepository<EgitimTuru, Long> {}
