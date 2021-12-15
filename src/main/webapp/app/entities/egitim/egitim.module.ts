import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EgitimComponent } from './list/egitim.component';
import { EgitimDetailComponent } from './detail/egitim-detail.component';
import { EgitimUpdateComponent } from './update/egitim-update.component';
import { EgitimDeleteDialogComponent } from './delete/egitim-delete-dialog.component';
import { EgitimRoutingModule } from './route/egitim-routing.module';
import { DuyuruModule } from '../duyuru/duyuru.module';
import { TakvimModule } from '../takvim/takvim.module';

@NgModule({
  imports: [SharedModule, EgitimRoutingModule, DuyuruModule, TakvimModule],
  declarations: [EgitimComponent, EgitimDetailComponent, EgitimUpdateComponent, EgitimDeleteDialogComponent],
  entryComponents: [EgitimDeleteDialogComponent],
})
export class EgitimModule {}
