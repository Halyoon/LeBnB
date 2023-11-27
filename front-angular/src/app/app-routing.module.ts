import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { RegisterComponent } from './pages/register/register.component';
import { authGuard } from './auth/auth.guard';

 const routes: Routes = [
    {
        path: "login",
        component: LoginComponent
    },
    {

        path: "welcome",
        component: WelcomeComponent,
        canActivate: [authGuard]
    },
    {
        path: "register",
        component: RegisterComponent
    }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
