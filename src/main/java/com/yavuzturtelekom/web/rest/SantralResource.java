package com.yavuzturtelekom.web.rest;
import com.yavuzturtelekom.domain.Santral;
import com.yavuzturtelekom.service.SantralService;
import com.yavuzturtelekom.web.rest.errors.BadRequestAlertException;
import com.yavuzturtelekom.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Santral.
 */
@RestController
@RequestMapping("/api")
public class SantralResource {

    private final Logger log = LoggerFactory.getLogger(SantralResource.class);

    private static final String ENTITY_NAME = "santral";

    private final SantralService santralService;

    public SantralResource(SantralService santralService) {
        this.santralService = santralService;
    }

    /**
     * POST  /santrals : Create a new santral.
     *
     * @param santral the santral to create
     * @return the ResponseEntity with status 201 (Created) and with body the new santral, or with status 400 (Bad Request) if the santral has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/santrals")
    public ResponseEntity<Santral> createSantral(@Valid @RequestBody Santral santral) throws URISyntaxException {
        log.debug("REST request to save Santral : {}", santral);
        if (santral.getId() != null) {
            throw new BadRequestAlertException("A new santral cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Santral result = santralService.save(santral);
        return ResponseEntity.created(new URI("/api/santrals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /santrals : Updates an existing santral.
     *
     * @param santral the santral to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated santral,
     * or with status 400 (Bad Request) if the santral is not valid,
     * or with status 500 (Internal Server Error) if the santral couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/santrals")
    public ResponseEntity<Santral> updateSantral(@Valid @RequestBody Santral santral) throws URISyntaxException {
        log.debug("REST request to update Santral : {}", santral);
        if (santral.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Santral result = santralService.save(santral);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, santral.getId().toString()))
            .body(result);
    }

    /**
     * GET  /santrals : get all the santrals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of santrals in body
     */
    @GetMapping("/santrals")
    public List<Santral> getAllSantrals() {
        log.debug("REST request to get all Santrals");
        return santralService.findAll();
    }

    /**
     * GET  /santrals/:id : get the "id" santral.
     *
     * @param id the id of the santral to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the santral, or with status 404 (Not Found)
     */
    @GetMapping("/santrals/{id}")
    public ResponseEntity<Santral> getSantral(@PathVariable Long id) {
        log.debug("REST request to get Santral : {}", id);
        Optional<Santral> santral = santralService.findOne(id);
        return ResponseUtil.wrapOrNotFound(santral);
    }

    /**
     * DELETE  /santrals/:id : delete the "id" santral.
     *
     * @param id the id of the santral to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/santrals/{id}")
    public ResponseEntity<Void> deleteSantral(@PathVariable Long id) {
        log.debug("REST request to delete Santral : {}", id);
        santralService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
