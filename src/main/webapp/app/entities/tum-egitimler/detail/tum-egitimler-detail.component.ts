import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITumEgitimler } from '../tum-egitimler.model';

@Component({
  selector: 'jhi-tum-egitimler-detail',
  templateUrl: './tum-egitimler-detail.component.html',
})
export class TumEgitimlerDetailComponent implements OnInit {
  tumEgitimler: ITumEgitimler | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tumEgitimler }) => {
      this.tumEgitimler = tumEgitimler;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
