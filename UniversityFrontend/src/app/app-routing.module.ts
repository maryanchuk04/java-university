import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GroupsComponent } from './components/groups/groups.component';
import { MainComponent } from './components/main/main.component';
import { StudentComponent } from './components/student/student.component';

const routes: Routes = [
  {path :"" , component : MainComponent},
  {path : "students", component : StudentComponent},
  {path : "groups", component : GroupsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
