export class AdminModel {

  public id: number;
  public email: string;
  public password: string;
  public rightId: string;
  public signupDate: Date;

  constructor(id: number, email: string, password: string, rightId: string, signupDate: Date) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.rightId = rightId;
    this.signupDate = signupDate;
  }

}
