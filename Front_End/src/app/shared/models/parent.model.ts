export class ParentModel {
  public id: number;
  public firstName: string;
  public email: string;
  public phoneNr: string;
  public token: string;
  public password = '';

  constructor(firstName: string, email: string, phoneNr: string, token: string, password = '') {
    this.firstName = firstName;
    this.email = email;
    this.phoneNr = phoneNr;
    this.token = token;
    this.password = password;
  }
}
