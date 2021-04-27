import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { AddAdComponent } from './add-ad/add-ad.component';

const routes: Routes = [
  {
    path: 'ad',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: 'add',
        component: AddAdComponent,
        data: {
          isLogged: true,
          title: 'ADD AD',
        },
      },
      {
        path: 'details/:id',
        component: AdDetailsComponent 
      }
    ]
  }
];

export const AdRoutingModule = RouterModule.forChild(routes);
