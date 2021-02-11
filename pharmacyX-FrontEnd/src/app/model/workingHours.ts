import { Address } from './address';
import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class WorkingHours implements Deserializable {
    
    id: number;
    pharmacyId: number;
    employeeId: number;
    day: string;
    startTime: string;
    endTime: string;
    
    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
