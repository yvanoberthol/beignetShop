import {Component, Input, OnDestroy, OnInit, Output, EventEmitter} from '@angular/core';
import {PlatService} from '../../services/plat.service';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit, OnDestroy {

  mySubscription;
  id;
  show = false;
  showButton = false;
  @Input() panier: any;
  @Output() slidePayementPage = new EventEmitter<string>();
  montantTotal = 0;

  constructor(private platService: PlatService,
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
    this.panier.plats.forEach(
      plat => {
        this.montantTotal += plat.montant;
      }
    );
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  deletePlat(id: any) {
    this.platService.delete(id).subscribe(
      (data: any) => {
        this.router.navigateByUrl('/addCommande');
      }
    );
  }

  setId(id: any) {
    this.id = id;
    this.show = !this.show;
  }

  slidePayement(value: any) {
    this.showButton = !(value === 'false');
    this.slidePayementPage.emit(value);
  }
}
