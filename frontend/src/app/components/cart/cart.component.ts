import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { CartItem } from '../models';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart: CartItem[] = [];
  total: number = 0;

  constructor(private cartSvc: CartService) { }

  ngOnInit(): void {
    this.cartSvc.cartItems$.subscribe(cartItems => {
      this.cart = cartItems;
      this.calculateTotal();
    });
  }

  calculateTotal(): void {
    this.total = this.cart.reduce((acc, item) => acc + (item.product.price * item.quantity), 0);
  }

  incrementQuantity(item: CartItem): void {
    item.quantity++;
    this.calculateTotal();
  }

  decrementQuantity(item: CartItem): void {
    if (item.quantity > 1) {
      item.quantity--;
      this.calculateTotal();
    }
  }

  removeItem(index: number): void {
    this.cartSvc.removeFromCart(index);
  }
}
