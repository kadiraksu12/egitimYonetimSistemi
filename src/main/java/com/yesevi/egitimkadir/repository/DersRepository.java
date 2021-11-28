package com.yesevi.egitimkadir.repository;

import com.yesevi.egitimkadir.domain.Ders;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Ders entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DersRepository extends JpaRepository<Ders, Long> {}
