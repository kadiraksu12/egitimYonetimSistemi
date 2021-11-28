import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TumEgitimlerComponent } from '../list/tum-egitimler.component';
import { TumEgitimlerDetailComponent } from '../detail/tum-egitimler-detail.component';
import { TumEgitimlerUpdateComponent } from '../update/tum-egitimler-update.component';
import { TumEgitimlerRoutingResolveService } from './tum-egitimler-routing-resolve.service';

const tumEgitimlerRoute: Routes = [
  {
    path: '',
    component: TumEgitimlerComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TumEgitimlerDetailComponent,
    resolve: {
      tumEgitimler: TumEgitimlerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TumEgitimlerUpdateComponent,
    resolve: {
      tumEgitimler: TumEgitimlerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TumEgitimlerUpdateComponent,
    resolve: {
      tumEgitimler: TumEgitimlerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tumEgitimlerRoute)],
  exports: [RouterModule],
})
export class TumEgitimlerRoutingModule {}
