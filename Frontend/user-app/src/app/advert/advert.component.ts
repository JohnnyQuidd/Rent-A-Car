import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CartServiceComponent } from '../service/cart-service/cart-service.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-advert',
  templateUrl: './advert.component.html',
  styleUrls: ['./advert.component.css']
})
export class AdvertComponent implements OnInit {
  uuid: string;
  imageURL: string;
  comments: Comment[];
  cart: string[];
  adverts : Advert[];

  constructor(private http: HttpClient, private router: Router,private cartService : CartServiceComponent) { 
    this.uuid = localStorage.getItem('advertUUID');
    this.fetchAdvert(this.uuid);
    this.fetchComments(this.uuid);
    this.fetchImage(this.uuid);
  }

  ngOnInit(): void {
  }

  async fetchComments(uuid) {
    const apiEndpoint = 'http://localhost:8080/review-service/comment/' + uuid;
    this.http.get(apiEndpoint).subscribe(data => {
      this.comments = data as Array<Comment>;
      console.log('Comments: ', this.comments);
    }, err => {
      console.log('Could not fetch comments!');
    });
  }

  async fetchAdvert(uuid) {
    const apiEndpoint = 'http://localhost:8080/search-service/advert/' + uuid;
    this.http.get(apiEndpoint).subscribe(response => {
        this.adverts = response as Array<Advert>;
    }, err => {
      console.log('Unable to fetch adverts for id ', uuid);
    });
  }

  async fetchImage(uuid : string) {
    const apiEndpoint = 'http://localhost:8080/image-service/profile-image/' + uuid;
    this.http.get(apiEndpoint, {responseType: 'text'}).subscribe(response => {
      this.imageURL = response as string;
    }, err => {
      console.log(`Could not fetch image with id: ${uuid}`);
      console.log(err);
    });
  }

  formatAdvertDate(givenDate: Date) {
    let date = new Date(givenDate);
    return (date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear() + ".");
  }

  onAddToCart(uuid : string) : void {
    this.cart = this.cartService.getFromCart();
    console.log(this.cart);
    //this.cart = this.cartService.getAdvertUUIDS();
      if(this.cart == null)
      {
        this.cart = [];
        this.cart.push(uuid);
        this.cartService.addAdvertsToCart(this.cart)
        //this.cartService.setAdvertUUIDS(this.cart);
        let n  = this.cartService.getAdvertNumber();
        n += 1;
        this.cartService.setAdvertNumber(n);

      }else
      {
        let tempAdvert = this.cart.find(id => id == uuid);
          if(tempAdvert == null)
          {
            this.cart.push(uuid);
            this.cartService.addAdvertsToCart(this.cart)
            //this.cartService.setAdvertUUIDS(this.cart);
            let n  = this.cartService.getAdvertNumber();
            n += 1;
            this.cartService.setAdvertNumber(n);
          }   
          console.log("This advert already in cart!");
      }

  }

  async onDetails(username : string) {
    localStorage.setItem('userDetails', username);
    this.router.navigateByUrl("/user/profile");
  }


}


interface Comment{
  username: string;
  text: string;
  mark: number;
};

export interface Advert {
  publisher: string;
  price: number;
  carLocation: string;
  availableForRentFrom: Date;
  availableForRentUntil: Date;
  brand: string;
  model: string;
  fuel: string;
  transmission: string;
  bodyType: string;
  mileage: number;
  rentLimited: boolean;
  limitInKilometers: number;
  numberOfSeatsForChildren: number;
  uuid: string;
  imageURL: string;
  protectionAvailable: boolean;
  protectionPrice: number;
  description: string;
};