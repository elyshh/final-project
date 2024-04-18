import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './services/auth.guard';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { LayoutComponent } from './components/layout/layout.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'product/:id', component: ProductDetailsComponent },

  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page if no matching route
  { path: '**', redirectTo: '/login', pathMatch: 'full' } // Redirect to login page for any other invalid route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
