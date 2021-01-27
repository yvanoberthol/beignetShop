import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NavigationEnd, Router} from '@angular/router';
import {AdresseLivraisonService} from '../../services/adresse-livraison.service';
import {AdresseLivraison} from '../../models/adresse-livraison';
import {CommandeService} from '../../services/commande.service';
import {Commande} from '../../models/commande';

@Component({
  selector: 'app-payement',
  templateUrl: './payement.component.html',
  styleUrls: ['./payement.component.css']
})
export class PayementComponent implements OnInit, OnDestroy {
  @Input() userId;
  @Input() panierId;
  adresseLivraisonId;
  adresseLivraisons: AdresseLivraison[];
  formAddAdresse: FormGroup;
  mySubscription: any;

  constructor(private router: Router,
              private adresseLivraisonService: AdresseLivraisonService,
              private commandeService: CommandeService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      }
    });
  }

  ngOnInit() {
    this.getAdresseLivraisons(this.userId);

    this.formAddAdresse = new FormGroup(
      {
        quartier: new FormControl('', Validators.required),
        numBlock: new FormControl(0, Validators.required),
        numCase: new FormControl(0, Validators.required),
        utilisateurId: new FormControl(this.userId)
      }
    );
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  addAdresseLivraison(formAddAdresse: FormGroup) {
    this.adresseLivraisonService.add(formAddAdresse.value).subscribe(
      (data: any) => {
        this.getAdresseLivraisons(this.userId);
      }, (error) => {
        console.log(error);
      }
    );
  }

  getAdresseLivraisons(userId: any) {
    this.adresseLivraisonService.getAll(userId).subscribe(
      adresseLivraisons => this.adresseLivraisons = adresseLivraisons
    );
  }

  creerCommande() {
    const commande = new Commande();
    commande.adresseLivraisonId = this.adresseLivraisonId;
    commande.panierId = this.panierId;

    this.commandeService.add(commande).subscribe(
      (data: any) => {
        this.router.navigateByUrl('/addCommande');
      }, ( err) => {
        console.log(err);
      }
    );
  }
}
