import { Deserializable } from './deserializable';
import { Address } from './address';
import { Drug } from './drug';

export class User implements Deserializable {
    
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    phone: string;
    address: Address;
    jwttoken : string;
    id : number;
    deleted : boolean;
    userRole: string;
    allergies: Drug[] = []; 

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
