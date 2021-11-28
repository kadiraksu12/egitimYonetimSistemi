import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITumEgitimler, TumEgitimler } from '../tum-egitimler.model';
import { TumEgitimlerService } from '../service/tum-egitimler.service';

@Injectable({ providedIn: 'root' })
export class TumEgitimlerRoutingResolveService implements Resolve<ITumEgitimler> {
  constructor(protected service: TumEgitimlerService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITumEgitimler> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tumEgitimler: HttpResponse<TumEgitimler>) => {
          if (tumEgitimler.body) {
            return of(tumEgitimler.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TumEgitimler());
  }
}
