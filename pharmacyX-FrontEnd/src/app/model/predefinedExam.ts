import { Deserializable } from './deserializable';
import { Drug } from './drug';
import { Pharmacy } from './pharmacy';
import { User } from './user';

export class PredefinedExam implements Deserializable {
    
    id: number;
    dateTime: Date;
    pharmacy: Pharmacy;
    patient: User;
    therapyDescription: string;
    therapyDrugs: Drug[];
    price : number;
    deleted: boolean;
    dermatologist: User;
    diagnosis: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
