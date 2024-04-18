import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../models';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  productId: string | null = null;
  product: Product | null = null;
  quantity: number = 1;

  constructor(private route: ActivatedRoute, private productSvc: ProductService, private cartSvc: CartService) { }

  ngOnInit(): void {
    const productIdParam = this.route.snapshot.paramMap.get('id');
    this.productId = productIdParam !== null ? productIdParam : null; 
    if (this.productId !== null) {
      this.getProductDetails();
    }
  }

  getProductDetails(): void {
    if (this.productId !== null) {
      this.productSvc.getProductById(this.productId).subscribe(
        (data: Product) => {
          this.product = data;
        },
        (error: any) => {
          console.error('Error fetching product details:', error);
        }
      );
    }
  }

  addToCart(product: Product): void {
    if (product) {
      this.cartSvc.addToCart(product);
    }
  }

  incrementQuantity(): void {
    this.quantity++;
  }

  decrementQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }
}
