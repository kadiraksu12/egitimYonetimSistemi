import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TumEgitimlerDetailComponent } from './tum-egitimler-detail.component';

describe('TumEgitimler Management Detail Component', () => {
  let comp: TumEgitimlerDetailComponent;
  let fixture: ComponentFixture<TumEgitimlerDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TumEgitimlerDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tumEgitimler: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TumEgitimlerDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TumEgitimlerDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tumEgitimler on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tumEgitimler).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
