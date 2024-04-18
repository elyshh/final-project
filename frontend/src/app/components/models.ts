export interface Product {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: {
        rate: number;
        count: number;
    };
}

export interface Category {
    name: string;
}

export interface CartItem {
    product: Product;
    quantity: number;
  }
  