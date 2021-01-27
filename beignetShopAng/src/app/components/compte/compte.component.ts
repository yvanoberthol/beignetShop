import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css']
})
export class CompteComponent implements OnInit {
  private user: any;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.user = this.authenticationService.getUser();
  }

}
