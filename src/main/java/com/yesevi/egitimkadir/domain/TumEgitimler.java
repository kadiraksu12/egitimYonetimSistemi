package com.yesevi.egitimkadir.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TumEgitimler.
 */
@Entity
@Table(name = "tum_egitimler")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TumEgitimler implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "egitim_baslik")
    private String egitimBaslik;

    @Column(name = "egitim_alt_baslik")
    private String egitimAltBaslik;

    @Column(name = "egitim_baslama_tarihi")
    private LocalDate egitimBaslamaTarihi;

    @Column(name = "egitim_bitis_tarihi")
    private LocalDate egitimBitisTarihi;

    @Column(name = "ders_sayisi")
    private Integer dersSayisi;

    @Column(name = "egitim_suresi")
    private Float egitimSuresi;

    @Column(name = "egitim_yeri")
    private String egitimYeri;

    @Column(name = "egitim_puani")
    private Float egitimPuani;

    @Column(name = "kayit")
    private Boolean kayit;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TumEgitimler id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEgitimBaslik() {
        return this.egitimBaslik;
    }

    public TumEgitimler egitimBaslik(String egitimBaslik) {
        this.setEgitimBaslik(egitimBaslik);
        return this;
    }

    public void setEgitimBaslik(String egitimBaslik) {
        this.egitimBaslik = egitimBaslik;
    }

    public String getEgitimAltBaslik() {
        return this.egitimAltBaslik;
    }

    public TumEgitimler egitimAltBaslik(String egitimAltBaslik) {
        this.setEgitimAltBaslik(egitimAltBaslik);
        return this;
    }

    public void setEgitimAltBaslik(String egitimAltBaslik) {
        this.egitimAltBaslik = egitimAltBaslik;
    }

    public LocalDate getEgitimBaslamaTarihi() {
        return this.egitimBaslamaTarihi;
    }

    public TumEgitimler egitimBaslamaTarihi(LocalDate egitimBaslamaTarihi) {
        this.setEgitimBaslamaTarihi(egitimBaslamaTarihi);
        return this;
    }

    public void setEgitimBaslamaTarihi(LocalDate egitimBaslamaTarihi) {
        this.egitimBaslamaTarihi = egitimBaslamaTarihi;
    }

    public LocalDate getEgitimBitisTarihi() {
        return this.egitimBitisTarihi;
    }

    public TumEgitimler egitimBitisTarihi(LocalDate egitimBitisTarihi) {
        this.setEgitimBitisTarihi(egitimBitisTarihi);
        return this;
    }

    public void setEgitimBitisTarihi(LocalDate egitimBitisTarihi) {
        this.egitimBitisTarihi = egitimBitisTarihi;
    }

    public Integer getDersSayisi() {
        return this.dersSayisi;
    }

    public TumEgitimler dersSayisi(Integer dersSayisi) {
        this.setDersSayisi(dersSayisi);
        return this;
    }

    public void setDersSayisi(Integer dersSayisi) {
        this.dersSayisi = dersSayisi;
    }

    public Float getEgitimSuresi() {
        return this.egitimSuresi;
    }

    public TumEgitimler egitimSuresi(Float egitimSuresi) {
        this.setEgitimSuresi(egitimSuresi);
        return this;
    }

    public void setEgitimSuresi(Float egitimSuresi) {
        this.egitimSuresi = egitimSuresi;
    }

    public String getEgitimYeri() {
        return this.egitimYeri;
    }

    public TumEgitimler egitimYeri(String egitimYeri) {
        this.setEgitimYeri(egitimYeri);
        return this;
    }

    public void setEgitimYeri(String egitimYeri) {
        this.egitimYeri = egitimYeri;
    }

    public Float getEgitimPuani() {
        return this.egitimPuani;
    }

    public TumEgitimler egitimPuani(Float egitimPuani) {
        this.setEgitimPuani(egitimPuani);
        return this;
    }

    public void setEgitimPuani(Float egitimPuani) {
        this.egitimPuani = egitimPuani;
    }

    public Boolean getKayit() {
        return this.kayit;
    }

    public TumEgitimler kayit(Boolean kayit) {
        this.setKayit(kayit);
        return this;
    }

    public void setKayit(Boolean kayit) {
        this.kayit = kayit;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TumEgitimler)) {
            return false;
        }
        return id != null && id.equals(((TumEgitimler) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TumEgitimler{" +
            "id=" + getId() +
            ", egitimBaslik='" + getEgitimBaslik() + "'" +
            ", egitimAltBaslik='" + getEgitimAltBaslik() + "'" +
            ", egitimBaslamaTarihi='" + getEgitimBaslamaTarihi() + "'" +
            ", egitimBitisTarihi='" + getEgitimBitisTarihi() + "'" +
            ", dersSayisi=" + getDersSayisi() +
            ", egitimSuresi=" + getEgitimSuresi() +
            ", egitimYeri='" + getEgitimYeri() + "'" +
            ", egitimPuani=" + getEgitimPuani() +
            ", kayit='" + getKayit() + "'" +
            "}";
    }
}
