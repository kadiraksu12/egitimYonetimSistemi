package com.yesevi.egitimkadir.service;

import com.yesevi.egitimkadir.domain.TumEgitimler;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TumEgitimler}.
 */
public interface TumEgitimlerService {
    /**
     * Save a tumEgitimler.
     *
     * @param tumEgitimler the entity to save.
     * @return the persisted entity.
     */
    TumEgitimler save(TumEgitimler tumEgitimler);

    /**
     * Partially updates a tumEgitimler.
     *
     * @param tumEgitimler the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TumEgitimler> partialUpdate(TumEgitimler tumEgitimler);

    /**
     * Get all the tumEgitimlers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TumEgitimler> findAll(Pageable pageable);

    /**
     * Get the "id" tumEgitimler.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TumEgitimler> findOne(Long id);

    /**
     * Delete the "id" tumEgitimler.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
