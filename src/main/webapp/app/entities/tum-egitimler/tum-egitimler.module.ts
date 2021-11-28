import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TumEgitimlerComponent } from './list/tum-egitimler.component';
import { TumEgitimlerDetailComponent } from './detail/tum-egitimler-detail.component';
import { TumEgitimlerUpdateComponent } from './update/tum-egitimler-update.component';
import { TumEgitimlerDeleteDialogComponent } from './delete/tum-egitimler-delete-dialog.component';
import { TumEgitimlerRoutingModule } from './route/tum-egitimler-routing.module';

@NgModule({
  imports: [SharedModule, TumEgitimlerRoutingModule],
  declarations: [TumEgitimlerComponent, TumEgitimlerDetailComponent, TumEgitimlerUpdateComponent, TumEgitimlerDeleteDialogComponent],
  entryComponents: [TumEgitimlerDeleteDialogComponent],
})
export class TumEgitimlerModule {}
