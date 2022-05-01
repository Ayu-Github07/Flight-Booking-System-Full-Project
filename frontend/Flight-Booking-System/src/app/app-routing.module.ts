import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AboutComponent } from './components/about/about.component';
import { AddAirportComponent } from './components/add-airport/add-airport.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { AdminComponent } from './components/admin/admin.component';
import { AirportComponent } from './components/airport/airport.component';
import { BookingDetailsComponent } from './components/booking-details/booking-details.component';
import { BookingComponent } from './components/booking/booking.component';
import { CheckinComponent } from './components/checkin/checkin.component';
import { ContactComponent } from './components/contact/contact.component';
import { FlightSearchComponent } from './components/flight-search/flight-search.component';
import { FlightsComponent } from './components/flights/flights.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PaymentComponent } from './components/payment/payment.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { SeatsComponent } from './components/seats/seats.component';
import { SuccessComponent } from './components/success/success.component';
import { TicketDetailsComponent } from './components/ticket-details/ticket-details.component';
import { ManageBookingsComponent } from './components/user-dashboard/manage-bookings/manage-bookings.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserProfileComponent } from './components/user-dashboard/user-profile/user-profile.component';
import { UserComponent } from './components/user/user.component';
import { HasRoleGuardGuard } from './has-role-guard.guard';
import { UserRoleGuard } from './user-role.guard';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'profile',
    component: UserComponent,
    canActivate: [AuthGuard, UserRoleGuard],
  },
  {
    path: 'booking',
    component: BookingDetailsComponent,
    canActivate: [AuthGuard, UserRoleGuard],
  },
  { path: 'flight-search', component: FlightSearchComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'about', component: AboutComponent },
  {
    path: 'payment',
    component: PaymentComponent,
    canActivate: [AuthGuard, UserRoleGuard],
  },
  {
    path: 'profile/user',
    component: UserDashboardComponent,
    children: [
      {
        path: '',
        component: UserProfileComponent,
      },
    ],
    canActivate: [AuthGuard, UserRoleGuard],
  },
  {
    path: 'profile/manageBooking',
    component: UserDashboardComponent,
    children: [
      {
        path: '',
        component: ManageBookingsComponent,
      },
    ],
    canActivate: [AuthGuard],
  },
  {
    path: 'check-in/seats',
    component: SeatsComponent,
    canActivate: [AuthGuard, UserRoleGuard],
  },
  {
    path: 'success',
    component: SuccessComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'airport-details',
    component: AirportComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'flight-details',
    component: FlightsComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'booking-details',
    component: BookingComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'check-in',
    component: CheckinComponent,
    canActivate: [AuthGuard, UserRoleGuard],
  },
  {
    path: 'add-airport',
    component: AddAirportComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'flight-details/add-flight',
    component: AddFlightComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
  {
    path: 'ticket-details',
    component: TicketDetailsComponent,
    canActivate: [AuthGuard, HasRoleGuardGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
