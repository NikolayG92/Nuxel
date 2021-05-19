import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { SendMessageComponent } from './send-message/send-message.component';


const routes: Routes = [
  {
    path: 'message',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: 'send-message/:adId/:sellerId/:buyerId',
        component: SendMessageComponent,
        data: {
          isLogged: true,
        },
      }
     
    ]
  }
];

export const MessageRoutingModule = RouterModule.forChild(routes);
