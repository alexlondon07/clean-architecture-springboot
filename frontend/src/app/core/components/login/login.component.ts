import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from "../../services/auth.service";
import { User } from '../../models/user';
import { Login } from '../../models/login';

@Component({
  selector: "app-logIn",
  templateUrl: "./logIn.component.html",
  styleUrls: ["./logIn.component.css"],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  private formSubmitAttempt: boolean;
  user: User;

  constructor(private fb: FormBuilder, public authService: AuthService) { }

  ngOnInit() {
    this.form = this.fb.group({
      userName: ['alexlondon07', Validators.required],
      password: ['alexlondon07', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return true;
  }

  onSubmit() {
    if (this.form.valid) {
      this.login();
    }
    this.formSubmitAttempt = true;
  }

  login() {
      const login = new Login(this.form.value.userName,  this.form.value.password);
      this.authService.login(login).subscribe({
      complete: () => console.info('complete') ,
      error: err => {
        console.log(err.error.message);
      },
      next: response => {
        this.manageResponseLogin(response);
      },
});

  }

  manageResponseLogin(response: any) {
    this.user = response;
    this.setLocalStorageLogin(response);
  }

  setLocalStorageLogin(data: any) {
    const response: User = data;
    this.authService.saveStorage( response.id.toString(), response.accessToken, response );
    window.location.href = '#/dashboard';
  }

}
