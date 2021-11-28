package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.Duyuru;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Duyuru entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuyuruRepository extends JpaRepository<Duyuru, Long> {}
