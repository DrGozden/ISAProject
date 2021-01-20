import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class DermatologistExam implements Deserializable {
    
    dermatologist: User;
    diagnosis: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
