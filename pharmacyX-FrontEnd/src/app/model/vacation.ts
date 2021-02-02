import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class Vacation implements Deserializable {
    
    id: number;
    startDate: Date;
    endDate: Date;
    userId: number;
    accepted: boolean;
    deleted: boolean;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
