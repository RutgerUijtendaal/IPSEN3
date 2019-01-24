export class ParentModel {
  public id: number;
  public firstName: string;
  public email: string;
  public phoneNr: string;
  public password = '';

  constructor(firstName: string, email: string, phoneNr: string, password = '') {
    this.firstName = firstName;
    this.email = email;
    this.phoneNr = phoneNr;
    this.password = password;
  }
}
