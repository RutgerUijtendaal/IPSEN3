export class ParentModel {
  public id: number;
  public firstName: string;
  public email: string;
  public phoneNr: string;

  constructor(firstName: string, email: string, phoneNr: string) {
    this.firstName = firstName;
    this.email = email;
    this.phoneNr = phoneNr;
  }
}
