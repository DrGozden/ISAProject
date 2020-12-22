import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllergiesComponent } from './allergies/allergies.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserProfileComponent } from './user-profile/user-profile.component';


const routes: Routes = [
  { path: 'homepage', component: HomepageComponent },
  { path: '', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'my-profile', component: UserProfileComponent},
  { path: 'my-allergies', component: AllergiesComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
