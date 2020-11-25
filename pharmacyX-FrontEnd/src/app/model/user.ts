import { Deserializable } from './deserializable';
import { Address } from './address';

export class User implements Deserializable {
    
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    phone: string;
    Address: Address;
    jwttoken : string;
    idUser : number;
    deleted : boolean;
    userRole: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
