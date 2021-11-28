jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TumEgitimlerService } from '../service/tum-egitimler.service';
import { ITumEgitimler, TumEgitimler } from '../tum-egitimler.model';

import { TumEgitimlerUpdateComponent } from './tum-egitimler-update.component';

describe('TumEgitimler Management Update Component', () => {
  let comp: TumEgitimlerUpdateComponent;
  let fixture: ComponentFixture<TumEgitimlerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tumEgitimlerService: TumEgitimlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TumEgitimlerUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(TumEgitimlerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TumEgitimlerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tumEgitimlerService = TestBed.inject(TumEgitimlerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tumEgitimler: ITumEgitimler = { id: 456 };

      activatedRoute.data = of({ tumEgitimler });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(tumEgitimler));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TumEgitimler>>();
      const tumEgitimler = { id: 123 };
      jest.spyOn(tumEgitimlerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tumEgitimler });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tumEgitimler }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(tumEgitimlerService.update).toHaveBeenCalledWith(tumEgitimler);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TumEgitimler>>();
      const tumEgitimler = new TumEgitimler();
      jest.spyOn(tumEgitimlerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tumEgitimler });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tumEgitimler }));
      saveSubject.complete();

      // THEN
      expect(tumEgitimlerService.create).toHaveBeenCalledWith(tumEgitimler);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TumEgitimler>>();
      const tumEgitimler = { id: 123 };
      jest.spyOn(tumEgitimlerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tumEgitimler });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tumEgitimlerService.update).toHaveBeenCalledWith(tumEgitimler);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
