import { Address } from './address';
import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';
import { WorkingHours } from './workingHours';

export class Employee implements Deserializable {
    
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    phone: string;
    address : Address = new Address();
    deleted: boolean = false;
    userRole: string;
    workingHours: WorkingHours[] = [];

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
