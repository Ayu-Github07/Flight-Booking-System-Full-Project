import { Component, OnInit } from '@angular/core';
import { Ticket } from 'src/app/model/ticket/ticket';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';
import { SuccessService } from 'src/app/services/success/success.service';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';
import * as pdfMake from 'pdfmake/build/pdfmake';
import { Router } from '@angular/router';
const pdfMakeX = require('pdfmake/build/pdfmake.js');
const pdfFontsX = require('pdfmake-unicode/dist/pdfmake-unicode.js');
pdfMakeX.vfs = pdfFontsX.pdfMake.vfs;

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css'],
})
export class SuccessComponent implements OnInit {
  ticketId!: any;
  bookingDetails!: Ticket;
  generateTicket = new jsPDF();

  constructor(
    private bookingService: BookingDetailsService,
    private successService: SuccessService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ticketId = this.bookingService.getTicketId();
    console.log('TicketId', this.ticketId);

    this.successService
      .getBookingDetailsByTicketId(this.ticketId)
      .subscribe((data) => {
        this.bookingDetails = data;
        console.log(data);
      });
  }

  generatePdf(action = 'open') {
    let pdf = new jsPDF('p', 'pt', 'a4');

    pdf.text(`Your Ticket Id: ${this.bookingDetails.ticketId}`, 10, 10);
    pdf.save();

    this.router.navigate(['']);
  }
}
