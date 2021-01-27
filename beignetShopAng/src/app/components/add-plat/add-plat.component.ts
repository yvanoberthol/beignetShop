import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LignePlat} from '../../models/ligne-plat';
import {Produit} from '../../models/produit';
import {ProduitService} from '../../services/produit.service';
import {PlatService} from '../../services/plat.service';
import {Plat} from '../../models/plat';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-add-plat',
  templateUrl: './add-plat.component.html',
  styleUrls: ['./add-plat.component.css']
})
export class AddPlatComponent implements OnInit, OnDestroy {
  produits: Produit[];
  @Input() panierId;
  mySubscription: any;
  platVide = false;
  montantInvalide = false;
  lignePlatsFormGroup: FormGroup[] = [];
  constructor(private produitService: ProduitService,
              private platService: PlatService,
              private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      }
    });

  }

  ngOnInit() {
    this.getProduits();
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  getProduits() {
    this.produitService.getAll().subscribe(
      data => {
        this.produits = data;
        data.forEach(produit => {
          this.lignePlatsFormGroup[produit.libelle] = new FormGroup({
            nom: new FormControl(produit.libelle, Validators.required),
            montant: new FormControl(0, Validators.required)
          });
        });
      });
  }

  addPlat(platFormGroup: FormGroup[]) {
    let lignePlats: LignePlat[] = [] ;
    let montantPlat = 0;
    this.produits.forEach(
      produit => {
        const produitGroup = platFormGroup[produit.libelle];
        if (produitGroup.value.montant > 0){
          if (produitGroup.value.montant % produit.prix === 0) {
            montantPlat += produitGroup.value.montant;
            const lignePlat = new LignePlat();
            lignePlat.nomProduit = produitGroup.value.nom;
            lignePlat.montant = produitGroup.value.montant;
            lignePlats.push(lignePlat);
          } else {
            montantPlat = 0;
            lignePlats = [];
            this.montantInvalide = true;
            this.platVide = false;
          }
        }
      }
    );

    if (montantPlat === 0) {
      this.platVide = true;
      this.montantInvalide = false;
    } else {
      const plat = new Plat();
      plat.panier_id = this.panierId;
      plat.montant = montantPlat;
      plat.lignePlats = lignePlats;
      this.platService.add(plat).subscribe(
        () => {
          this.router.navigateByUrl('/addCommande');
        }, ( err) => {
          console.log(err);
        }
      );
    }

  }

}
