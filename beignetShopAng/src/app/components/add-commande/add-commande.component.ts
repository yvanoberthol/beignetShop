import { Component, OnInit } from '@angular/core';
import {PanierService} from '../../services/panier.service';
import {Panier} from '../../models/panier';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-commande',
  templateUrl: './add-commande.component.html',
  styleUrls: ['./add-commande.component.css']
})
export class AddCommandeComponent implements OnInit {
  panierExists = false;
  panier: any;
  user;
  slide = 'false';
  constructor(private panierService: PanierService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }
  ngOnInit() {
    this.user = this.authenticationService.getUser();
    this.getPanier(this.user.id);
  }

  creerPanier() {
    const panier = new Panier();
    panier.utilisateurId = this.user.id;
    this.panierService.add(panier).subscribe(
      () => {
        this.getPanier(this.user.id);
      }
    );
  }

  getPanier(utilisateurId) {
    this.panierService.getPanierUtilisateur(utilisateurId).subscribe(
      (data: any) => {
        this.panier = data;
        this.panierExists = (data != null);
      },
      (err) => {
        if (err.status === 404) {
          this.panierExists = false;
        }
      }
    );
  }

  slidePayement(slide: any) {
    this.slide = slide;
  }
}
