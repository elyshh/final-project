import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    private apiUrl = '/api/products';

    constructor(private http: HttpClient) { }

    getProducts(category?: string): Observable<any[]> {
        let params = new HttpParams();
        if (category) {
            params = params.set('category', category);
        }
        return this.http.get<any[]>(this.apiUrl, { params });
    }

    getProductById(id: string): Observable<any> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<any>(url);
    }

    filterProducts(products: any[], query: string): any[] {
        if (!query) {
          return products;
        }
        query = query.toLowerCase();
        return products.filter(product => product.title.toLowerCase().startsWith(query));
    }
    
}
