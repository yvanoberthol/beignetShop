import {Component, Input, OnInit} from '@angular/core';
import {CommandeService} from '../../../services/commande.service';

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css']
})
export class CommandeComponent implements OnInit {

  @Input() user;
  private commandes: any;
  constructor(private commandeService: CommandeService) { }

  ngOnInit() {
    this.getCommandeUtilisateur(this.user.id);
  }

  getCommandeUtilisateur(utilisateurId) {
    this.commandeService.getAll(utilisateurId).subscribe(
      (data: any) => {
        this.commandes = data;
      }
    );
  }

}
