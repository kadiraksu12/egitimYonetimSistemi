import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITumEgitimler, getTumEgitimlerIdentifier } from '../tum-egitimler.model';

export type EntityResponseType = HttpResponse<ITumEgitimler>;
export type EntityArrayResponseType = HttpResponse<ITumEgitimler[]>;

@Injectable({ providedIn: 'root' })
export class TumEgitimlerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tum-egitimlers');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(tumEgitimler: ITumEgitimler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tumEgitimler);
    return this.http
      .post<ITumEgitimler>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tumEgitimler: ITumEgitimler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tumEgitimler);
    return this.http
      .put<ITumEgitimler>(`${this.resourceUrl}/${getTumEgitimlerIdentifier(tumEgitimler) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tumEgitimler: ITumEgitimler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tumEgitimler);
    return this.http
      .patch<ITumEgitimler>(`${this.resourceUrl}/${getTumEgitimlerIdentifier(tumEgitimler) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITumEgitimler>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITumEgitimler[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTumEgitimlerToCollectionIfMissing(
    tumEgitimlerCollection: ITumEgitimler[],
    ...tumEgitimlersToCheck: (ITumEgitimler | null | undefined)[]
  ): ITumEgitimler[] {
    const tumEgitimlers: ITumEgitimler[] = tumEgitimlersToCheck.filter(isPresent);
    if (tumEgitimlers.length > 0) {
      const tumEgitimlerCollectionIdentifiers = tumEgitimlerCollection.map(
        tumEgitimlerItem => getTumEgitimlerIdentifier(tumEgitimlerItem)!
      );
      const tumEgitimlersToAdd = tumEgitimlers.filter(tumEgitimlerItem => {
        const tumEgitimlerIdentifier = getTumEgitimlerIdentifier(tumEgitimlerItem);
        if (tumEgitimlerIdentifier == null || tumEgitimlerCollectionIdentifiers.includes(tumEgitimlerIdentifier)) {
          return false;
        }
        tumEgitimlerCollectionIdentifiers.push(tumEgitimlerIdentifier);
        return true;
      });
      return [...tumEgitimlersToAdd, ...tumEgitimlerCollection];
    }
    return tumEgitimlerCollection;
  }

  protected convertDateFromClient(tumEgitimler: ITumEgitimler): ITumEgitimler {
    return Object.assign({}, tumEgitimler, {
      egitimBaslamaTarihi: tumEgitimler.egitimBaslamaTarihi?.isValid() ? tumEgitimler.egitimBaslamaTarihi.format(DATE_FORMAT) : undefined,
      egitimBitisTarihi: tumEgitimler.egitimBitisTarihi?.isValid() ? tumEgitimler.egitimBitisTarihi.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.egitimBaslamaTarihi = res.body.egitimBaslamaTarihi ? dayjs(res.body.egitimBaslamaTarihi) : undefined;
      res.body.egitimBitisTarihi = res.body.egitimBitisTarihi ? dayjs(res.body.egitimBitisTarihi) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tumEgitimler: ITumEgitimler) => {
        tumEgitimler.egitimBaslamaTarihi = tumEgitimler.egitimBaslamaTarihi ? dayjs(tumEgitimler.egitimBaslamaTarihi) : undefined;
        tumEgitimler.egitimBitisTarihi = tumEgitimler.egitimBitisTarihi ? dayjs(tumEgitimler.egitimBitisTarihi) : undefined;
      });
    }
    return res;
  }
}
