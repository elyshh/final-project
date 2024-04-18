import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Product } from '../models';

@Component({
  selector: 'app-searchform',
  templateUrl: './searchform.component.html',
  styleUrls: ['./searchform.component.css']
})
export class SearchformComponent implements OnInit {
  searchForm!: FormGroup;
  products: Product[] = [];
  filteredProducts: Product[] = [];
  isProductsEmpty: boolean = false;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      query: ['']
    });

    this.checkProductsEmpty();
  }

  onSubmit(): void {
    const query = this.searchForm.value.query.toLowerCase();
    if (query.trim() === '') {
      this.filteredProducts = [];
    } else {
      this.filteredProducts = this.products.filter(product =>
        product.title.toLowerCase().includes(query)
      );
    }

    this.checkProductsEmpty();
  }

  private checkProductsEmpty(): void {
    this.isProductsEmpty = this.products.length === 0;
  }
}
