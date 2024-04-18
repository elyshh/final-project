import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../models';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  @Input() products: Product[] = [];

}
