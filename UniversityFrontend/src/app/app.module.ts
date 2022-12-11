import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { StudentComponent } from './components/student/student.component';
import { StudentCardComponent } from './components/student/student-card/student-card.component';
import { GroupsComponent } from './components/groups/groups.component';
import { GroupComponent } from './components/groups/group/group.component'
import { FormsModule } from '@angular/forms'
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    StudentComponent,
    StudentCardComponent,
    GroupsComponent,
    GroupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
