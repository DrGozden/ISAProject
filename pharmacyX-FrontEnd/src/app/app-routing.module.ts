import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllergiesComponent } from './allergies/allergies.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PharmaciesComponent} from "./pharmacies/pharmacies.component";
import { DrugSearchComponent} from "./drug-search/drug-search.component";
import { PharmacyComponent } from './pharmacy/pharmacy.component';
import { DrugReservationsComponent } from './drug-reservations/drug-reservations.component';
import { ExamReservationsComponent } from './exam-reservations/exam-reservations.component';
import { ConsultingReservationsComponent } from './consulting-reservations/consulting-reservations.component';
import { ConsultingReservationComponent } from './consulting-reservation/consulting-reservation.component';
import { DrugReservationComponent } from './drug-reservation/drug-reservation.component';
import { AllDermatologistComponent } from './all-dermatologist/all-dermatologist.component';
import { AllPharmacistComponent } from './all-pharmacist/all-pharmacist.component';
import { FreeApointmentsComponent } from './free-apointments/free-apointments.component';
import { VacationsComponent } from './vacations/vacations.component';
import { OrderComponent } from './order/order.component';
import { OrderPendingComponent } from './order-pending/order-pending.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const routes: Routes = [
  { path: 'homepage', component: HomepageComponent },
  { path: '', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'my-profile', component: UserProfileComponent},
  { path: 'my-allergies', component: AllergiesComponent},
  { path: 'pharmacies', component: PharmaciesComponent},
  { path: 'drug-search', component: DrugSearchComponent},
  { path: 'pharmacy/:id', component: PharmacyComponent},
  { path: 'dermatologists', component: AllDermatologistComponent},
  { path: 'pharmacists', component: AllPharmacistComponent},
  { path: 'drug-reservations', component: DrugReservationsComponent},
  { path: 'exam-reservations', component: ExamReservationsComponent},
  { path: 'consulting-reservations', component: ConsultingReservationsComponent},

  { path: 'drug-reservation/:id', component: DrugReservationComponent},
  { path: 'consulting-reservation', component: ConsultingReservationComponent}, 
  { path: 'free-apointments', component: FreeApointmentsComponent},
  { path: 'vacations', component: VacationsComponent},
  { path: 'order', component: OrderComponent},
  { path: 'pending-orders', component: OrderPendingComponent},
  { path: 'change-password', component: ChangePasswordComponent},

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
