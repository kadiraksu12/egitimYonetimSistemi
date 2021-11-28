package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.OgrenciEgitimler;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OgrenciEgitimler entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OgrenciEgitimlerRepository extends JpaRepository<OgrenciEgitimler, Long> {}
