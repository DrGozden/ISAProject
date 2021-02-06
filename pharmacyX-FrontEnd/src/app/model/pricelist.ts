import { Deserializable } from './deserializable';
import { Price } from './price';
import { User } from './user';

export class PriceList implements Deserializable {
    
    startDate: Date;
    endtDate: Date;
    id: number;
    deleted: boolean;
    prices: Price[];



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

