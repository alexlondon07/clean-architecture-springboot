import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from "../../services/auth.service";

@Component({
  selector: "app-logIn",
  templateUrl: "./logIn.component.html",
  styleUrls: ["./logIn.component.css"],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  private formSubmitAttempt: boolean;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  ngOnInit() {
    this.form = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  isFieldInvalid(field: string) {
    return true;
  }

  onSubmit() {
    if (this.form.valid) {
      // this.authService.login(this.form.value);
    }
    this.formSubmitAttempt = true;
  }
}
