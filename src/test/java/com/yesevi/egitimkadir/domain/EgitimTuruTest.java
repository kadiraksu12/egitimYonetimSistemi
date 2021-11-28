package com.yesevi.egitimkadir.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.yesevi.egitimkadir.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EgitimTuruTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EgitimTuru.class);
        EgitimTuru egitimTuru1 = new EgitimTuru();
        egitimTuru1.setId(1L);
        EgitimTuru egitimTuru2 = new EgitimTuru();
        egitimTuru2.setId(egitimTuru1.getId());
        assertThat(egitimTuru1).isEqualTo(egitimTuru2);
        egitimTuru2.setId(2L);
        assertThat(egitimTuru1).isNotEqualTo(egitimTuru2);
        egitimTuru1.setId(null);
        assertThat(egitimTuru1).isNotEqualTo(egitimTuru2);
    }
}
