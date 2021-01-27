import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UtilisateurService} from '../../../services/utilisateur.service';

@Component({
  selector: 'app-update-utilisateur',
  templateUrl: './update-utilisateur.component.html',
  styleUrls: ['./update-utilisateur.component.css']
})
export class UpdateUtilisateurComponent implements OnInit {

  @Input() userId;
  user;
  formUpdateUtilisateur: FormGroup;

  constructor(private utilisateurService: UtilisateurService) {
    this.formUpdateUtilisateur = new FormGroup(
      {
        id: new FormControl(this.userId),
        nomComplet: new FormControl('', Validators.required),
        phoneNumber: new FormControl('', Validators.required),
        email: new FormControl('', Validators.required),
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      }
    );
  }

  ngOnInit() {
    this.getUtilisateur(this.userId);
  }

  updateUtilisateur(formUpdateUtilisateur: FormGroup) {

  }

  getUtilisateur(id) {
    this.utilisateurService.getOne(this.userId)
      .subscribe(
        (data: any) => {
          this.formUpdateUtilisateur.patchValue(
            {
              nomComplet: data.nomComplet,
              phoneNumber: data.phoneNumber,
              email: data.email,
              username: data.username
            }
          );
        });
    }
}
