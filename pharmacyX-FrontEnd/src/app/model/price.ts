import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { User } from './user';

export class Price implements Deserializable {
    
    drug: Drug;
    price: number;



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

