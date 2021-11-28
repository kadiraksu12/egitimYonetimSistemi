package com.yesevi.egitimkadir.service.impl;

import com.yesevi.egitimkadir.domain.TumEgitimler;
import com.yesevi.egitimkadir.repository.TumEgitimlerRepository;
import com.yesevi.egitimkadir.service.TumEgitimlerService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TumEgitimler}.
 */
@Service
@Transactional
public class TumEgitimlerServiceImpl implements TumEgitimlerService {

    private final Logger log = LoggerFactory.getLogger(TumEgitimlerServiceImpl.class);

    private final TumEgitimlerRepository tumEgitimlerRepository;

    public TumEgitimlerServiceImpl(TumEgitimlerRepository tumEgitimlerRepository) {
        this.tumEgitimlerRepository = tumEgitimlerRepository;
    }

    @Override
    public TumEgitimler save(TumEgitimler tumEgitimler) {
        log.debug("Request to save TumEgitimler : {}", tumEgitimler);
        return tumEgitimlerRepository.save(tumEgitimler);
    }

    @Override
    public Optional<TumEgitimler> partialUpdate(TumEgitimler tumEgitimler) {
        log.debug("Request to partially update TumEgitimler : {}", tumEgitimler);

        return tumEgitimlerRepository
            .findById(tumEgitimler.getId())
            .map(existingTumEgitimler -> {
                if (tumEgitimler.getEgitimBaslik() != null) {
                    existingTumEgitimler.setEgitimBaslik(tumEgitimler.getEgitimBaslik());
                }
                if (tumEgitimler.getEgitimAltBaslik() != null) {
                    existingTumEgitimler.setEgitimAltBaslik(tumEgitimler.getEgitimAltBaslik());
                }
                if (tumEgitimler.getEgitimBaslamaTarihi() != null) {
                    existingTumEgitimler.setEgitimBaslamaTarihi(tumEgitimler.getEgitimBaslamaTarihi());
                }
                if (tumEgitimler.getEgitimBitisTarihi() != null) {
                    existingTumEgitimler.setEgitimBitisTarihi(tumEgitimler.getEgitimBitisTarihi());
                }
                if (tumEgitimler.getDersSayisi() != null) {
                    existingTumEgitimler.setDersSayisi(tumEgitimler.getDersSayisi());
                }
                if (tumEgitimler.getEgitimSuresi() != null) {
                    existingTumEgitimler.setEgitimSuresi(tumEgitimler.getEgitimSuresi());
                }
                if (tumEgitimler.getEgitimYeri() != null) {
                    existingTumEgitimler.setEgitimYeri(tumEgitimler.getEgitimYeri());
                }
                if (tumEgitimler.getEgitimPuani() != null) {
                    existingTumEgitimler.setEgitimPuani(tumEgitimler.getEgitimPuani());
                }
                if (tumEgitimler.getKayit() != null) {
                    existingTumEgitimler.setKayit(tumEgitimler.getKayit());
                }

                return existingTumEgitimler;
            })
            .map(tumEgitimlerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TumEgitimler> findAll(Pageable pageable) {
        log.debug("Request to get all TumEgitimlers");
        return tumEgitimlerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TumEgitimler> findOne(Long id) {
        log.debug("Request to get TumEgitimler : {}", id);
        return tumEgitimlerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TumEgitimler : {}", id);
        tumEgitimlerRepository.deleteById(id);
    }
}
