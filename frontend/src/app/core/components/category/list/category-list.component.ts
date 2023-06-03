import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ViewChild,
} from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { Category } from "../../../models/category";
import { CategoryService } from "src/app/core/services/category.service";
import { CategoryFormComponent } from "../create-edit/category-form.component";
import { DeleteDialogComponent } from "src/app/core/shared/dialogs/delete-dialog/delete-dialog.component";
import { MessageApp } from "src/app/utils/messages";
@Component({
  selector: "app-category-list",
  templateUrl: "./category-list.component.html",
  styleUrls: ["./category-list.component.css"],
})
export class CategoryListComponent implements AfterViewInit {
  title: string = "Categorías";
  description: string = "Listado de categorías registradas en el sistema";

  dataList: Category[] = [];
  totalElements: number = 0;
  pageIndex: number = 0;
  pageSize: number = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ["id", "name", "groupName", "enable", "icons"];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor(
    private service: CategoryService,
    public _dialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.init();
  }

  init() {
    this.initDataPages();
  }

  private initDataPages() {
    this.getDataPage(this.pageIndex.toString(), this.pageSize.toString());
  }

  getDataPage(page: string, size: string) {
    this.service.getAllPages(page, size).subscribe({
      complete: () => console.info("complete"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (resp) => {
        this.manageResponsePages(resp);
      },
    });
  }

  getDataPageWithText(page: string, size: string, text: string) {
    this.service.getAllPagesWithText(page, size, text).subscribe({
      complete: () => console.info("complete"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (resp) => {
        this.manageResponsePages(resp);
      },
    });
  }

  enable(category: Category) {
    this.service.enable(category.id, !category.enable).subscribe({
      complete: () => console.info("complete"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (resp) => {
        console.log(resp);
      },
    });
  }

  delete(id: number) {
    const dialogRef = this._dialog.open(DeleteDialogComponent, {
      data: MessageApp.GENERIC_CONFIRMATION_MESSAGE_DELETE,
    });

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.service.delete(id).subscribe({
          complete: () => console.info("complete"),
          error: (err) => {
            console.log(err.error.message);
          },
          next: (resp) => {
            this.initDataPages();
          },
        });
      }
    });
  }

  openDialog(category: any): void {
    const dialogRef = this._dialog.open(CategoryFormComponent, {
      width: "640px",
      disableClose: true,
      data: category,
    });
    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
        if (data["event"] == "close") {
          this.init();
        }
        console.log("TCL: CategoryListComponent -> data", data);
      }
    });
  }
  create(): void {
    this.openDialog(null);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.getDataPageWithText(
      this.pageIndex.toString(),
      this.pageSize.toString(),
      filterValue.trim().toLowerCase().toString()
    );
  }

  detectChanges() {
    this.changeDetectorRefs.detectChanges();
  }

  nextPage(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getDataPage(this.pageIndex.toString(), this.pageSize.toString());
  }
  manageResponsePages(data: any) {
    this.dataSource = new MatTableDataSource(data["content"]);
    this.dataList = data["content"];
    this.totalElements = data["totalElements"] as number;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.detectChanges();
  }
}
