import { Component, EventEmitter, OnInit } from '@angular/core';
import { Group } from 'src/app/models/group';
import { GroupService } from 'src/app/services/group.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {
  groups: Array<Group> = [];
  searchText: string ="";
  constructor(private groupService: GroupService ) {}

  ngOnInit(): void {
    this.groupService.getGroups().subscribe(data => this.groups = data);
  }


  handleChange(text: any){
    console.log(text);
    this.groupService.getFiltred(text).subscribe(data => this.groups = data);
  }
}
