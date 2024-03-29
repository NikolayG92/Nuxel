import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { MessagesByUserComponent } from '../message/messages-by-user/messages-by-user.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LoginComponent } from './login/login.component';
import { ProfileDetailsComponent } from './profile-details/profile-details.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: 'user',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: 'register',
        component: RegisterComponent,
        data: {
          isLogged: false,
          noNavigation: true,
          title: 'REGISTER USER'
        },
      },
      {
        path: 'login',
        component: LoginComponent,
        data: {
          isLogged: false,
          title: 'USER LOGIN'
        }
      },
      {
        path: 'profile',
        component: ProfileComponent,
        data: {
          isLogged: true,
          title: 'USER PROFILE'
        }
      },
      {
        path: 'changePassword',
        component: ChangePasswordComponent,
        data: {
          isLogged: true,
          title: 'CHANGE PASSWORD'
        }
      },
      {
        path: 'profileDetails',
        component: ProfileDetailsComponent,
        data: {
          isLogged: true,
          title: 'USER PROFILE'
        }
      }
      
    ]
  }
];

export const UserRoutingModule = RouterModule.forChild(routes);
