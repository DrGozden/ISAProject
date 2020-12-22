import { Deserializable } from './deserializable';
import { Address } from './address';
import { Drug } from './drug';

export class DrugSpecification implements Deserializable {
    
    id: number;
    drugType: string;
    contraindications: string;
    structure: string;
    drugForm: string;
    dailyRecommendation: Address;
    substitutes: Drug[];
    producer : string;
    description : number;
    prescription : boolean;
    deleted: boolean;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
