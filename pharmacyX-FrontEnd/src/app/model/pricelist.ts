import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Price } from './price';
import { User } from './user';

export class PriceList implements Deserializable {
    
    startDate: string;
    drugs:Drug[] = [];
    pricesList:number[] = [];
    endtDate: Date;
    id: number;
    deleted: boolean;



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

