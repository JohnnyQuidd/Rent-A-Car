import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ReviewComponent } from 'src/app/review/review.component';

@Component({
  selector: 'app-user-homepage',
  templateUrl: './user-homepage.component.html',
  styleUrls: ['./user-homepage.component.css']
})
export class UserHomepageComponent implements OnInit {

  numberOfAdverts = [1, 2];

  constructor(private http: HttpClient, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openDialog() {
    let dialogRef = this.dialog.open(ReviewComponent, {
      data: {username: 'Skinny Pete', uuid: '5ccff5a6-b1b2-11ea-b3de-0242ac130004'},
      height: '350px',
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });

  }

  onLogout() {
    const apiEndpoint = 'http://localhost:8080/auth-service/logout';
    this.http.post(apiEndpoint, {responseType: 'json', withCredentials: true}).subscribe(data => {
      localStorage.clear();
      this.router.navigateByUrl('/login');
    }, err => {
      console.log('Unable to log out');
    });
  }

}
