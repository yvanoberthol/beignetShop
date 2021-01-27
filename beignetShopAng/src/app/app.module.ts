import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddProduitComponent } from './components/add-produit/add-produit.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AddCommandeComponent } from './components/add-commande/add-commande.component';
import { AddUtilisateurComponent } from './components/add-utilisateur/add-utilisateur.component';
import {JwtinterceptorService} from './helpers/jwtinterceptor.service';
import {ErrorinterceptorService} from './helpers/errorinterceptor.service';
import { AddPlatComponent } from './components/add-plat/add-plat.component';
import { PanierComponent } from './components/panier/panier.component';
import { ConnexionComponent } from './components/connexion/connexion.component';
import { MontantZeroOffPipe } from './pipes/montant-zero-off.pipe';
import { PayementComponent } from './components/payement/payement.component';
import { CompteComponent } from './components/compte/compte.component';
import { UpdateUtilisateurComponent } from './components/compte/update-utilisateur/update-utilisateur.component';
import { AdresseLivraisonComponent } from './components/compte/adresse-livraison/adresse-livraison.component';
import { CommandeComponent } from './components/compte/commande/commande.component';

@NgModule({
  declarations: [
    AppComponent,
    AddProduitComponent,
    AddCommandeComponent,
    AddUtilisateurComponent,
    AddPlatComponent,
    PanierComponent,
    ConnexionComponent,
    MontantZeroOffPipe,
    PayementComponent,
    CompteComponent,
    UpdateUtilisateurComponent,
    AdresseLivraisonComponent,
    CommandeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtinterceptorService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorinterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
