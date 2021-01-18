import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class PharmacistExam implements Deserializable {
    
    pharmacist: User;
    pharmacistOpinion: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
