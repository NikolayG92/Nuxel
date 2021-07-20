import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
const routes: Routes = [
  {
    path: 'core',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
      
      }
    ]
  }
];

export const CoreRoutingModule = RouterModule.forChild(routes);
