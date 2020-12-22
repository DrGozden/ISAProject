import { Deserializable } from './deserializable';
import { User } from './user';

export class Pharmacy implements Deserializable {
    
    id: number;
    name: string;
    description: string;
    dermatologists : User[];
    pharmacists: User[];
    ratings: number[];



    

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}

