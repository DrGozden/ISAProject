import { Deserializable } from './deserializable';

export class Address implements Deserializable {
    
    street: string;
    city: string;
    country: string;
    postalCode: string;
    deleted: boolean;
    id: number;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}