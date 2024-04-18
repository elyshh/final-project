import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private lastActivityTime: number = Date.now();
  private inactivityThreshold: number = 5 * 60 * 1000; // 5 minutes

  constructor(private http: HttpClient) {
    this.setupActivityTracking();
    this.setupSessionExpiration();
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>('/api/login', { username, password }).pipe(
      tap(response => {
        // Upon successful login, store user information in local storage
        localStorage.setItem('loggedInUser', JSON.stringify({ username }));
        this.lastActivityTime = Date.now(); // Update last activity time
      }),
      catchError(error => {
        throw error; // Handle login error in the component
      })
    );
  }

  logout(): void {
    localStorage.removeItem('loggedInUser');
  }

  getLoggedInUser(): boolean {
    const user = localStorage.getItem('loggedInUser');
    return user ? true : false;
  }

  getLoggedInUsername(): string {
    const user = localStorage.getItem('loggedInUser');
    return user ? JSON.parse(user).username : '';
  }

  private setupActivityTracking(): void {
    // Update last activity time on user interaction events
    window.addEventListener('mousemove', this.updateActivityTime.bind(this));
    window.addEventListener('keydown', this.updateActivityTime.bind(this));
    window.addEventListener('click', this.updateActivityTime.bind(this));
    window.addEventListener('scroll', this.updateActivityTime.bind(this));
  }

  private updateActivityTime(): void {
    this.lastActivityTime = Date.now(); // Update last activity time
  }

  private setupSessionExpiration(): void {
    setInterval(() => {
      const currentTime = Date.now();
      const elapsedTime = currentTime - this.lastActivityTime;
      if (elapsedTime > this.inactivityThreshold && this.getLoggedInUser()) {
        this.logout(); // Clear session if user is logged in and inactive
      }
    }, 1000); // Check every second
  }
}
