import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITumEgitimler } from '../tum-egitimler.model';
import { TumEgitimlerService } from '../service/tum-egitimler.service';

@Component({
  templateUrl: './tum-egitimler-delete-dialog.component.html',
})
export class TumEgitimlerDeleteDialogComponent {
  tumEgitimler?: ITumEgitimler;

  constructor(protected tumEgitimlerService: TumEgitimlerService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tumEgitimlerService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
