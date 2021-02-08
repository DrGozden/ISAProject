import { PricelistDTO } from '../modelDTO/pricelistDTO';
import { Deserializable } from './deserializable';
import { DrugStock } from './drugStock';
import { PriceList } from './pricelist';
import { User } from './user';

export class Pharmacy implements Deserializable {
    
    id: number;
    name: string;
    description: string;
    dermatologists : User[];
    pharmacists: User[];
    ratings: number[];
    drugsInStock: DrugStock[] = [];
    priceList: PricelistDTO[];



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

