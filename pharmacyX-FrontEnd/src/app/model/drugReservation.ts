import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class DrugReservation implements Deserializable {
    
    id: number;
    dateTimeDeadline: Date;
    pharmacyName: string;
    drugName: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
