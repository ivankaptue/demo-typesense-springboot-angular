import {Component, OnInit} from '@angular/core';
import {StudentService} from './student.service';
import {PageModel} from './page.model';
import {StudentModel} from './student.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  page = 1;
  size = 10;

  studentPage: PageModel<StudentModel>;
  loading = false;

  constructor(private studentService: StudentService) {
  }

  ngOnInit(): void {
    this.loadStudents();
  }

  previousPage(): void {
    if (this.studentPage.first) {
      return;
    }
    this.page = this.page - 1;
    this.loadStudents();
  }

  nextPage(): void {
    if (this.studentPage.last) {
      return;
    }
    this.page = this.page + 1;
    this.loadStudents();
  }

  changePerPage(event): void {
    this.page = 1;
    this.size = event.target.value;
    this.loadStudents();
  }

  private loadStudents(): void {
    this.loading = true;
    this.studentService.findAll(this.page, this.size)
      .subscribe(data => {
          this.loading = false;
          this.studentPage = data;
        },
        error => {
          this.loading = false;
          console.error(error);
        });
  }

}
