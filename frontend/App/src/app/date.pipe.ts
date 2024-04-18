import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'date'
})
export class DatePipe implements PipeTransform {

  transform(value: any): String {
  let today=new Date();
  let z=new Date(value);
  let diff=Math.abs(today.valueOf()-z.valueOf());
  diff=Math.ceil(diff / (1000 * 3600 * 24*7));
  return diff.toString()+"days ago";  
  }

}
