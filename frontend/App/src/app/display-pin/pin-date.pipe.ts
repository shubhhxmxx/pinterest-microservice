import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pinDate'
})
export class PinDatePipe implements PipeTransform {

  transform(value: any): String{
    let today=new Date();
    let postDate=new Date(value);

    let diff=Math.abs(today.valueOf()-postDate.valueOf());
    diff=Math.ceil(diff / (1000 * 3600 * 24));
    return diff+"d ago";

  }

}
