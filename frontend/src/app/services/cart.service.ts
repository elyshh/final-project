import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CartItem, Product } from '../components/models';

@Injectable({
  providedIn: 'root'
})
export class CartService {
    private cartItemsSubject = new BehaviorSubject<CartItem[]>([]);
    cartItems$: Observable<CartItem[]> = this.cartItemsSubject.asObservable();
  
    constructor() { }
  
    addToCart(product: Product): void {
        const currentCartItems = this.cartItemsSubject.value;
        const existingIndex = currentCartItems.findIndex(item => item.product.id === product.id);
        if (existingIndex !== -1) {
            currentCartItems[existingIndex].quantity++;
        } else {
            currentCartItems.push({ product, quantity: 1 });
        }
        this.cartItemsSubject.next(currentCartItems);
    }
    
    removeFromCart(index: number): void {
        const currentCartItems = this.cartItemsSubject.value;
        currentCartItems.splice(index, 1);
        this.cartItemsSubject.next(currentCartItems);
    }

}
