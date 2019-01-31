export class ConfigModel {
  private _id: number;
  private _name: string;
  private _value: string;


  constructor(name: string, value: string) {
    this._name = name;
    this._value = value;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get value(): string {
    return this._value;
  }

  set id(value: number) {
    this._id = value;
  }

  set name(value: string) {
    this._name = value;
  }

  set value(value: string) {
    this._value = value;
  }
}
