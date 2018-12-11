export class ChildModel {
  private _date: Date;
  private _isBorn: boolean;

  constructor(date: Date, isBorn: boolean) {
    this._date = date;
    this._isBorn = isBorn;
  }

  get date(): Date {
    return this._date;
  }

  get isBorn(): boolean {
    return this._isBorn;
  }
}
