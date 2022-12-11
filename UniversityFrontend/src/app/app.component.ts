import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'UniversityFrontend';
  menuList: MenuItem[] = [
    {title : "Home", url : "/"},
    { title : "Students", url : "/students"},
    {title : "Groups", url : "/groups"},
    { title : "Teachers", url : "/teachers"}
  ]
}

interface MenuItem{
 title: string
 url: string
}
