import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Utilisateur} from '../../models/utilisateur';
import {UtilisateurService} from '../../services/utilisateur.service';
import {UtilisateurStatus} from '../../models/utilisateur-status';

@Component({
  selector: 'app-add-utilisateur',
  templateUrl: './add-utilisateur.component.html',
  styleUrls: ['./add-utilisateur.component.css']
})
export class AddUtilisateurComponent implements OnInit {

  utilisateurs: Utilisateur[];
  formAddUtilisateur: FormGroup;
  constructor(private utilisateurService: UtilisateurService) {
    this.formAddUtilisateur = new FormGroup(
      {
        nomComplet: new FormControl('', Validators.required),
        phoneNumber: new FormControl('', Validators.required),
        email: new FormControl('', [Validators.required, Validators.email]),
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        role: new FormControl('client', Validators.required),
        active: new FormControl(true),
      }
    );
  }

  ngOnInit() {
    this.getUtilisateurs();
  }

  addUtilisateur(formAddUtilisateur: any) {
    this.utilisateurService.add(formAddUtilisateur.value).subscribe(
      (data) => {
        this.getUtilisateurs();
      }
    );
  }

  deleteUtilisateur(id: any) {
    this.utilisateurService.delete(id).subscribe(
      (data) => {
        this.getUtilisateurs();
      }
    );
  }

  getUtilisateurs() {
    this.utilisateurService.getAll()
      .subscribe(utilisateurs => this.utilisateurs = utilisateurs);
  }

  toggleStatus(id, active) {
    const utilisateurStatus = new UtilisateurStatus();
    utilisateurStatus.id = id;
    utilisateurStatus.active = active;

    this.utilisateurService.updateStatus(utilisateurStatus).subscribe(
      () => this.getUtilisateurs()
    );

  }
}
