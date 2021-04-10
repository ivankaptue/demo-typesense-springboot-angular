import {Component, OnInit} from '@angular/core';
import {PageModel} from '../page.model';
import {StudentModel} from '../student.model';
import {StudentService} from '../student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {

  page = 1;
  size = 10;

  studentPage: PageModel<StudentModel>;
  loading = false;
  successMessage: string;
  errorMessage: string;

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

  deleteStudent(student: StudentModel): void {
    this.successMessage = null;
    this.errorMessage = null;
    this.studentService.delete(student.id)
      .subscribe(() => {
        this.successMessage = `Student ${student.firstname} ${student.lastname} deleted`;
        this.loadStudents();
      }, error => {
        if (error.status === 404) {
          this.errorMessage = `An error occur. Code : ${error.status}`;
        }
      })
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
