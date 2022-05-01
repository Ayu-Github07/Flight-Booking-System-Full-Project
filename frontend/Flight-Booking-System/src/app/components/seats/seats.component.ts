import { Component, OnInit } from '@angular/core';
import { Seats } from 'src/app/model/seats/seats';
import { CheckinService } from 'src/app/services/checkin/checkin.service';

@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.css'],
})
export class SeatsComponent implements OnInit {
  seats!: Seats[];

  constructor(private checkService: CheckinService) {}

  ngOnInit(): void {
    this.seats = this.checkService.getSeatDetails();
  }
}
