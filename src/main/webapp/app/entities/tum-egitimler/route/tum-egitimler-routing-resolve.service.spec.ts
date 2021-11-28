jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITumEgitimler, TumEgitimler } from '../tum-egitimler.model';
import { TumEgitimlerService } from '../service/tum-egitimler.service';

import { TumEgitimlerRoutingResolveService } from './tum-egitimler-routing-resolve.service';

describe('TumEgitimler routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: TumEgitimlerRoutingResolveService;
  let service: TumEgitimlerService;
  let resultTumEgitimler: ITumEgitimler | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(TumEgitimlerRoutingResolveService);
    service = TestBed.inject(TumEgitimlerService);
    resultTumEgitimler = undefined;
  });

  describe('resolve', () => {
    it('should return ITumEgitimler returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTumEgitimler = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTumEgitimler).toEqual({ id: 123 });
    });

    it('should return new ITumEgitimler if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTumEgitimler = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultTumEgitimler).toEqual(new TumEgitimler());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as TumEgitimler })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTumEgitimler = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTumEgitimler).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
