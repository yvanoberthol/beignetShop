import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  formLogin: FormGroup;
  loading = false;
  msgError = false;
  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.initializeFormGroup();
  }

  public initializeFormGroup() {
    this.formLogin = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  public connexion(formLogin: FormGroup) {
    this.loading = true;
    this.authenticationService.login(formLogin.value).subscribe(
      (data: any) => {
        const jwtToken = data.headers.get('authorization');
        this.authenticationService.saveToken(jwtToken);
        this.router.navigateByUrl('/addCommande');
      }, (err) => {
        this.loading = false;
        console.log(err.status);
        this.msgError = true;
      }
    );
  }

}
