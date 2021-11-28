import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITumEgitimler, TumEgitimler } from '../tum-egitimler.model';
import { TumEgitimlerService } from '../service/tum-egitimler.service';

@Component({
  selector: 'jhi-tum-egitimler-update',
  templateUrl: './tum-egitimler-update.component.html',
})
export class TumEgitimlerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    egitimBaslik: [],
    egitimAltBaslik: [],
    egitimBaslamaTarihi: [],
    egitimBitisTarihi: [],
    dersSayisi: [],
    egitimSuresi: [],
    egitimYeri: [],
    egitimPuani: [],
    kayit: [],
  });

  constructor(protected tumEgitimlerService: TumEgitimlerService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tumEgitimler }) => {
      this.updateForm(tumEgitimler);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tumEgitimler = this.createFromForm();
    if (tumEgitimler.id !== undefined) {
      this.subscribeToSaveResponse(this.tumEgitimlerService.update(tumEgitimler));
    } else {
      this.subscribeToSaveResponse(this.tumEgitimlerService.create(tumEgitimler));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITumEgitimler>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(tumEgitimler: ITumEgitimler): void {
    this.editForm.patchValue({
      id: tumEgitimler.id,
      egitimBaslik: tumEgitimler.egitimBaslik,
      egitimAltBaslik: tumEgitimler.egitimAltBaslik,
      egitimBaslamaTarihi: tumEgitimler.egitimBaslamaTarihi,
      egitimBitisTarihi: tumEgitimler.egitimBitisTarihi,
      dersSayisi: tumEgitimler.dersSayisi,
      egitimSuresi: tumEgitimler.egitimSuresi,
      egitimYeri: tumEgitimler.egitimYeri,
      egitimPuani: tumEgitimler.egitimPuani,
      kayit: tumEgitimler.kayit,
    });
  }

  protected createFromForm(): ITumEgitimler {
    return {
      ...new TumEgitimler(),
      id: this.editForm.get(['id'])!.value,
      egitimBaslik: this.editForm.get(['egitimBaslik'])!.value,
      egitimAltBaslik: this.editForm.get(['egitimAltBaslik'])!.value,
      egitimBaslamaTarihi: this.editForm.get(['egitimBaslamaTarihi'])!.value,
      egitimBitisTarihi: this.editForm.get(['egitimBitisTarihi'])!.value,
      dersSayisi: this.editForm.get(['dersSayisi'])!.value,
      egitimSuresi: this.editForm.get(['egitimSuresi'])!.value,
      egitimYeri: this.editForm.get(['egitimYeri'])!.value,
      egitimPuani: this.editForm.get(['egitimPuani'])!.value,
      kayit: this.editForm.get(['kayit'])!.value,
    };
  }
}
