package com.yesevi.egitimkadir.web.rest;

import com.yesevi.egitimkadir.domain.TumEgitimler;
import com.yesevi.egitimkadir.repository.TumEgitimlerRepository;
import com.yesevi.egitimkadir.service.TumEgitimlerService;
import com.yesevi.egitimkadir.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.yesevi.egitimkadir.domain.TumEgitimler}.
 */
@RestController
@RequestMapping("/api")
public class TumEgitimlerResource {

    private final Logger log = LoggerFactory.getLogger(TumEgitimlerResource.class);

    private static final String ENTITY_NAME = "tumEgitimler";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TumEgitimlerService tumEgitimlerService;

    private final TumEgitimlerRepository tumEgitimlerRepository;

    public TumEgitimlerResource(TumEgitimlerService tumEgitimlerService, TumEgitimlerRepository tumEgitimlerRepository) {
        this.tumEgitimlerService = tumEgitimlerService;
        this.tumEgitimlerRepository = tumEgitimlerRepository;
    }

    /**
     * {@code POST  /tum-egitimlers} : Create a new tumEgitimler.
     *
     * @param tumEgitimler the tumEgitimler to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tumEgitimler, or with status {@code 400 (Bad Request)} if the tumEgitimler has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tum-egitimlers")
    public ResponseEntity<TumEgitimler> createTumEgitimler(@RequestBody TumEgitimler tumEgitimler) throws URISyntaxException {
        log.debug("REST request to save TumEgitimler : {}", tumEgitimler);
        if (tumEgitimler.getId() != null) {
            throw new BadRequestAlertException("A new tumEgitimler cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TumEgitimler result = tumEgitimlerService.save(tumEgitimler);
        return ResponseEntity
            .created(new URI("/api/tum-egitimlers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tum-egitimlers/:id} : Updates an existing tumEgitimler.
     *
     * @param id the id of the tumEgitimler to save.
     * @param tumEgitimler the tumEgitimler to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tumEgitimler,
     * or with status {@code 400 (Bad Request)} if the tumEgitimler is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tumEgitimler couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tum-egitimlers/{id}")
    public ResponseEntity<TumEgitimler> updateTumEgitimler(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TumEgitimler tumEgitimler
    ) throws URISyntaxException {
        log.debug("REST request to update TumEgitimler : {}, {}", id, tumEgitimler);
        if (tumEgitimler.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tumEgitimler.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tumEgitimlerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TumEgitimler result = tumEgitimlerService.save(tumEgitimler);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tumEgitimler.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tum-egitimlers/:id} : Partial updates given fields of an existing tumEgitimler, field will ignore if it is null
     *
     * @param id the id of the tumEgitimler to save.
     * @param tumEgitimler the tumEgitimler to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tumEgitimler,
     * or with status {@code 400 (Bad Request)} if the tumEgitimler is not valid,
     * or with status {@code 404 (Not Found)} if the tumEgitimler is not found,
     * or with status {@code 500 (Internal Server Error)} if the tumEgitimler couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tum-egitimlers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TumEgitimler> partialUpdateTumEgitimler(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TumEgitimler tumEgitimler
    ) throws URISyntaxException {
        log.debug("REST request to partial update TumEgitimler partially : {}, {}", id, tumEgitimler);
        if (tumEgitimler.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tumEgitimler.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tumEgitimlerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TumEgitimler> result = tumEgitimlerService.partialUpdate(tumEgitimler);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tumEgitimler.getId().toString())
        );
    }

    /**
     * {@code GET  /tum-egitimlers} : get all the tumEgitimlers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tumEgitimlers in body.
     */
    @GetMapping("/tum-egitimlers")
    public ResponseEntity<List<TumEgitimler>> getAllTumEgitimlers(Pageable pageable) {
        log.debug("REST request to get a page of TumEgitimlers");
        Page<TumEgitimler> page = tumEgitimlerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tum-egitimlers/:id} : get the "id" tumEgitimler.
     *
     * @param id the id of the tumEgitimler to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tumEgitimler, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tum-egitimlers/{id}")
    public ResponseEntity<TumEgitimler> getTumEgitimler(@PathVariable Long id) {
        log.debug("REST request to get TumEgitimler : {}", id);
        Optional<TumEgitimler> tumEgitimler = tumEgitimlerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tumEgitimler);
    }

    /**
     * {@code DELETE  /tum-egitimlers/:id} : delete the "id" tumEgitimler.
     *
     * @param id the id of the tumEgitimler to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tum-egitimlers/{id}")
    public ResponseEntity<Void> deleteTumEgitimler(@PathVariable Long id) {
        log.debug("REST request to delete TumEgitimler : {}", id);
        tumEgitimlerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
