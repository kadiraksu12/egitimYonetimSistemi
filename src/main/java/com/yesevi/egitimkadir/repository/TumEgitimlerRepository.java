package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.TumEgitimler;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TumEgitimler entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TumEgitimlerRepository extends JpaRepository<TumEgitimler, Long> {}
