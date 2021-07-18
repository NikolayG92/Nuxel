import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdsByWordComponent } from './ads-by-word/ads-by-word.component';
const routes: Routes = [
  {
    path: 'core',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: 'ads-by-word/:word',
        component: AdsByWordComponent,
      }
    ]
  }
];

export const CoreRoutingModule = RouterModule.forChild(routes);
