import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { ConversationComponent } from './conversation/conversation.component';
import { MessagesByUserComponent } from './messages-by-user/messages-by-user.component';
import { SendMessageComponent } from './send-message/send-message.component';


const routes: Routes = [
  {
    path: 'messages',
    canActivateChild: [
      AuthGuard
    ],
    children: [
      {
        path: 'send-message/:id',
        component: ConversationComponent,
        data: {
          isLogged: true,
        },
      },
      {
        path: 'newConversation/:id/:senderId',
        component: SendMessageComponent,
        data: {
          isLogged: true,
        },
      },
      {
        path: 'byBuyer/:id',
        component: MessagesByUserComponent
      }
     
    ]
  }
];

export const MessageRoutingModule = RouterModule.forChild(routes);
