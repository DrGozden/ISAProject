import { Deserializable } from './deserializable';
import { DrugSpecification } from './drugSpecification';

export class Drug implements Deserializable {
    id: number;
    name: string;
    drugType: string;
    contraindications: string;
    structure: string;
    drugForm: string;
    dailyRecommendation: string;
    subtitues: number[] = [];
    produces: string;
    description: string;
    perscription: boolean;
    code: string;
    specification: DrugSpecification;
    deleted: boolean

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
