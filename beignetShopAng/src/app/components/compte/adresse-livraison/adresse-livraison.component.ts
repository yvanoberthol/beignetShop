import {Component, Input, OnInit} from '@angular/core';
import {CommandeService} from '../../../services/commande.service';
import {AdresseLivraisonService} from '../../../services/adresse-livraison.service';

@Component({
  selector: 'app-adresse-livraison',
  templateUrl: './adresse-livraison.component.html',
  styleUrls: ['./adresse-livraison.component.css']
})
export class AdresseLivraisonComponent implements OnInit {

  @Input() user;
  private adresses: any;
  constructor(private adresseLivraisonService: AdresseLivraisonService) { }

  ngOnInit() {
    this.getAdresseLivraison(this.user.id);
  }

  getAdresseLivraison(utilisateurId) {
    this.adresseLivraisonService.getAll(utilisateurId).subscribe(
      (data: any) => {
        this.adresses = data;
      }
    );
  }

  deleteAdresseLivraison(id) {
    this.adresseLivraisonService.delete(id).subscribe(
      () => this.getAdresseLivraison(this.user.id)
    );
  }

}
