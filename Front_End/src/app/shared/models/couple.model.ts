import {ParentModel} from './parent.model';

export class CoupleModel {

  public coupleId: number;
  public parent1: ParentModel;
  public parent2: ParentModel;

  constructor(coupleId: number, parent1: ParentModel, parent2: ParentModel) {
    this.coupleId = coupleId;
    this.parent1 = parent1;
    this.parent2 = parent2;
  }
}
