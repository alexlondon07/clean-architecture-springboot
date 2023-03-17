import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { CategoryService } from "src/app/core/services/category.service";

@Component({
  selector: "app-category-form",
  templateUrl: "./category-form.component.html",
  styleUrls: ["./category-form.component.css"],
})
export class CategoryFormComponent implements OnInit {
  public titleForm: string = "Agregar categoría";
  public breakpoint: number; // Breakpoint observer code
  public form: FormGroup;
  wasFormChanged = false;

  groupList: string[] = ["", "Account", "Accruals", "AcctImport"];

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private service: CategoryService
  ) {}

  public ngOnInit(): void {
    this.createFormBuilder();

    this.breakpoint = window.innerWidth <= 600 ? 1 : 2; // Breakpoint observer code
  }

  createFormBuilder(): void {
    this.form = this.fb.group({
      id: null,
      name: [
        null,
        [Validators.required, Validators.pattern("[a-zA-Z]+([a-zA-Z ]+)*")],
      ],
      code: [null, [Validators.required]],
      groupName: [null, [Validators.required]],
      enable: [true, [Validators.required]],
    });

    this.onCreateGroupFormValueChange();
  }

  onCreateGroupFormValueChange(): void {
    this.form.valueChanges.subscribe((value) => {
      console.log(value);
    });
  }

  openDialog(): void {
    this.dialog.closeAll();
  }

  public onAddCus(): void {
    this.markAsDirty(this.form);
  }

  public onResize(event: any): void {
    this.breakpoint = event.target.innerWidth <= 600 ? 1 : 2;
  }

  private markAsDirty(group: FormGroup): void {
    group.markAsDirty();
    for (const i in group.controls) {
      group.controls[i].markAsDirty();
    }
  }

  formChanged() {
    this.wasFormChanged = true;
  }

  resetForm() {
    this.form.reset();
  }

  add() {
    if (this.validateForm()) {
      this.service.create(this.form.value).subscribe({
        complete: () => console.info("complete"),
        error: (err) => {
          console.log(err.error.message);
        },
        next: (resp) => {
          console.log(resp);
          this.resetForm();
        },
      });
    }
  }

  validateForm(): boolean {
    return this.form.valid;
  }
}
