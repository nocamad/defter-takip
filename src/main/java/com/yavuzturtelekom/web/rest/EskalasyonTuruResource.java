package com.yavuzturtelekom.web.rest;
import com.yavuzturtelekom.domain.EskalasyonTuru;
import com.yavuzturtelekom.service.EskalasyonTuruService;
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
 * REST controller for managing EskalasyonTuru.
 */
@RestController
@RequestMapping("/api")
public class EskalasyonTuruResource {

    private final Logger log = LoggerFactory.getLogger(EskalasyonTuruResource.class);

    private static final String ENTITY_NAME = "eskalasyonTuru";

    private final EskalasyonTuruService eskalasyonTuruService;

    public EskalasyonTuruResource(EskalasyonTuruService eskalasyonTuruService) {
        this.eskalasyonTuruService = eskalasyonTuruService;
    }

    /**
     * POST  /eskalasyon-turus : Create a new eskalasyonTuru.
     *
     * @param eskalasyonTuru the eskalasyonTuru to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eskalasyonTuru, or with status 400 (Bad Request) if the eskalasyonTuru has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eskalasyon-turus")
    public ResponseEntity<EskalasyonTuru> createEskalasyonTuru(@Valid @RequestBody EskalasyonTuru eskalasyonTuru) throws URISyntaxException {
        log.debug("REST request to save EskalasyonTuru : {}", eskalasyonTuru);
        if (eskalasyonTuru.getId() != null) {
            throw new BadRequestAlertException("A new eskalasyonTuru cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EskalasyonTuru result = eskalasyonTuruService.save(eskalasyonTuru);
        return ResponseEntity.created(new URI("/api/eskalasyon-turus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eskalasyon-turus : Updates an existing eskalasyonTuru.
     *
     * @param eskalasyonTuru the eskalasyonTuru to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eskalasyonTuru,
     * or with status 400 (Bad Request) if the eskalasyonTuru is not valid,
     * or with status 500 (Internal Server Error) if the eskalasyonTuru couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eskalasyon-turus")
    public ResponseEntity<EskalasyonTuru> updateEskalasyonTuru(@Valid @RequestBody EskalasyonTuru eskalasyonTuru) throws URISyntaxException {
        log.debug("REST request to update EskalasyonTuru : {}", eskalasyonTuru);
        if (eskalasyonTuru.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EskalasyonTuru result = eskalasyonTuruService.save(eskalasyonTuru);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eskalasyonTuru.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eskalasyon-turus : get all the eskalasyonTurus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eskalasyonTurus in body
     */
    @GetMapping("/eskalasyon-turus")
    public List<EskalasyonTuru> getAllEskalasyonTurus() {
        log.debug("REST request to get all EskalasyonTurus");
        return eskalasyonTuruService.findAll();
    }

    /**
     * GET  /eskalasyon-turus/:id : get the "id" eskalasyonTuru.
     *
     * @param id the id of the eskalasyonTuru to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eskalasyonTuru, or with status 404 (Not Found)
     */
    @GetMapping("/eskalasyon-turus/{id}")
    public ResponseEntity<EskalasyonTuru> getEskalasyonTuru(@PathVariable Long id) {
        log.debug("REST request to get EskalasyonTuru : {}", id);
        Optional<EskalasyonTuru> eskalasyonTuru = eskalasyonTuruService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eskalasyonTuru);
    }

    /**
     * DELETE  /eskalasyon-turus/:id : delete the "id" eskalasyonTuru.
     *
     * @param id the id of the eskalasyonTuru to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eskalasyon-turus/{id}")
    public ResponseEntity<Void> deleteEskalasyonTuru(@PathVariable Long id) {
        log.debug("REST request to delete EskalasyonTuru : {}", id);
        eskalasyonTuruService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
