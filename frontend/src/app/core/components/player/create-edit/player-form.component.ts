import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Player } from 'src/app/core/models/player';
import { PlayerService } from 'src/app/core/services/player.service';
import { NotificationService } from 'src/app/core/services/shared/notification.service';
import { MessageApp } from 'src/app/utils/messages';

@Component({
  selector: 'app-player-form',
  templateUrl: './player-form.component.html',
  styleUrls: ['./player-form.component.css']
})
export class PlayerFormComponent implements OnInit {
  public titleForm: string = "Add new Player";
  public titleButton: string = "Create";
  public breakpoint: number; // Breakpoint observer code
  public form: FormGroup;
  wasFormChanged = false;
  player: Player = new Player();
  positionList: string[] = [
    "NA",
    "Portero (P)",
    "Defensor Izquierdo (DF I)",
    "Defensor Derecho (DF D)",
    "Defensa Central (DF C)",
    "Mediocampista Defensivo Izquierdo (DM I)",
    "Mediocampista Defensivo Diestro (DM D)",
    "Mediocampista Defensivo Central (DM C)",
    "Mediocampista Izquierdo (M I)",
    "Mediocampista Derecho (M D)",
    "Mediocampista Central",
    "Mediocampista Ofensivo Izquierdo (MP I)",
    "Mediocampista Ofensivo Derecho (MP D)",
    "Mediocampista Ofensivo Central (MP C)",
    "Delantero Izquierdo (D I)",
    "Delantero Derecho (D D)",
    "Delantero Centro (D C)"
  ];

  validation_messages = {
    'name': [
      { type: 'required', message: 'name is required' },
      { type: 'minlength', message: 'name must be at least 10 characters long' },
      { type: 'maxlength', message: 'name cannot be more than 90 characters long' },
      { type: 'pattern', message: 'Your name must contain only letters' }
    ],
    'position': [
      { type: 'required', message: 'Position is required' }
    ],
    'cellphone': [
      { type: 'required', message: 'Cellphone is required' },
      { type: 'minlength', message: 'Cellphone must be at least 6 characters long' },
      { type: 'maxlength', message: 'Cellphone cannot be more than 10 characters long' },
      { type: 'pattern', message: 'Your Cellphone must contain only numbers' },
      { type: 'validCellphone', message: 'Your cellphone has already been taken' }
    ]
  };

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private service: PlayerService,
    public dialogRef: MatDialogRef<PlayerFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    public notificationService: NotificationService
  ) {
    this.player = data;

    if (this.player?.id > 0) {
      this.titleButton = MessageApp.UPDATE;
      this.titleForm = MessageApp.EDIT;
    }
  }

  public ngOnInit(): void {
    this.createFormBuilder();

    this.breakpoint = window.innerWidth <= 800 ? 1 : 2; // Breakpoint observer code
  }

  createFormBuilder(): void {
    this.form = this.fb.group({
      id: this.player?.id,
      name: [
        this.player?.name,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(90),
          Validators.pattern("^[ña-zA-Z ]*$"),
        ],
      ],
      position: [this.player?.position, [
        Validators.required
      ]],
      cellphone: [this.player?.cellphone, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(10),
        Validators.pattern("^[0-9]*$")
      ]],
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
    this.breakpoint = event.target.innerWidth <= 800 ? 1 : 2;
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
      complete: () => console.info("complete player add"),
      error: (err) => {
        this.showErrorMessage(err.error.message);
      },
      next: (resp) => {
        this.closeDialogRef(resp);
      },
    });
  }

  update() {
    this.service.update(this.form.value).subscribe({
      complete: () => console.info("complete player update"),
      error: (err) => {
        this.showErrorMessage(err.error.message);
      },
      next: (resp) => {
        this.closeDialogRef(resp);
      },
    });
  }

  showErrorMessage(error: any){
    this.notificationService.error(error, 'Close');
  }

  showSuccessMessage(success: any){
    this.notificationService.success(success);
    console.log(success);
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

  private closeDialogRef(resp: Player) {
    this.dialogRef.close({
      event: "close",
      data: this.form.value,
      response: resp,
    });
  }
}
