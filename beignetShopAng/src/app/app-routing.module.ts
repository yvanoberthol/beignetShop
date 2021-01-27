import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddProduitComponent} from './components/add-produit/add-produit.component';
import {AddCommandeComponent} from './components/add-commande/add-commande.component';
import {AddUtilisateurComponent} from './components/add-utilisateur/add-utilisateur.component';
import {ConnexionComponent} from './components/connexion/connexion.component';
import {AuthguardService} from './helpers/authguard.service';
import {CompteComponent} from './components/compte/compte.component';
import {AutorisationGuardService} from './helpers/autorisation-guard.service';

const routes: Routes = [
  {path: 'addProduit', component: AddProduitComponent, canActivate: [AuthguardService, AutorisationGuardService]},
  {path: 'addCommande', component: AddCommandeComponent, canActivate: [AuthguardService]},
  {path: 'addUtilisateur', component: AddUtilisateurComponent, canActivate: [AuthguardService, AutorisationGuardService]},
  {path: 'compte', component: CompteComponent, canActivate: [AuthguardService]},
  {path: 'connexion', component: ConnexionComponent},
  { path: '', redirectTo: '/addCommande', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
