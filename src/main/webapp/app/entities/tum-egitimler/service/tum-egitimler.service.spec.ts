import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITumEgitimler, TumEgitimler } from '../tum-egitimler.model';

import { TumEgitimlerService } from './tum-egitimler.service';

describe('TumEgitimler Service', () => {
  let service: TumEgitimlerService;
  let httpMock: HttpTestingController;
  let elemDefault: ITumEgitimler;
  let expectedResult: ITumEgitimler | ITumEgitimler[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TumEgitimlerService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      egitimBaslik: 'AAAAAAA',
      egitimAltBaslik: 'AAAAAAA',
      egitimBaslamaTarihi: currentDate,
      egitimBitisTarihi: currentDate,
      dersSayisi: 0,
      egitimSuresi: 0,
      egitimYeri: 'AAAAAAA',
      egitimPuani: 0,
      kayit: false,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          egitimBaslamaTarihi: currentDate.format(DATE_FORMAT),
          egitimBitisTarihi: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a TumEgitimler', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          egitimBaslamaTarihi: currentDate.format(DATE_FORMAT),
          egitimBitisTarihi: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          egitimBaslamaTarihi: currentDate,
          egitimBitisTarihi: currentDate,
        },
        returnedFromService
      );

      service.create(new TumEgitimler()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TumEgitimler', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          egitimBaslik: 'BBBBBB',
          egitimAltBaslik: 'BBBBBB',
          egitimBaslamaTarihi: currentDate.format(DATE_FORMAT),
          egitimBitisTarihi: currentDate.format(DATE_FORMAT),
          dersSayisi: 1,
          egitimSuresi: 1,
          egitimYeri: 'BBBBBB',
          egitimPuani: 1,
          kayit: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          egitimBaslamaTarihi: currentDate,
          egitimBitisTarihi: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TumEgitimler', () => {
      const patchObject = Object.assign(
        {
          egitimBaslamaTarihi: currentDate.format(DATE_FORMAT),
          egitimBitisTarihi: currentDate.format(DATE_FORMAT),
          dersSayisi: 1,
          egitimYeri: 'BBBBBB',
          kayit: true,
        },
        new TumEgitimler()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          egitimBaslamaTarihi: currentDate,
          egitimBitisTarihi: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TumEgitimler', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          egitimBaslik: 'BBBBBB',
          egitimAltBaslik: 'BBBBBB',
          egitimBaslamaTarihi: currentDate.format(DATE_FORMAT),
          egitimBitisTarihi: currentDate.format(DATE_FORMAT),
          dersSayisi: 1,
          egitimSuresi: 1,
          egitimYeri: 'BBBBBB',
          egitimPuani: 1,
          kayit: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          egitimBaslamaTarihi: currentDate,
          egitimBitisTarihi: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a TumEgitimler', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addTumEgitimlerToCollectionIfMissing', () => {
      it('should add a TumEgitimler to an empty array', () => {
        const tumEgitimler: ITumEgitimler = { id: 123 };
        expectedResult = service.addTumEgitimlerToCollectionIfMissing([], tumEgitimler);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tumEgitimler);
      });

      it('should not add a TumEgitimler to an array that contains it', () => {
        const tumEgitimler: ITumEgitimler = { id: 123 };
        const tumEgitimlerCollection: ITumEgitimler[] = [
          {
            ...tumEgitimler,
          },
          { id: 456 },
        ];
        expectedResult = service.addTumEgitimlerToCollectionIfMissing(tumEgitimlerCollection, tumEgitimler);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TumEgitimler to an array that doesn't contain it", () => {
        const tumEgitimler: ITumEgitimler = { id: 123 };
        const tumEgitimlerCollection: ITumEgitimler[] = [{ id: 456 }];
        expectedResult = service.addTumEgitimlerToCollectionIfMissing(tumEgitimlerCollection, tumEgitimler);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tumEgitimler);
      });

      it('should add only unique TumEgitimler to an array', () => {
        const tumEgitimlerArray: ITumEgitimler[] = [{ id: 123 }, { id: 456 }, { id: 93115 }];
        const tumEgitimlerCollection: ITumEgitimler[] = [{ id: 123 }];
        expectedResult = service.addTumEgitimlerToCollectionIfMissing(tumEgitimlerCollection, ...tumEgitimlerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tumEgitimler: ITumEgitimler = { id: 123 };
        const tumEgitimler2: ITumEgitimler = { id: 456 };
        expectedResult = service.addTumEgitimlerToCollectionIfMissing([], tumEgitimler, tumEgitimler2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tumEgitimler);
        expect(expectedResult).toContain(tumEgitimler2);
      });

      it('should accept null and undefined values', () => {
        const tumEgitimler: ITumEgitimler = { id: 123 };
        expectedResult = service.addTumEgitimlerToCollectionIfMissing([], null, tumEgitimler, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tumEgitimler);
      });

      it('should return initial array if no TumEgitimler is added', () => {
        const tumEgitimlerCollection: ITumEgitimler[] = [{ id: 123 }];
        expectedResult = service.addTumEgitimlerToCollectionIfMissing(tumEgitimlerCollection, undefined, null);
        expect(expectedResult).toEqual(tumEgitimlerCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
