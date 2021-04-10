import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentUpdateComponent} from './student-update/student-update.component';
import {StudentListComponent} from './student-list/student-list.component';

const routes: Routes = [
  {path: '', component: StudentListComponent},
  {path: 'students/create', component: StudentUpdateComponent},
  {path: 'students/update/:id', component: StudentUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
