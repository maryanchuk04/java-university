import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  studentsList!: Student[];
  searchText:string = "";
  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
    this.studentService.getAllStudents().subscribe(data=>{
      this.studentsList = data;
    });
  }

  handleChange(search: any){
    console.log(search);
    this.studentService.getFiltered(search).subscribe(data => this.studentsList = data);
  }

}
