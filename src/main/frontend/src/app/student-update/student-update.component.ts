import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StudentModel} from '../student.model';
import {StudentService} from '../student.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-student-update',
  templateUrl: './student-update.component.html',
  styleUrls: ['./student-update.component.scss']
})
export class StudentUpdateComponent implements OnInit {

  studentForm: FormGroup;
  loading = false;
  successMessage: string;
  errorMessage: string;
  id: number;
  actionUpdate = false;

  constructor(
    private formBuilder: FormBuilder,
    private studentService: StudentService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params.id;
    this.actionUpdate = this.id != null && this.id > 0;

    this.studentForm = this.formBuilder.group({
      firstname: [null, Validators.required],
      lastname: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      school: [null, Validators.required],
    });

    if (this.actionUpdate) {
      this.errorMessage = null;
      this.studentService.findById(this.id)
        .subscribe(student => {
          this.studentForm.patchValue({
            firstname: student.firstname,
            lastname: student.lastname,
            email: student.email,
            school: student.school
          });
        }, error => {
          if (error.status === 404) {
            this.errorMessage = error.error;
          }
        });
    }
  }

  get formControls(): any {
    return this.studentForm.controls;
  }

  cancel(): void {
    this.router.navigate(['/']).then();
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.studentForm.invalid) {
      console.log('invalid data');
      return;
    }

    const student = new StudentModel();
    student.firstname = this.formControls.firstname.value;
    student.lastname = this.formControls.lastname.value;
    student.email = this.formControls.email.value;
    student.school = this.formControls.school.value;

    this.loading = true;
    if (this.actionUpdate) {
      this.studentService.update(this.id, student)
        .subscribe(() => {
          this.loading = false;
          this.successMessage = 'Student saved';
        }, error => {
          this.loading = false;
          this.errorMessage = `An error occur. Code: ${error.status}`;
          console.error(error);
        });
    } else {
      this.studentService.create(student)
        .subscribe(() => {
          this.loading = false;
          this.successMessage = 'Student saved';
        }, error => {
          this.loading = false;
          this.errorMessage = `An error occur. Code: ${error.status}`;
          console.error(error);
        });
    }
  }

}
