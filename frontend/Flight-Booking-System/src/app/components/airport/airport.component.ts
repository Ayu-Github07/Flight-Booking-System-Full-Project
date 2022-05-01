import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Airport } from 'src/app/model/airport';
import { AirportsService } from 'src/app/services/airports.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.css'],
})
export class AirportComponent implements OnInit {
  public airportDetails!: Airport[];

  constructor(
    private airportService: AirportsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.airportService.getAllAirports().subscribe((data) => {
      this.airportDetails = data;
      console.log(`Airport Details`);
      console.log(this.airportDetails);
    });
  }

  updateAirport(airport: Airport) {
    console.log(airport);
    Swal.fire({
      title: 'Are you sure you want to update?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, update it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.airportService.setAirportById(airport);
        console.log(this.airportService.getAirportById());
        this.router.navigate(['../add-airport']);
      }
    });
    this.airportService.setAirportById(airport);
  }

  deleteAirport(id: any) {
    Swal.fire({
      title: 'Are you sure?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.airportService.deleteAirportDetails(id).subscribe((data) => {
          console.log(data);
        });
        Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
        location.reload();
      }
    });
  }
}
