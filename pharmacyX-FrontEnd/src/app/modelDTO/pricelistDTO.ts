import { Drug } from '../model/drug';
export class PricelistDTO{
    
    startDate: string;
    startDateString: string;
    drugs:Drug[] = [];
    pricesList:number[] = [];
    endtDate: Date;
    id: number;
    deleted: boolean;

}

