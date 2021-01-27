import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'montantZeroOff'
})
export class MontantZeroOffPipe implements PipeTransform {

  transform(objects: any[]): any[] {
    if (objects) {
      return objects.filter(object => {
        return object.montant > 0;
      });
    }
  }

}
