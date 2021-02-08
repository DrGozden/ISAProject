import { Drug } from '../model/drug';
export class PricelistDTO{
    
    startDate: string;
    drugs:Drug[] = [];
    pricesList:number[] = [];
    endtDate: Date;
    id: number;
    deleted: boolean;

}

