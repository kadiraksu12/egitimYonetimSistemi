import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'egitim',
        data: { pageTitle: 'egitimkadirApp.egitim.home.title' },
        loadChildren: () => import('./egitim/egitim.module').then(m => m.EgitimModule),
      },
      {
        path: 'egitim-turu',
        data: { pageTitle: 'egitimkadirApp.egitimTuru.home.title' },
        loadChildren: () => import('./egitim-turu/egitim-turu.module').then(m => m.EgitimTuruModule),
      },
      {
        path: 'kurum',
        data: { pageTitle: 'egitimkadirApp.kurum.home.title' },
        loadChildren: () => import('./kurum/kurum.module').then(m => m.KurumModule),
      },
      {
        path: 'egitmen',
        data: { pageTitle: 'egitimkadirApp.egitmen.home.title' },
        loadChildren: () => import('./egitmen/egitmen.module').then(m => m.EgitmenModule),
      },
      {
        path: 'ogrenci-egitimler',
        data: { pageTitle: 'egitimkadirApp.ogrenciEgitimler.home.title' },
        loadChildren: () => import('./ogrenci-egitimler/ogrenci-egitimler.module').then(m => m.OgrenciEgitimlerModule),
      },
      {
        path: 'tum-egitimler',
        data: { pageTitle: 'egitimkadirApp.tumEgitimler.home.title' },
        loadChildren: () => import('./tum-egitimler/tum-egitimler.module').then(m => m.TumEgitimlerModule),
      },
      {
        path: 'duyuru',
        data: { pageTitle: 'egitimkadirApp.duyuru.home.title' },
        loadChildren: () => import('./duyuru/duyuru.module').then(m => m.DuyuruModule),
      },
      {
        path: 'ders',
        data: { pageTitle: 'egitimkadirApp.ders.home.title' },
        loadChildren: () => import('./ders/ders.module').then(m => m.DersModule),
      },
      {
        path: 'egitim-dersler',
        data: { pageTitle: 'egitimkadirApp.egitimDersler.home.title' },
        loadChildren: () => import('./egitim-dersler/egitim-dersler.module').then(m => m.EgitimDerslerModule),
      },
      {
        path: 'takvim',
        data: { pageTitle: 'egitimkadirApp.takvim.home.title' },
        loadChildren: () => import('./takvim/takvim.module').then(m => m.TakvimModule),
      },
      {
        path: 'application-user',
        data: { pageTitle: 'egitimkadirApp.applicationUser.home.title' },
        loadChildren: () => import('./application-user/application-user.module').then(m => m.ApplicationUserModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
