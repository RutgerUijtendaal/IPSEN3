export class AccountModel {

  public type: string;
  public name: string;
  public email: string;
  public password: string;
  public right: number;

  constructor() {
  }

  setData (data) {
    let loginData;
    loginData = data as AccountModel;
    this.type = loginData.type;
    this.name = loginData.name;
    this.email = loginData.email;
    this.password = loginData.password;
    this.right = loginData.right;
  }

}
