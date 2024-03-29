import { Component, OnInit } from "@angular/core";
import { User } from "src/app/core/models/user";
import { AuthService } from "../../../core/services/auth.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: [],
})
export class AppHeaderComponent implements OnInit {
  user: any;
  constructor(public authService: AuthService) {}

  ngOnInit() {
    this.user = this.authService.getUserLocalStorage();
  }
}
