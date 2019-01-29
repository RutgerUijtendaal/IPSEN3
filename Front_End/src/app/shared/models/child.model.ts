export class ChildModel {
  public id: number;
  public date: Date;
  public isBorn: boolean;

  constructor(id: number, date: Date, isBorn: boolean) {
    this.id = id;
    this.date = date;
    this.isBorn = isBorn;
  }

}
