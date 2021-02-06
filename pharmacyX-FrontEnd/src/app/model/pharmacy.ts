import { Deserializable } from './deserializable';
import { PriceList } from './pricelist';
import { User } from './user';

export class Pharmacy implements Deserializable {
    
    id: number;
    name: string;
    description: string;
    dermatologists : User[];
    pharmacists: User[];
    ratings: number[];
    priceList: PriceList[];



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

