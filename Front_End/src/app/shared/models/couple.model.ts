import {ParentModel} from './parent.model';

export class CoupleModel {

  public coupleId: number;
  public parent1: ParentModel;
  public parent2: ParentModel;
  public signupDate: Date;
  public token: string;

  constructor(coupleId: number, parent1: ParentModel, parent2: ParentModel, signupDate: Date, token: string) {
    this.coupleId = coupleId;
    this.parent1 = parent1;
    this.parent2 = parent2;
    this.signupDate = signupDate;
    this.token = token;
  }
}
