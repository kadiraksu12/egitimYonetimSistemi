package com.yesevi.egitimkadir.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.yesevi.egitimkadir.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TumEgitimlerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TumEgitimler.class);
        TumEgitimler tumEgitimler1 = new TumEgitimler();
        tumEgitimler1.setId(1L);
        TumEgitimler tumEgitimler2 = new TumEgitimler();
        tumEgitimler2.setId(tumEgitimler1.getId());
        assertThat(tumEgitimler1).isEqualTo(tumEgitimler2);
        tumEgitimler2.setId(2L);
        assertThat(tumEgitimler1).isNotEqualTo(tumEgitimler2);
        tumEgitimler1.setId(null);
        assertThat(tumEgitimler1).isNotEqualTo(tumEgitimler2);
    }
}
