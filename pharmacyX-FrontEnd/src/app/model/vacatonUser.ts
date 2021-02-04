import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';
import { Vacation } from './vacation';

export class VacationUser implements Deserializable {
    firstName: string;
    lastName: string;
    email: string;
    vacation: Vacation;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
