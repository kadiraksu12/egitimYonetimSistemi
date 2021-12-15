import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TakvimComponent } from './list/takvim.component';
import { TakvimDetailComponent } from './detail/takvim-detail.component';
import { TakvimUpdateComponent } from './update/takvim-update.component';
import { TakvimDeleteDialogComponent } from './delete/takvim-delete-dialog.component';
import { TakvimRoutingModule } from './route/takvim-routing.module';
import { FullCalendarModule } from '@fullcalendar/angular';

@NgModule({
  imports: [SharedModule, TakvimRoutingModule, FullCalendarModule],
  declarations: [TakvimComponent, TakvimDetailComponent, TakvimUpdateComponent, TakvimDeleteDialogComponent],
  entryComponents: [TakvimDeleteDialogComponent],
  exports: [TakvimComponent],
})
export class TakvimModule {}
