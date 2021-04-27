import { Routes, RouterModule } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';

const routes: Routes = [
    {
        path: 'about',
        component: AboutUsComponent,
      }
    ];


export const AboutRoutingModule = RouterModule.forChild(routes);
