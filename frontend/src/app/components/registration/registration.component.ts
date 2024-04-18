import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit {

  registrationForm!: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  usernameEntered: boolean = false;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      password1: ['', [Validators.required, Validators.minLength(6)]],
      password2: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      termsAndConditions: [false, Validators.requiredTrue]
    }, { validator: this.ifPasswordMatch });
  }

  ifPasswordMatch(formGroup: FormGroup) {
    const password1Control = formGroup.get('password1');
    const password2Control = formGroup.get('password2');
    if (!password1Control || !password2Control) {
      return;
    }
  
    const password1 = password1Control.value;
    const password2 = password2Control.value;
    if (password1 !== password2) {
      password2Control.setErrors({ mismatch: true });
    } else {
      password2Control.setErrors(null);
    }
  }

  ifUsernameAvailable() {
    const username = this.registrationForm.value.username;
    if (this.usernameEntered && username.length >= 6) {
      this.http.get<boolean>(`/api/check-username/${username}`).subscribe((response) => {
        if (response == false) {
          this.errorMessage = "Username is taken";
        } else {
          this.errorMessage = null;
        }
      });
    } else {
      this.errorMessage = null;
    }
  }

  onUsernameBlur() {
    this.usernameEntered = true;
    this.ifUsernameAvailable();
  }

  onSubmit() {
    this.registrationForm.markAllAsTouched();
  
    if (this.registrationForm.valid && !this.errorMessage) {
      const userData = {
        username: this.registrationForm.value.username,
        password: this.registrationForm.value.password1,
        email: this.registrationForm.value.email
      };
      this.http.post('/api/register', userData, { responseType: 'text' }).subscribe((response: any) => {
        console.log("Response:", response);
        this.successMessage = "Successfully registered!";
        this.registrationForm.reset();
      }, error => {
        console.error("Registration error:", error);
        this.errorMessage = "An error occurred during registration. Please try again.";
      });
    }
  }

}
