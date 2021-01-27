import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {AuthenticationService} from './services/authentication.service';
import {PanierService} from './services/panier.service';
import {Panier} from './models/panier';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'beignetShop';
  isconnected = false;
  user;
  panier: Panier;

  constructor(private authenticationService: AuthenticationService,
              private panierService: PanierService,
              private router: Router) {
    this.router.events.subscribe(
      (val: any) => {
        if (val instanceof NavigationEnd) {
          this.isconnected = this.authenticationService.isConnected();
          if (this.authenticationService.isConnected()) {
            this.user = this.authenticationService.getUser();
          }
        }
      }
    );
  }

  ngOnInit() {
  }

  goToCommande() {
    this.router.navigateByUrl('/addCommande');
  }

  goToProduit() {
    this.router.navigateByUrl('/addProduit');
  }

  goToUtilisateur() {
    this.router.navigateByUrl('/addUtilisateur');
  }



  deconnexion() {
    this.authenticationService.logout();
    this.router.navigateByUrl('/connexion');
  }

  goToCompte() {
    this.router.navigateByUrl('/compte');
  }
}
