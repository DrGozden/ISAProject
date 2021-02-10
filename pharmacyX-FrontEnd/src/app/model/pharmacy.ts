import { PricelistDTO } from '../modelDTO/pricelistDTO';
import { Address } from './address';
import { Deserializable } from './deserializable';
import { DrugStock } from './drugStock';
import { PriceList } from './pricelist';
import { User } from './user';

export class Pharmacy implements Deserializable {
    
    id: number = 0;
    name: string;
    description: string;
    dermatologists : User[];
    pharmacists: User[];
    ratings: number[];
    drugsInStock: DrugStock[] = [];
    priceList: PricelistDTO[];
    address: Address = new Address();



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

