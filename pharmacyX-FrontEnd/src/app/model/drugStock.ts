import { PricelistDTO } from '../modelDTO/pricelistDTO';
import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { PriceList } from './pricelist';
import { User } from './user';

export class DrugStock implements Deserializable {
    
    drug: Drug;
    quantity: number;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

