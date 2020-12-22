import { Deserializable } from './deserializable';
import { Address } from './address';

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

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
