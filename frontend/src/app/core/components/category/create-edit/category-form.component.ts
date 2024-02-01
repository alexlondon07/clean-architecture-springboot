import { Component, Inject, OnInit, Optional } from "@angular/core";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from "@angular/material/dialog";
import { Category } from "src/app/core/models/category";
import { CategoryService } from "src/app/core/services/category.service";
import { MessageApp } from "src/app/utils/messages";

@Component({
  selector: "app-category-form",
  templateUrl: "./category-form.component.html",
  styleUrls: ["./category-form.component.css"],
})
export class CategoryFormComponent implements OnInit {
  public titleForm: string = "Agregar categoría";
  public titleButton: string = "Crear";
  public breakpoint: number; // Breakpoint observer code
  public form: FormGroup;
  wasFormChanged = false;
  category: Category = new Category();

  groupList: string[] = ["", "General", "Otro"];

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private service: CategoryService,
    public dialogRef: MatDialogRef<CategoryFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.category = data;

    if (this.category?.id > 0) {
      this.titleButton = MessageApp.UPDATE;
      this.titleForm = MessageApp.EDIT;
    }
  }

  public ngOnInit(): void {
    this.createFormBuilder();

    this.breakpoint = window.innerWidth <= 600 ? 1 : 2; // Breakpoint observer code
  }

  createFormBuilder(): void {
    this.form = this.fb.group({
      id: this.category?.id,
      name: [
        this.category?.name,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(80),
          Validators.pattern("[a-zA-Z]+([a-zA-Z ]+)*"),
        ],
      ],
      groupName: [this.category?.groupName, [Validators.required]],
      enable: [
        this.category?.enable ? this.category?.enable : true,
        [Validators.required],
      ],
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

  onSubmit() {
    this.validateForm();
  }

  add() {
    this.service.create(this.form.value).subscribe({
      complete: () => console.info("complete"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (resp) => {
        this.closeDialogRef(resp);
      },
    });
  }

  update() {
    this.service.update(this.form.value).subscribe({
      complete: () => console.info("complete"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (resp) => {
        this.closeDialogRef(resp);
      },
    });
  }

  validateForm(): void {
    if (!this.form.valid) {
      return;
    }

    if (this.form.controls.id.value > 0) {
      this.update();
      return;
    }

    this.add();
  }

  private closeDialogRef(resp: Category) {
    this.dialogRef.close({
      event: "close",
      data: this.form.value,
      response: resp,
    });
  }
}
