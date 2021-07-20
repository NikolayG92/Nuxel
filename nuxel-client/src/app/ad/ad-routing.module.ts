import { RouterModule, Routes } from '@angular/router';
import { AdsByWordComponent } from './ads-by-word/ads-by-word.component';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { AddAdComponent } from './add-ad/add-ad.component';
import { AdsByUserComponent } from './ads-by-user/ads-by-user.component';
import { EditAdComponent } from './edit-ad/edit-ad.component';

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
      },
      {
        path: 'byUser/:id',
        component: AdsByUserComponent
      },
      {
        path: 'edit/:id',
        component: EditAdComponent
      },
      {
        path: 'ads-by-word/:word',
        component: AdsByWordComponent
      }
    ]
  }
];

export const AdRoutingModule = RouterModule.forChild(routes);
