import { Deserializable } from './deserializable';
import { DrugSpecification } from './drugSpecification';

export class Drug implements Deserializable {
    id: number;
    name: string;
    code: string;
    specification: DrugSpecification;
    deleted: boolean

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}
