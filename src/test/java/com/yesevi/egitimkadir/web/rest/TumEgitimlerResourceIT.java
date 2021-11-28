package com.yesevi.egitimkadir.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yesevi.egitimkadir.IntegrationTest;
import com.yesevi.egitimkadir.domain.TumEgitimler;
import com.yesevi.egitimkadir.repository.TumEgitimlerRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TumEgitimlerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TumEgitimlerResourceIT {

    private static final String DEFAULT_EGITIM_BASLIK = "AAAAAAAAAA";
    private static final String UPDATED_EGITIM_BASLIK = "BBBBBBBBBB";

    private static final String DEFAULT_EGITIM_ALT_BASLIK = "AAAAAAAAAA";
    private static final String UPDATED_EGITIM_ALT_BASLIK = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EGITIM_BASLAMA_TARIHI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EGITIM_BASLAMA_TARIHI = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EGITIM_BITIS_TARIHI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EGITIM_BITIS_TARIHI = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DERS_SAYISI = 1;
    private static final Integer UPDATED_DERS_SAYISI = 2;

    private static final Float DEFAULT_EGITIM_SURESI = 1F;
    private static final Float UPDATED_EGITIM_SURESI = 2F;

    private static final String DEFAULT_EGITIM_YERI = "AAAAAAAAAA";
    private static final String UPDATED_EGITIM_YERI = "BBBBBBBBBB";

    private static final Float DEFAULT_EGITIM_PUANI = 1F;
    private static final Float UPDATED_EGITIM_PUANI = 2F;

    private static final Boolean DEFAULT_KAYIT = false;
    private static final Boolean UPDATED_KAYIT = true;

    private static final String ENTITY_API_URL = "/api/tum-egitimlers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TumEgitimlerRepository tumEgitimlerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTumEgitimlerMockMvc;

    private TumEgitimler tumEgitimler;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TumEgitimler createEntity(EntityManager em) {
        TumEgitimler tumEgitimler = new TumEgitimler()
            .egitimBaslik(DEFAULT_EGITIM_BASLIK)
            .egitimAltBaslik(DEFAULT_EGITIM_ALT_BASLIK)
            .egitimBaslamaTarihi(DEFAULT_EGITIM_BASLAMA_TARIHI)
            .egitimBitisTarihi(DEFAULT_EGITIM_BITIS_TARIHI)
            .dersSayisi(DEFAULT_DERS_SAYISI)
            .egitimSuresi(DEFAULT_EGITIM_SURESI)
            .egitimYeri(DEFAULT_EGITIM_YERI)
            .egitimPuani(DEFAULT_EGITIM_PUANI)
            .kayit(DEFAULT_KAYIT);
        return tumEgitimler;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TumEgitimler createUpdatedEntity(EntityManager em) {
        TumEgitimler tumEgitimler = new TumEgitimler()
            .egitimBaslik(UPDATED_EGITIM_BASLIK)
            .egitimAltBaslik(UPDATED_EGITIM_ALT_BASLIK)
            .egitimBaslamaTarihi(UPDATED_EGITIM_BASLAMA_TARIHI)
            .egitimBitisTarihi(UPDATED_EGITIM_BITIS_TARIHI)
            .dersSayisi(UPDATED_DERS_SAYISI)
            .egitimSuresi(UPDATED_EGITIM_SURESI)
            .egitimYeri(UPDATED_EGITIM_YERI)
            .egitimPuani(UPDATED_EGITIM_PUANI)
            .kayit(UPDATED_KAYIT);
        return tumEgitimler;
    }

    @BeforeEach
    public void initTest() {
        tumEgitimler = createEntity(em);
    }

    @Test
    @Transactional
    void createTumEgitimler() throws Exception {
        int databaseSizeBeforeCreate = tumEgitimlerRepository.findAll().size();
        // Create the TumEgitimler
        restTumEgitimlerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tumEgitimler)))
            .andExpect(status().isCreated());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeCreate + 1);
        TumEgitimler testTumEgitimler = tumEgitimlerList.get(tumEgitimlerList.size() - 1);
        assertThat(testTumEgitimler.getEgitimBaslik()).isEqualTo(DEFAULT_EGITIM_BASLIK);
        assertThat(testTumEgitimler.getEgitimAltBaslik()).isEqualTo(DEFAULT_EGITIM_ALT_BASLIK);
        assertThat(testTumEgitimler.getEgitimBaslamaTarihi()).isEqualTo(DEFAULT_EGITIM_BASLAMA_TARIHI);
        assertThat(testTumEgitimler.getEgitimBitisTarihi()).isEqualTo(DEFAULT_EGITIM_BITIS_TARIHI);
        assertThat(testTumEgitimler.getDersSayisi()).isEqualTo(DEFAULT_DERS_SAYISI);
        assertThat(testTumEgitimler.getEgitimSuresi()).isEqualTo(DEFAULT_EGITIM_SURESI);
        assertThat(testTumEgitimler.getEgitimYeri()).isEqualTo(DEFAULT_EGITIM_YERI);
        assertThat(testTumEgitimler.getEgitimPuani()).isEqualTo(DEFAULT_EGITIM_PUANI);
        assertThat(testTumEgitimler.getKayit()).isEqualTo(DEFAULT_KAYIT);
    }

    @Test
    @Transactional
    void createTumEgitimlerWithExistingId() throws Exception {
        // Create the TumEgitimler with an existing ID
        tumEgitimler.setId(1L);

        int databaseSizeBeforeCreate = tumEgitimlerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTumEgitimlerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tumEgitimler)))
            .andExpect(status().isBadRequest());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTumEgitimlers() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        // Get all the tumEgitimlerList
        restTumEgitimlerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tumEgitimler.getId().intValue())))
            .andExpect(jsonPath("$.[*].egitimBaslik").value(hasItem(DEFAULT_EGITIM_BASLIK)))
            .andExpect(jsonPath("$.[*].egitimAltBaslik").value(hasItem(DEFAULT_EGITIM_ALT_BASLIK)))
            .andExpect(jsonPath("$.[*].egitimBaslamaTarihi").value(hasItem(DEFAULT_EGITIM_BASLAMA_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].egitimBitisTarihi").value(hasItem(DEFAULT_EGITIM_BITIS_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].dersSayisi").value(hasItem(DEFAULT_DERS_SAYISI)))
            .andExpect(jsonPath("$.[*].egitimSuresi").value(hasItem(DEFAULT_EGITIM_SURESI.doubleValue())))
            .andExpect(jsonPath("$.[*].egitimYeri").value(hasItem(DEFAULT_EGITIM_YERI)))
            .andExpect(jsonPath("$.[*].egitimPuani").value(hasItem(DEFAULT_EGITIM_PUANI.doubleValue())))
            .andExpect(jsonPath("$.[*].kayit").value(hasItem(DEFAULT_KAYIT.booleanValue())));
    }

    @Test
    @Transactional
    void getTumEgitimler() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        // Get the tumEgitimler
        restTumEgitimlerMockMvc
            .perform(get(ENTITY_API_URL_ID, tumEgitimler.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tumEgitimler.getId().intValue()))
            .andExpect(jsonPath("$.egitimBaslik").value(DEFAULT_EGITIM_BASLIK))
            .andExpect(jsonPath("$.egitimAltBaslik").value(DEFAULT_EGITIM_ALT_BASLIK))
            .andExpect(jsonPath("$.egitimBaslamaTarihi").value(DEFAULT_EGITIM_BASLAMA_TARIHI.toString()))
            .andExpect(jsonPath("$.egitimBitisTarihi").value(DEFAULT_EGITIM_BITIS_TARIHI.toString()))
            .andExpect(jsonPath("$.dersSayisi").value(DEFAULT_DERS_SAYISI))
            .andExpect(jsonPath("$.egitimSuresi").value(DEFAULT_EGITIM_SURESI.doubleValue()))
            .andExpect(jsonPath("$.egitimYeri").value(DEFAULT_EGITIM_YERI))
            .andExpect(jsonPath("$.egitimPuani").value(DEFAULT_EGITIM_PUANI.doubleValue()))
            .andExpect(jsonPath("$.kayit").value(DEFAULT_KAYIT.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTumEgitimler() throws Exception {
        // Get the tumEgitimler
        restTumEgitimlerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTumEgitimler() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();

        // Update the tumEgitimler
        TumEgitimler updatedTumEgitimler = tumEgitimlerRepository.findById(tumEgitimler.getId()).get();
        // Disconnect from session so that the updates on updatedTumEgitimler are not directly saved in db
        em.detach(updatedTumEgitimler);
        updatedTumEgitimler
            .egitimBaslik(UPDATED_EGITIM_BASLIK)
            .egitimAltBaslik(UPDATED_EGITIM_ALT_BASLIK)
            .egitimBaslamaTarihi(UPDATED_EGITIM_BASLAMA_TARIHI)
            .egitimBitisTarihi(UPDATED_EGITIM_BITIS_TARIHI)
            .dersSayisi(UPDATED_DERS_SAYISI)
            .egitimSuresi(UPDATED_EGITIM_SURESI)
            .egitimYeri(UPDATED_EGITIM_YERI)
            .egitimPuani(UPDATED_EGITIM_PUANI)
            .kayit(UPDATED_KAYIT);

        restTumEgitimlerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTumEgitimler.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTumEgitimler))
            )
            .andExpect(status().isOk());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
        TumEgitimler testTumEgitimler = tumEgitimlerList.get(tumEgitimlerList.size() - 1);
        assertThat(testTumEgitimler.getEgitimBaslik()).isEqualTo(UPDATED_EGITIM_BASLIK);
        assertThat(testTumEgitimler.getEgitimAltBaslik()).isEqualTo(UPDATED_EGITIM_ALT_BASLIK);
        assertThat(testTumEgitimler.getEgitimBaslamaTarihi()).isEqualTo(UPDATED_EGITIM_BASLAMA_TARIHI);
        assertThat(testTumEgitimler.getEgitimBitisTarihi()).isEqualTo(UPDATED_EGITIM_BITIS_TARIHI);
        assertThat(testTumEgitimler.getDersSayisi()).isEqualTo(UPDATED_DERS_SAYISI);
        assertThat(testTumEgitimler.getEgitimSuresi()).isEqualTo(UPDATED_EGITIM_SURESI);
        assertThat(testTumEgitimler.getEgitimYeri()).isEqualTo(UPDATED_EGITIM_YERI);
        assertThat(testTumEgitimler.getEgitimPuani()).isEqualTo(UPDATED_EGITIM_PUANI);
        assertThat(testTumEgitimler.getKayit()).isEqualTo(UPDATED_KAYIT);
    }

    @Test
    @Transactional
    void putNonExistingTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tumEgitimler.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tumEgitimler))
            )
            .andExpect(status().isBadRequest());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tumEgitimler))
            )
            .andExpect(status().isBadRequest());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tumEgitimler)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTumEgitimlerWithPatch() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();

        // Update the tumEgitimler using partial update
        TumEgitimler partialUpdatedTumEgitimler = new TumEgitimler();
        partialUpdatedTumEgitimler.setId(tumEgitimler.getId());

        partialUpdatedTumEgitimler
            .egitimBaslamaTarihi(UPDATED_EGITIM_BASLAMA_TARIHI)
            .egitimBitisTarihi(UPDATED_EGITIM_BITIS_TARIHI)
            .dersSayisi(UPDATED_DERS_SAYISI)
            .kayit(UPDATED_KAYIT);

        restTumEgitimlerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTumEgitimler.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTumEgitimler))
            )
            .andExpect(status().isOk());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
        TumEgitimler testTumEgitimler = tumEgitimlerList.get(tumEgitimlerList.size() - 1);
        assertThat(testTumEgitimler.getEgitimBaslik()).isEqualTo(DEFAULT_EGITIM_BASLIK);
        assertThat(testTumEgitimler.getEgitimAltBaslik()).isEqualTo(DEFAULT_EGITIM_ALT_BASLIK);
        assertThat(testTumEgitimler.getEgitimBaslamaTarihi()).isEqualTo(UPDATED_EGITIM_BASLAMA_TARIHI);
        assertThat(testTumEgitimler.getEgitimBitisTarihi()).isEqualTo(UPDATED_EGITIM_BITIS_TARIHI);
        assertThat(testTumEgitimler.getDersSayisi()).isEqualTo(UPDATED_DERS_SAYISI);
        assertThat(testTumEgitimler.getEgitimSuresi()).isEqualTo(DEFAULT_EGITIM_SURESI);
        assertThat(testTumEgitimler.getEgitimYeri()).isEqualTo(DEFAULT_EGITIM_YERI);
        assertThat(testTumEgitimler.getEgitimPuani()).isEqualTo(DEFAULT_EGITIM_PUANI);
        assertThat(testTumEgitimler.getKayit()).isEqualTo(UPDATED_KAYIT);
    }

    @Test
    @Transactional
    void fullUpdateTumEgitimlerWithPatch() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();

        // Update the tumEgitimler using partial update
        TumEgitimler partialUpdatedTumEgitimler = new TumEgitimler();
        partialUpdatedTumEgitimler.setId(tumEgitimler.getId());

        partialUpdatedTumEgitimler
            .egitimBaslik(UPDATED_EGITIM_BASLIK)
            .egitimAltBaslik(UPDATED_EGITIM_ALT_BASLIK)
            .egitimBaslamaTarihi(UPDATED_EGITIM_BASLAMA_TARIHI)
            .egitimBitisTarihi(UPDATED_EGITIM_BITIS_TARIHI)
            .dersSayisi(UPDATED_DERS_SAYISI)
            .egitimSuresi(UPDATED_EGITIM_SURESI)
            .egitimYeri(UPDATED_EGITIM_YERI)
            .egitimPuani(UPDATED_EGITIM_PUANI)
            .kayit(UPDATED_KAYIT);

        restTumEgitimlerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTumEgitimler.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTumEgitimler))
            )
            .andExpect(status().isOk());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
        TumEgitimler testTumEgitimler = tumEgitimlerList.get(tumEgitimlerList.size() - 1);
        assertThat(testTumEgitimler.getEgitimBaslik()).isEqualTo(UPDATED_EGITIM_BASLIK);
        assertThat(testTumEgitimler.getEgitimAltBaslik()).isEqualTo(UPDATED_EGITIM_ALT_BASLIK);
        assertThat(testTumEgitimler.getEgitimBaslamaTarihi()).isEqualTo(UPDATED_EGITIM_BASLAMA_TARIHI);
        assertThat(testTumEgitimler.getEgitimBitisTarihi()).isEqualTo(UPDATED_EGITIM_BITIS_TARIHI);
        assertThat(testTumEgitimler.getDersSayisi()).isEqualTo(UPDATED_DERS_SAYISI);
        assertThat(testTumEgitimler.getEgitimSuresi()).isEqualTo(UPDATED_EGITIM_SURESI);
        assertThat(testTumEgitimler.getEgitimYeri()).isEqualTo(UPDATED_EGITIM_YERI);
        assertThat(testTumEgitimler.getEgitimPuani()).isEqualTo(UPDATED_EGITIM_PUANI);
        assertThat(testTumEgitimler.getKayit()).isEqualTo(UPDATED_KAYIT);
    }

    @Test
    @Transactional
    void patchNonExistingTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tumEgitimler.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tumEgitimler))
            )
            .andExpect(status().isBadRequest());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tumEgitimler))
            )
            .andExpect(status().isBadRequest());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTumEgitimler() throws Exception {
        int databaseSizeBeforeUpdate = tumEgitimlerRepository.findAll().size();
        tumEgitimler.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTumEgitimlerMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tumEgitimler))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TumEgitimler in the database
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTumEgitimler() throws Exception {
        // Initialize the database
        tumEgitimlerRepository.saveAndFlush(tumEgitimler);

        int databaseSizeBeforeDelete = tumEgitimlerRepository.findAll().size();

        // Delete the tumEgitimler
        restTumEgitimlerMockMvc
            .perform(delete(ENTITY_API_URL_ID, tumEgitimler.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TumEgitimler> tumEgitimlerList = tumEgitimlerRepository.findAll();
        assertThat(tumEgitimlerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
