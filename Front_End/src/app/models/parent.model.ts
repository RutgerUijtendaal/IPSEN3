export class ParentModel {
  public id: number;
  public name: string;
  public email: string;
  public phone: string;

  constructor(name: string, email: string, phone: string) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
}
