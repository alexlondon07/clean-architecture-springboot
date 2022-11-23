import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";

@Component({
  selector: "app-category-form",
  templateUrl: "./category-form.component.html",
  styleUrls: ["./category-form.component.css"],
})
export class CategoryFormComponent implements OnInit {
  public titleForm: string = "Agregar categoría";
  public breakpoint: number; // Breakpoint observer code
  public fname: string = `Categoria`;
  public form: FormGroup;
  wasFormChanged = false;

  groupList: string[] = [ "",
  "Account",
  "Accruals",
  "AcctImport"
  ];

  constructor(private fb: FormBuilder, public dialog: MatDialog) {}

  public ngOnInit(): void {
    this.form = this.fb.group({
      id: null,
      name: [
        this.fname,
        [Validators.required, Validators.pattern("[a-zA-Z]+([a-zA-Z ]+)*")],
      ],
      code: [
        null,
        [Validators.required],
      ],
      groupName: [
        null,
        [Validators.required],
      ],
      enable: [
        true,
        [Validators.required],
      ],
    });
    this.breakpoint = window.innerWidth <= 600 ? 1 : 2; // Breakpoint observer code
  }

  openDialog(): void {
      this.dialog.closeAll();
  }

  public onAddCus(): void {
    this.markAsDirty(this.form);
  }
  // tslint:disable-next-line:no-any
  public onResize(event: any): void {
    this.breakpoint = event.target.innerWidth <= 600 ? 1 : 2;
  }

  private markAsDirty(group: FormGroup): void {
    group.markAsDirty();
    // tslint:disable-next-line:forin
    for (const i in group.controls) {
      group.controls[i].markAsDirty();
    }
  }

  formChanged() {
    this.wasFormChanged = true;
  }
}
