import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  username: string = '';
  showDropdown: boolean = false;

  constructor(
    private authSvc: AuthService,
  ) {}

  ngOnInit() {
    this.username = this.authSvc.getLoggedInUsername();
  }

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }

  logout() {
    this.authSvc.logout();
  }

}
