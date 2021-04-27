import { RouterModule, Routes } from '@angular/router';
import { AdsByCategoryComponent } from '../ad/ads-by-category/ads-by-category.component';
import { AuthGuard } from '../core/guards/auth.guard';
const routes: Routes = [
  {
    path: 'categories',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: ':id',
        component: AdsByCategoryComponent    
      }
    ]
  }
];

export const CategoryRoutingModule = RouterModule.forChild(routes);
