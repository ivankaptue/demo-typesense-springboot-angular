import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {StudentUpdateComponent} from './student-update/student-update.component';
import {ReactiveFormsModule} from '@angular/forms';
import {StudentListComponent} from './student-list/student-list.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentUpdateComponent,
    StudentListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
