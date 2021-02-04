import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { JwtInterceptor } from './interceptors/jwt-interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomepageComponent } from './homepage/homepage.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AllergiesComponent } from './allergies/allergies.component';
import { PharmaciesComponent } from './pharmacies/pharmacies.component';
import { DrugSearchComponent } from './drug-search/drug-search.component';
import { PharmacyComponent } from './pharmacy/pharmacy.component';
import { DrugReservationsComponent } from './drug-reservations/drug-reservations.component';
import { ConsultingReservationsComponent } from './consulting-reservations/consulting-reservations.component';
import { ExamReservationsComponent } from './exam-reservations/exam-reservations.component';
import { DrugReservationComponent } from './drug-reservation/drug-reservation.component';
import { ConsultingReservationComponent } from './consulting-reservation/consulting-reservation.component';
import { DatePipe } from '@angular/common';
import { AllPharmacistComponent } from './all-pharmacist/all-pharmacist.component';
import { AllDermatologistComponent } from './all-dermatologist/all-dermatologist.component';
import { FreeApointmentsComponent } from './free-apointments/free-apointments.component';
import { VacationsComponent } from './vacations/vacations.component';
import { OrderComponent } from './order/order.component';
import { OrderPendingComponent } from './order-pending/order-pending.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomepageComponent,
    UserProfileComponent,
    AllergiesComponent,
    PharmaciesComponent,
    DrugSearchComponent,
    PharmacyComponent,
    DrugReservationsComponent,
    ConsultingReservationsComponent,
    ExamReservationsComponent,
    DrugReservationComponent,
    ConsultingReservationComponent,
    AllPharmacistComponent,
    AllDermatologistComponent,
    FreeApointmentsComponent,
    VacationsComponent,
    OrderComponent,
    OrderPendingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({preventDuplicates: true})
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
