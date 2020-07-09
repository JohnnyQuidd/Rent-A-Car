import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CartServiceComponent } from '../service/cart-service/cart-service.component';

@Component({
  selector: 'app-advert',
  templateUrl: './advert.component.html',
  styleUrls: ['./advert.component.css']
})
export class AdvertComponent implements OnInit {

  adverts: Advert[];
  comments: Comment[];
  cart: string[];

  constructor(private http: HttpClient,private cartService : CartServiceComponent) { 
    this.fetchAdvert('75fb7c38-d686-44fc-8c9c-156161e5697f');
    this.fetchComments('75fb7c38-d686-44fc-8c9c-156161e5697f');
  }

  ngOnInit(): void {
  }

  async fetchComments(uuid) {
    const apiEndpoint = 'http://localhost:8080/review-service/comment/' + uuid;
    this.http.get(apiEndpoint).subscribe(data => {
      this.comments = data as Array<Comment>;
    }, err => {
      console.log('Could not fetch comments!');
    });
  }

  async fetchAdvert(uuid) {
    const apiEndpoint = 'http://localhost:8080/posting-service/advert/' + uuid;
    this.http.get(apiEndpoint).subscribe(response => {
        this.adverts = response as Array<Advert>;
        console.log(this.adverts);
    }, err => {
      console.log('Unable to fetch adverts: ', err);
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
}
interface Advert {
  username: string;
  price: number;
  carLocation: string;
  dateStart: Date;
  dateEnd: Date;
  brand: string;
  model: string;
  fuel: string;
  transmission: string;
  bodyType: string;
  mileage: number;
  isRentLimited: boolean;
  limitInKilometers: number;
  numberOfSeatsForChildren: number;
  uuid: string;
  imageURL: string;
};

interface Comment{
  username: string;
  text: string;
  mark: number;
};