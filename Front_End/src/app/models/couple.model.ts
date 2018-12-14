import {ParentModel} from './parent.model';

export class CoupleModel {
  public parent1: ParentModel;
  public parent2: ParentModel;

  constructor(parent1: ParentModel, parent2: ParentModel) {
    this.parent1 = parent1;
    this.parent2 = parent2;
  }
}
