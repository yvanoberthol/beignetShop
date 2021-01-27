import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ProduitService} from '../../services/produit.service';
import {Produit} from '../../models/produit';

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent implements OnInit {

  minPrice = 25;
  formAddProduit: FormGroup;
  produits: Produit[];
  constructor(
    private produitService: ProduitService) {
    this.formAddProduit = new FormGroup(
      {
        libelle: new FormControl('', [Validators.required]),
        prix: new FormControl(25, [Validators.min(this.minPrice)]),
      }
    );
  }

  ngOnInit() {
    this.getProduits();
  }

  addProduit(formAddProduit: FormGroup) {
    this.produitService.add(formAddProduit.value).subscribe(
      (data: any) => {
       this.getProduits();
    }
    );
  }

  deleteProduit(id) {
    this.produitService.delete(id).subscribe(
      (data: any) => {
        this.getProduits();
      }
    );
  }

  getProduits() {
    this.produitService.getAll().subscribe(data => this.produits = data);
  }
}
