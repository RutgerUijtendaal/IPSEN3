export class AdminModel {

  public id: number;
  public email: string;
  public password: string;
  public rightId: string;

  constructor(id: number, email: string, password: string, rightId: string) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.rightId = rightId;
  }

}
