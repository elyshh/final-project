import { Component } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';
import { Category, Product } from '../models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  categories: Category[] = [];
  products: Product[] = [];

  constructor(
    private categorySvc: CategoryService,
    private productSvc: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadCategories();
    this.loadProducts();
  }

  loadCategories(): void {
    this.categorySvc.getCategories().subscribe(categories => {
      this.categories = categories;
    });
  }

  loadProducts(): void {
    this.productSvc.getProducts().subscribe(products => {
      this.products = products;
    });
  }

  filterProductsByCategory(category: Category | string): void {
    const categoryName = typeof category === 'string' ? category : category.name;

    if (categoryName === 'All') {
      this.loadProducts(); // Load all products
    } else {
      this.productSvc.getProducts().subscribe(products => {
        this.products = products.filter(product => product.category === categoryName);
      });
    }
  }

  isProductDetailsPage(): boolean {
    return this.route.snapshot.url[0].path === 'product';
  }
  
  navigateToHomeWithProductDetails(): void {
    this.loadProducts(); // Load all products
    this.router.navigate(['/home']);
  }
  
}
