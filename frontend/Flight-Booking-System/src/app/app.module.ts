import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { UserComponent } from './components/user/user.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { FlightSearchComponent } from './components/flight-search/flight-search.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './services/login/login.service';
import { AuthGuard } from './auth.guard';
import { AuthInterceptor } from './services/login/auth.interceptor';
import { BookingDetailsComponent } from './components/booking-details/booking-details.component';
import { UserProfileComponent } from './components/user-dashboard/user-profile/user-profile.component';
import { SideNavComponent } from './components/user-dashboard/pages/side-nav/side-nav.component';
import { PaymentComponent } from './components/payment/payment.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { ManageBookingsComponent } from './components/user-dashboard/manage-bookings/manage-bookings.component';
import { SuccessComponent } from './components/success/success.component';
import { AirportComponent } from './components/airport/airport.component';
import { FlightsComponent } from './components/flights/flights.component';
import { BookingComponent } from './components/booking/booking.component';
import { CheckinComponent } from './components/checkin/checkin.component';
import { AddAirportComponent } from './components/add-airport/add-airport.component';
import { SeatsComponent } from './components/seats/seats.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { TicketDetailsComponent } from './components/ticket-details/ticket-details.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    AdminComponent,
    UserComponent,
    NavbarComponent,
    HomeComponent,
    ForgotPasswordComponent,
    FlightSearchComponent,
    ContactComponent,
    AboutComponent,
    BookingDetailsComponent,
    UserProfileComponent,
    SideNavComponent,
    PaymentComponent,
    UserDashboardComponent,
    ManageBookingsComponent,
    SuccessComponent,
    AirportComponent,
    FlightsComponent,
    BookingComponent,
    CheckinComponent,
    AddAirportComponent,
    SeatsComponent,
    AddFlightComponent,
    TicketDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    LoginService,
    AuthGuard,
    [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
